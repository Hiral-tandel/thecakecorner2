package com.example.thecakecorner.adminactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thecakecorner.R;
import com.example.thecakecorner.models.itemmodel;
import com.example.thecakecorner.databse.redvelvethelper;

import java.util.ArrayList;

public class ad_edititem extends AppCompatActivity {

    redvelvethelper typered;
    private ArrayList<itemmodel> arrayList;
    SQLiteDatabase sql;
    ListView ls;

    String typename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_edititem);
        Bundle b1=getIntent().getExtras();
        typename= (b1.getString("typename")).replaceAll("\\s","").toLowerCase();
        arrayList = new ArrayList<>();
        typered= new redvelvethelper(this);

        ls = findViewById(R.id.list);
        // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        display();
    }
    public void display() {
        SQLiteDatabase sql = typered.getReadableDatabase();
        Cursor cursor = sql.rawQuery("select * from "+typename+"", null);
        ArrayList<itemmodel> model = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do{
                byte[] avatar = cursor.getBlob(2);
                String name = cursor.getString(1);
                int price=cursor.getInt(3);
                int weigh=cursor.getInt(4);
                model.add(new itemmodel(name, avatar,price,weigh));
            }while (cursor.moveToNext());
            cursor.close();
            Custom adapter= new Custom(this, R.layout.ad_singledata, model);
            ls.setAdapter( adapter);
        }

    }

    private class Custom extends BaseAdapter {
        private final Context context;
        private final int layout;
        private ArrayList<itemmodel>model;

        public Custom(Context context, int layout, ArrayList<itemmodel> model) {
            this.context = context;
            this.layout = layout;
            this.model = model;
        }
        public  class viewholder{
            TextView txtname,txtweigh;
            ImageView image;
            EditText priceed;
            Button delete,update;
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
            holder.txtname=convertView.findViewById(R.id.textView16);
            holder.priceed=convertView.findViewById(R.id.editTextTextPersonName9);
            holder.txtweigh=convertView.findViewById(R.id.textView23);
            holder.image=convertView.findViewById(R.id.imageView4);
            holder.delete=convertView.findViewById(R.id.button12);
            holder.update=convertView.findViewById(R.id.button7);
            convertView.setTag(holder);
            itemmodel moda=model.get(position);
            String price= String.valueOf(moda.getPrice());
            holder.priceed.setText(price);
            String weigh= String.valueOf(moda.getWeigh());
            holder.txtweigh.setText(weigh);
            holder.txtname.setText(moda.getName());
            byte[] image=moda.getImage();
            Bitmap bitmap= BitmapFactory.decodeByteArray(image,0,image.length);
            holder.image.setImageBitmap(bitmap);
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  Boolean deleteitemh=typered.deleteitem(typename,moda.getName());
                    Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                }
            });
            holder.update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int value = Integer.parseInt(holder.priceed.getText().toString());
                    Boolean update = typered.update(typename, moda.getName(), value);
                    Toast.makeText(context, "Save changes successfully", Toast.LENGTH_SHORT).show();
                }
            });
            return  convertView;
        }
    }
}