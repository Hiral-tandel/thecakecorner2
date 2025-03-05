package com.example.thecakecorner.databse;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class caketype extends SQLiteOpenHelper {
    public caketype( Context context) {
        super(context,"cake_type_mst", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("create table cake_type(id INTEGER primary key autoincrement,name text,image longblob)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
    public Boolean insertdata(String name, byte[] image){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("image",image);
        long result=db.insert("cake_type",null,values);
        return result != -1;

    }
    public Boolean checkcaketypename(String typename){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from cake_type where name=?",new String[] {typename});
        return cursor.getCount() > 0;

    }
}











