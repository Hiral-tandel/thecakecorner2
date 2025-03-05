package com.example.thecakecorner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thecakecorner.adminactivity.ad_home;
import com.example.thecakecorner.databse.dbhelper;
import com.example.thecakecorner.useractivity.signpage;
import com.example.thecakecorner.useractivity.user_home;

public class loginpage extends AppCompatActivity {
EditText name,pass;
Button login;
String ad_name="Hiral";
String password="Hiral@2804";
dbhelper db;
TextView sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        name=(EditText) findViewById(R.id.logname);
        pass=(EditText) findViewById(R.id.logpass);
        login=(Button) findViewById(R.id.login);
        sign=(TextView) findViewById(R.id.textView);
        db=new dbhelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String user=name.getText().toString();
                String passwd=pass.getText().toString();
                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(passwd)){
                    Toast.makeText(loginpage.this, " All Fields Are Required", Toast.LENGTH_SHORT).show();
                }else{
                    if(user.equals(ad_name)&& passwd.equals(password)){
                        Toast.makeText(loginpage.this, " Welcome Hiral", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(loginpage.this, ad_home.class));
                    }else{
                        Boolean check = db.checkuserpass(user, passwd);
                        if (check == false) {
                            Toast.makeText(loginpage.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(loginpage.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), user_home.class);
                            Bundle b=new Bundle();
                            b.putString("name",user);
                            i.putExtras(b);
                            startActivity(i);
                        }
                    }
                }
            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginpage.this, signpage.class));
            }
        });
    }

}