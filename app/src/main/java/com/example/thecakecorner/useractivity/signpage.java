package com.example.thecakecorner.useractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thecakecorner.R;
import com.example.thecakecorner.databse.dbhelper;
import com.example.thecakecorner.loginpage;

public class signpage extends AppCompatActivity {
EditText uname,pass,repass;
dbhelper db;
Button signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signpage);
        uname=(EditText) findViewById(R.id.name);
        pass=(EditText) findViewById(R.id.password);
        repass=(EditText) findViewById(R.id.repassword);
        signin=(Button) findViewById(R.id.signin);
        db=new dbhelper(this);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=uname.getText().toString();
                String passwd=pass.getText().toString();
                String repasswd=repass.getText().toString();
                if(TextUtils.isEmpty(user)||TextUtils.isEmpty(passwd)||TextUtils.isEmpty(repasswd)){
                    Toast.makeText(signpage.this, "All Fields Are Required", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkuser = db.checkusername(user);
                    if (!checkuser) {
                        Boolean insert = db.insertdata(user, passwd);
                        if (insert) {
                            Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), loginpage.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Registration Failled", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(signpage.this, "user is already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}