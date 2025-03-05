package com.example.thecakecorner.useractivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thecakecorner.R;
import com.example.thecakecorner.databse.dbhelper;
import com.example.thecakecorner.loginpage;

public class userprofile extends AppCompatActivity {
dbhelper db;
EditText name,newpass;
Button logount,save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        db=new dbhelper(this);
        save=(Button) findViewById(R.id.button2);
        logount=(Button) findViewById(R.id.button);
        name=(EditText) findViewById(R.id.editTextTextPersonName2);
        newpass=(EditText) findViewById(R.id.editTextTextPersonName);
        logount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(userprofile.this);
                builder.setMessage("Do You Want To Logout?");
                builder.setTitle("Alert!");
                builder.setIcon(R.drawable.round_add_alert_24);
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String user = name.getText().toString();
                        Boolean logoutpage = db.logout(user);
                        if (logoutpage) {
                            Toast.makeText(userprofile.this, " Successfully logout", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), loginpage.class));
                        } else {
                            Toast.makeText(userprofile.this, "Try Again", Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }

                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = name.getText().toString();
                String pass = newpass.getText().toString();
                Boolean checkname = db.checkusername(user);
                if (checkname == true) {
                    Boolean updated = db.update(user, pass);
                    if (updated) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(userprofile.this);
                        builder.setMessage("Password Successfully Changed");
                        builder.setTitle("SUCCESS");
                        builder.setIcon(R.drawable.baseline_verified_24);
                        builder.setCancelable(false);
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                        AlertDialog build=builder.create();
                        build.show();
                    } else {
                        Toast.makeText(userprofile.this, "not update try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}