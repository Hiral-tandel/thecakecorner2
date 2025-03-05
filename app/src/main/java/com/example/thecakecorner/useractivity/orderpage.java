package com.example.thecakecorner.useractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thecakecorner.R;
import com.example.thecakecorner.databse.orderdb;

public class orderpage extends AppCompatActivity {
TextView name,price,date1;
RadioGroup rg;
RadioButton bgm,bkg,bbkg;
ImageView img;
Button order;
orderdb dbdata;
EditText adrs,msg,namecus;
int weigh=0;
int value=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderpage);
        name = findViewById(R.id.textView20);
        price = findViewById(R.id.textView14);
        bgm = findViewById(R.id.radioButton);
        img = findViewById(R.id.imageView6);
        bkg = findViewById(R.id.radioButton2);
        rg = findViewById(R.id.radioGroup);
        bbkg = findViewById(R.id.radioButton3);
        namecus=findViewById(R.id.customername);
        date1 = findViewById(R.id.date);
        adrs = findViewById(R.id.address);
        msg = findViewById(R.id.message);
        order = findViewById(R.id.button5);
        dbdata=new orderdb(this);

          Bundle bundle=getIntent().getExtras();
          name.setText(bundle.getString("cake name"));
          String priced= bundle.getString("price");
            price.setText(priced);
            String weighed= bundle.getString("weigh");
             bgm.setText(weighed);
                byte[] image= bundle.getByteArray("image");
                Bitmap bitmap=BitmapFactory.decodeByteArray(image,0,image.length);
                img.setImageBitmap(bitmap);
              rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(RadioGroup group, int checkedId) {
                      int price1= Integer.parseInt(price.getText().toString());
                      if(bgm.isChecked()){
                      weigh=500;
                    value=price1;
                }if (bkg.isChecked()) {
                       weigh=1;
                       value=price1*2;
                } else if (bbkg.isChecked()) {
                        weigh=2;
                        value=price1*4;
                }else{
                        Toast.makeText(getApplicationContext(), "select weigh of cake", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //on click open calender dialog
        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               opendialog();
            }
        });

       String cakename = name.getText().toString();
        int price12 = Integer.parseInt(price.getText().toString());
//String cusname=namecus.getText().toString();
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insert order details in orderdb database
               boolean insert = dbdata.insertorder(cakename, value, adrs.getText().toString(),weigh, date1.getText().toString(), msg.getText().toString(),namecus.getText().toString());
                if (insert) {
                    Toast.makeText(orderpage.this, "Successfully order placed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(orderpage.this, "Order Not placed try agein", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//open calender dialog method
    private void opendialog() {
        DatePickerDialog dialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date1.setText(String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(dayOfMonth));
            }
        },2023,7,9);
        dialog.show();
    }

}
