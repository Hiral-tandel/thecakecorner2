package com.example.thecakecorner.databse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.thecakecorner.models.modal;

import java.util.ArrayList;

public class dbhelper  extends SQLiteOpenHelper {
    public static final String DBNAME="user info.db";
    public dbhelper( Context context) {
        super(context,"user info.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table userinfo(user_id INTEGER primary key autoincrement ,username TEXT,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists userinfo");
    }
    public Boolean insertdata(String username,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("username",username);
        values.put("password",password);
        long result=db.insert("userinfo",null,values);
        if(result==-1) return  false;
        else return true;
    }
    //check username function
    public Boolean checkusername(String username){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from userinfo where username=?",new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    //for check password is correct or not
    public Boolean checkuserpass(String username,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from userinfo where username=? and password=? ",new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean update(String name, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        //ContentValues values=new ContentValues();
        Cursor cursor=db.rawQuery("update userinfo set password=? where username=?",new String[] {password,name});
        if(cursor.getCount()>0)
            return false;
        else
            return true;
    }
    public Boolean logout(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("delete from userinfo where username=?",new String[] {name});
        if(cursor.getCount()>0)
            return false;
        else
            return true;
    }
    public  ArrayList<modal> readCourses() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("select * from userinfo", null);

        // on below line we are creating a new array list.
        ArrayList<modal> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new modal(cursorCourses.getString(1)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
    }
}
