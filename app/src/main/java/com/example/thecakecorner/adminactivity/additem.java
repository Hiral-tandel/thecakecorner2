package com.example.thecakecorner.adminactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.thecakecorner.R;
import com.example.thecakecorner.databse.redvelvethelper;

import java.io.ByteArrayOutputStream;

public class additem extends AppCompatActivity {
ImageView img;
EditText type_name,item_name,price,weigh;
Button add;
redvelvethelper typered;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);
        img= findViewById(R.id.imageView5);
        type_name= findViewById(R.id.editTextTextPersonName4);
        item_name= findViewById(R.id.editTextTextPersonName5);
        price= findViewById(R.id.editTextTextPersonName6);
        weigh= findViewById(R.id.editTextTextPersonName7);
        add= findViewById(R.id.button4);
        typered=new redvelvethelper(this);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,2);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tablename=type_name.getText().toString().replaceAll(" ","_");
                String item=item_name.getText().toString();
                byte[] image=ImageToByte(img);
                int item_price= Integer.parseInt(price.getText().toString());
                int item_weigh= Integer.parseInt(weigh.getText().toString());

               Boolean adddata=typered.insertdataitem(tablename,item,image,item_price,item_weigh);
               
               if(adddata==true){
                   Toast.makeText(additem.this, "Successfully addded", Toast.LENGTH_SHORT).show();
                   item_name.setText("");
                   price.setText("");
                   weigh.setText("");
                   img.setImageResource(R.drawable.baseline_add_a_photo_24);
               }else{
                   Toast.makeText(additem.this, "try again", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
    private byte[] ImageToByte(ImageView avtar){
        Bitmap bitmap=((BitmapDrawable)avtar.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream);
        return stream.toByteArray();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && data != null){
            Uri selectedimg=data.getData();
            img.setImageURI(selectedimg);

        }
    }
}