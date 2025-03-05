package com.example.thecakecorner.useractivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thecakecorner.R;
import com.example.thecakecorner.databse.caketype;
import com.example.thecakecorner.models.modeltype;
import com.example.thecakecorner.useractivity.items;

import java.util.ArrayList;

public class search_and_see extends AppCompatActivity {
    caketype type;
    private ArrayList<modeltype> arrayList;
    SQLiteDatabase sql;
    ListView ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_and_see);
        arrayList = new ArrayList<>();
        type = new caketype(this);
        ls = findViewById(R.id.list);
        display();
    }
      public void display() {
        SQLiteDatabase sql = type.getReadableDatabase();
        Cursor cursor = sql.rawQuery("select * from cake_type", null);
        ArrayList<modeltype> model = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do{
                byte[] avatar = cursor.getBlob(2);
                String name = cursor.getString(1);
                model.add(new modeltype(name, avatar));
            }while (cursor.moveToNext());
            cursor.close();
            Custom adapter= new Custom(this, R.layout.singledata, model);
            ls.setAdapter( adapter);
        }

    }

    private class Custom extends BaseAdapter {
        private final Context context;
        private final int layout;
        private ArrayList<modeltype>model;

        public Custom(Context context, int layout, ArrayList<modeltype> model) {
            this.context = context;
            this.layout = layout;
            this.model = model;
        }
        public  class viewholder{
            TextView txtname;
            ImageView image;
            CardView cd;
            Button add,display;
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
            viewholder holder= new viewholder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout,null);
            holder.txtname=convertView.findViewById(R.id.textView5);
            holder.image=convertView.findViewById(R.id.imageView4);
            convertView.setTag(holder);
            modeltype moda=model.get(position);
            holder.txtname.setText(moda.getName().toUpperCase().replaceAll("_"," "));
            byte[] image=moda.getImage();
            Bitmap bitmap= BitmapFactory.decodeByteArray(image,0,image.length);
            holder.image.setImageBitmap(bitmap);

            holder.display=convertView.findViewById(R.id.button10);
            holder.display.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, items.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("typename",moda.getName());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
            return  convertView;
        }
    }
}