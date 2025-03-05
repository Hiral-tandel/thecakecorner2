package com.example.thecakecorner.useractivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thecakecorner.R;

public class user_home extends AppCompatActivity {
ImageView userpage;
TextView seeall,search;
ImageButton back;
ImageButton order;
String name;
ImageView redvel,blackforest,cartoon,chocolate,flower,annivarsary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        userpage=(ImageView) findViewById(R.id.profile);
        seeall=(TextView) findViewById(R.id.textView4);
        search=(TextView) findViewById(R.id.textView11);
        redvel=findViewById(R.id.imgred);
        blackforest=findViewById(R.id.imgblack);
        back=findViewById(R.id.imageButton5);
        cartoon=findViewById(R.id.imgcartoon);
        annivarsary=findViewById(R.id.imganiv);
        order=findViewById(R.id.imageButton6);
        flower=findViewById(R.id.imgflower);
        chocolate=findViewById(R.id.imgchocolate);
        Bundle b=getIntent().getExtras();
        name=b.getString("name");
        seeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(user_home.this, search_and_see.class));
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(user_home.this, searchcakes.class));
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte=new Intent(getApplicationContext(),seeorder.class);
                Bundle b=new Bundle();
                b.putString("cusname",name);
                inte.putExtras(b);
                startActivity(inte);
            }
        });
        userpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(user_home.this, userprofile.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        redvel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), home_items.class);
                Bundle bundle=new Bundle();
                bundle.putString("name","red_velvet_cake");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        blackforest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),home_items.class);
                Bundle bundle=new Bundle();
                bundle.putString("name","black_forest_cake");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        cartoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),home_items.class);
                Bundle bundle=new Bundle();
                bundle.putString("name","cartoon_cake");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        annivarsary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),home_items.class);
                Bundle bundle=new Bundle();
                bundle.putString("name","anniversary_cake");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),home_items.class);
                Bundle bundle=new Bundle();
                bundle.putString("name","flower_cake");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        chocolate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),home_items.class);
                Bundle bundle=new Bundle();
                bundle.putString("name","chocolate_cake");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    public void onBackPressed(){
        AlertDialog.Builder builder=new AlertDialog.Builder(user_home.this);
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