package com.example.thecakecorner.adminactivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.thecakecorner.R;

public class ad_home extends AppCompatActivity {
ImageButton addtype,seeitem,seeuser,additem,seeorde,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_home);
        addtype=(ImageButton) findViewById(R.id.add);
        seeitem=(ImageButton) findViewById(R.id.imageButton2);
        seeuser=(ImageButton) findViewById(R.id.imageButton3);
        additem=(ImageButton) findViewById(R.id.imageButton);
        back=findViewById(R.id.imageButton4);
        seeorde=findViewById(R.id.seeorder);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ad_home.this, com.example.thecakecorner.adminactivity.addtype.class));
            }
        });
        seeitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ad_home.this, ad_seetype.class));
            }
        });
        seeuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ad_home.this, com.example.thecakecorner.adminactivity.seeuser.class));
            }
        });
        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), com.example.thecakecorner.adminactivity.additem.class));
            }
        });
        seeorde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), seeorders.class));
            }
        });
    }
    public void onBackPressed(){
        AlertDialog.Builder builder=new AlertDialog.Builder(ad_home.this);
        builder.setMessage("DO YOU WANT TO EXIT?....");
        builder.setTitle("Alert!");
        builder.setIcon(R.drawable.baseline_info_24);
        builder.setCancelable(false);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}