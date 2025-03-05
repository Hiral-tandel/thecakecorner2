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
import com.example.thecakecorner.databse.caketype;
import com.example.thecakecorner.databse.redvelvethelper;

import java.io.ByteArrayOutputStream;

public class addtype extends AppCompatActivity {
    EditText name;
    ImageView typeimg;
    Button add;
    redvelvethelper itemdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtype);
        name = (EditText) findViewById(R.id.editTextTextPersonName3);
        add = (Button) findViewById(R.id.button3);
        typeimg = (ImageView) findViewById(R.id.imageView3);
        caketype type = new caketype(this);
        itemdb = new redvelvethelper(this);
        typeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String typename = name.getText().toString().replaceAll("\\s", "_").toLowerCase();
                byte[] img = ImageToByte(typeimg);
                Boolean check = type.checkcaketypename(typename);
                if (!check) {
                    Boolean added = type.insertdata(typename, img);
                    if (added==true) {
                        Toast.makeText(addtype.this, "try again", Toast.LENGTH_SHORT).show();
                    } else {
                        itemdb.createtable(typename);
                        Toast.makeText(addtype.this, " successfully added", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        typeimg.setImageResource(R.drawable.baseline_add_a_photo_24);
                    }
                } else {
                    Toast.makeText(addtype.this, " Cake Type Already There", Toast.LENGTH_SHORT).show();
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
            typeimg.setImageURI(selectedimg);

        }
    }

}