package com.example.android_ergasia1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

/*
Kollias Anastasios MPSP19018
Ergasia 1 - Android
 */

public class dbHelper extends SQLiteOpenHelper {
    public dbHelper(@Nullable Context context) {
        super(context, "mydatabase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table users(uname text primary key, pass text)");
        db.execSQL("Create table movies(title text primary key, director text, duration text, genre text, pdyear text, rating text)");
        db.execSQL("Insert into users(uname, pass) VALUES('user', 'user')");
        db.execSQL("Insert into users(uname, pass) VALUES('tasos', '1234')");
        db.execSQL("Insert into users(uname, pass) VALUES('euthimios', 'alepis')");
        db.execSQL("Insert into movies(title, director, duration, genre, pdyear, rating) VALUES('Rush', 'Ron Howard', '123', 'Biography', '2013', '8.1')");
        db.execSQL("Insert into movies(title, director, duration, genre, pdyear, rating) VALUES('Ford v Ferrari', 'James Mangold', '152', 'Action', '2019', '8.1')");
        db.execSQL("Insert into movies(title, director, duration, genre, pdyear, rating) VALUES('Amulet', 'Romola Garai', '99', 'Horror', '2020', '5.4')");
        db.execSQL("Insert into movies(title, director, duration, genre, pdyear, rating) VALUES('Pretty Woman', 'Garry Marshall', '119', 'Comedy', '1990', '7.0')");
        db.execSQL("Insert into movies(title, director, duration, genre, pdyear, rating) VALUES('Bullit', 'Peter Yates', '116', 'Action', '1968', '7.4')");
        db.execSQL("Insert into movies(title, director, duration, genre, pdyear, rating) VALUES('The Dresser', 'Peter Yates', '118', 'Drama', '1983', '7.6')");
        db.execSQL("Insert into movies(title, director, duration, genre, pdyear, rating) VALUES('Breaking Away', 'Peter Yates', '111', 'Romance', '1979', '7.7')");
        db.execSQL("Insert into movies(title, director, duration, genre, pdyear, rating) VALUES('Apollo 13', 'Ron Howard', '140', 'Adventure', '1995', '7.6')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        db.execSQL("drop table if exists movies");
    }

    //insert users in database
    public boolean insertUser(String uname, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("uname",uname);
        contentValues.put("pass",pass);
        long ins = db.insert("users", null, contentValues);
        if(ins==-1) return false;
        else return true;
    }

    //change users' password
    public boolean changePass(String uname, String oldpass, String newpass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("uname",uname);
        contentValues.put("pass",newpass);
        long ins = db.update("users", contentValues, "uname=? and pass=?", new String[]{uname,oldpass});
        if(ins==-1) return false;
        else return true;
    }

    //Check for username duplicates
    public Boolean checkuname(String uname) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where uname=?", new String[]{uname});
        if (cursor.getCount()>0) return false;
        else return true;
    }

    //Check credentials' validity
    public Boolean validcreds(String uname,String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where uname=? and pass=?", new String[]{uname,pass});
        if(cursor.getCount()>0) return true;
        else return false;
    }

    //insert movies in database
    public boolean insertMovie(String title, String director, String duration, String genre, String pdyear, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("director",director);
        contentValues.put("duration",duration);
        contentValues.put("genre",genre);
        contentValues.put("pdyear",pdyear);
        contentValues.put("rating",rating);
        long ins = db.insert("movies", null, contentValues);
        if(ins==-1) return false;
        else return true;
    }

    //Check for movie title duplicates
    public Boolean checkmovtitle(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from movies where title=?", new String[]{title});
        if (cursor.getCount()>0) return false;
        else return true;
    }

    //Check for directors existence
    public Boolean checkdirector(String director) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from movies where director=?", new String[]{director});
        if (cursor.getCount()>0) return true;
        else return false;
    }

    //delete movies from database
    public boolean deletemovie(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        long ins = db.delete("movies", "title=?", new String[]{title});
        if(ins==-1) return false;
        else return true;
    }

    //Read a movie's entries
    public Cursor moviereader(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from movies where title=?", new String[]{title});
        return cursor;
    }

    //Reading movies with a specific director
    public Cursor searchdirector(String director) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from movies where director=?", new String[]{director});
        return cursor;
    }

    //Search movies with a part of the title
    public Cursor searchparttitle(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from movies where title LIKE '%"+title+"%'", null);
        return cursor;
    }

    //Update a movie entry
    public boolean updatemovie(String title, String director, String duration, String genre, String pdyear, String rating){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("director",director);
        contentValues.put("duration",duration);
        contentValues.put("genre",genre);
        contentValues.put("pdyear",pdyear);
        contentValues.put("rating",rating);
        long ins = db.update("movies", contentValues, "title=?", new String[]{title});
        if(ins==-1) return false;
        else return true;
    }

    //Get all of the movie titles
    public ArrayList getmovies(){
        ArrayList<String> movielist = new ArrayList<>(0);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select * from movies",null);
        try {
            if (cur.moveToFirst()){
                while (cur.isAfterLast()==false){
                    movielist.add(cur.getString(cur.getColumnIndex("title")));
                    cur.moveToNext();
                }
            }
            return movielist;
        }
        catch(Exception ex) {
            return movielist;
        }
    }
}
