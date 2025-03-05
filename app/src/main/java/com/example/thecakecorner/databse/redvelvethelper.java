package com.example.thecakecorner.databse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class redvelvethelper extends SQLiteOpenHelper {
    public redvelvethelper(Context context) {
        super( context,"cake_items_mst", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public Boolean createtable(String TABLE_NAME){
        String tablename=TABLE_NAME.toLowerCase().replaceAll(" ","_");
        SQLiteDatabase db=this.getWritableDatabase();
        String s="create table "+tablename +"(id INTEGER Primary key autoincrement,name TEXT,image longblob,price INTEGER,weigh INTEGER)";
        db.execSQL(s);
        return true;
    }
    public Boolean insertdataitem(String TABLE_NAME,String name, byte[] image,int price,int weigh){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        String tablename=TABLE_NAME.toLowerCase();
        values.put("name",name);
        values.put("image",image);
        values.put("price",price);
        values.put("weigh",weigh);
        long result=db.insert(tablename,null,values);
        return result != -1;

    }
    public Boolean deleteitem(String TABLE_NAME,String name){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("delete from "+TABLE_NAME+ " where name=?",new String[] {name});
        if(cursor.getCount()>0)
            return false;
        else
            return true;
    }
    public Boolean update(String TABLE_NAME, String name, int price) {
        SQLiteDatabase db = this.getWritableDatabase();
        //ContentValues values=new ContentValues();
        Cursor cursor = db.rawQuery("update "+TABLE_NAME+" set price=? where name=?", new String[]{String.valueOf(price), name});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }
}
