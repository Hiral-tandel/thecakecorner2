package com.example.thecakecorner.useractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thecakecorner.R;
import com.example.thecakecorner.databse.orderdb;
import com.example.thecakecorner.models.ordermodel;

import java.util.ArrayList;

public class seeorder extends AppCompatActivity {
    orderdb order;
    private ArrayList<ordermodel> arrayList;
    SQLiteDatabase sql;
    ListView ls;
String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeorder);
        arrayList = new ArrayList<>();
        Bundle b=getIntent().getExtras();
        name=b.getString("cusname");
        order=new orderdb(this);
        ls = findViewById(R.id.list);
        display();
    }

    public void display() {
        SQLiteDatabase sql = order.getReadableDatabase();
        Cursor cursor = sql.rawQuery("select * from orderdata where cusname='"+name+"'", null);
        ArrayList<ordermodel> model = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do{
                String msg=cursor.getString(6);
                int price=cursor.getInt(2);
                int weigh=cursor.getInt(3);
                String adrs=cursor.getString(4);
                String date=cursor.getString(5);
                String name = cursor.getString(1);
                String cusname=cursor.getString(7);
                model.add(new ordermodel(name, price,adrs,weigh,date,msg,cusname));
            }while (cursor.moveToNext());
            cursor.close();
           Custom adapter= new Custom(this, R.layout.singleorder, model);
            ls.setAdapter( adapter);
        }

    }

    private class Custom extends BaseAdapter {
        private final Context context;
        private final int layout;
        private ArrayList<ordermodel>model;

        public Custom(Context context, int layout, ArrayList<ordermodel> model) {
            this.context = context;
            this.layout = layout;
            this.model = model;
        }
        public  class viewholder{
            TextView txtname,txtprice,txtweigh,txtmsg,txtadrs,txtdate,txtcusname;

        }

        @Override
        public int getCount() {
            return model.size();
        }

        @Override
        public Object getItem(int position) {
            return model.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Custom.viewholder holder= new Custom.viewholder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout,null);
            holder.txtname=convertView.findViewById(R.id.name);
            holder.txtadrs=convertView.findViewById(R.id.address);
            holder.txtdate=convertView.findViewById(R.id.textView31);
            holder.txtprice=convertView.findViewById(R.id.price);
            holder.txtweigh=convertView.findViewById(R.id.weigh);
            holder.txtmsg=convertView.findViewById(R.id.message);
            holder.txtcusname=convertView.findViewById(R.id.txtcusname);
            convertView.setTag(holder);
            ordermodel moda=model.get(position);
            String price= String.valueOf(moda.getPrice());
            holder.txtname.setText(moda.getCakename());
            String weigh= String.valueOf(moda.getWeigh());
            holder.txtweigh.setText(weigh);
            holder.txtadrs.setText(moda.getAddress());
            holder.txtprice.setText(price);
            holder.txtmsg.setText(moda.getMessage());
            holder.txtdate.setText(moda.getDate());
            holder.txtcusname.setText(moda.getCusname());
            return  convertView;
        }

    }

}