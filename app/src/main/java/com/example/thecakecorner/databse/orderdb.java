package com.example.thecakecorner.databse;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class orderdb extends SQLiteOpenHelper {
    public orderdb( Context context) {
        super(context, "order_Mst",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table orderdata(id integer primary key autoincrement ,cakename text ,price integer, weigh integer, address text,Date date,message text,cusname text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public Boolean insertorder(String name, int price, String addre,int weigh, String date, String msg,String cusname){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("cakename",name);
        values.put("price",price);
        values.put("weigh",weigh);
        values.put("address",addre);
        values.put("cusname",cusname);
        values.put("date", String.valueOf(date));
        values.put("message",msg);
       // values.put("phoneno",no);
        Long result=db.insert("orderdata",null,values);
        return result!=-1;
    }
}
