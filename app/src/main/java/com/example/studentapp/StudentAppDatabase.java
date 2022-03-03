package com.example.studentapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class StudentAppDatabase extends SQLiteOpenHelper {
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE STUDENTS ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "SURNAME TEXT, "
                + "BDAY TEXT, "
                + "MAJOR TEXT);");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public StudentAppDatabase(Context context) {

        super(context.getApplicationContext(), "StudentManagement", null, 1);


    }

    public SQLiteDatabase open()  {

        SQLiteDatabase sqLiteDatabase=null;
        try {
            sqLiteDatabase = getWritableDatabase();


            Log.v("Sql", "opened");
            onCreate(sqLiteDatabase);
            return(sqLiteDatabase);


        } catch (Exception ex)
        {
            return(sqLiteDatabase);
        }

    }


    public void insertStudent(SQLiteDatabase sqLiteDatabase,
                           String name, String surname, String bday,
                           String major) {
        ContentValues studentValues = new ContentValues();
        studentValues.put("NAME", name);
        studentValues.put("SURNAME", surname);
        studentValues.put("BDAY", bday);
        studentValues.put("MAJOR", major);

        sqLiteDatabase.insert("STUDENTS", null, studentValues);
        Log.v("sql","Data is added");
    }



    public SimpleCursorAdapter readData(SQLiteDatabase sqLiteDatabase, Context context) {
        SimpleCursorAdapter simpleCursorAdapter=null;

        ArrayList<String>  allstudents=new ArrayList<String>();

        Cursor cursor = sqLiteDatabase.query("STUDENTS", new String[]{"_id", "NAME",  "SURNAME", "BDAY", "MAJOR"}, null, null, null, null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                allstudents.add(cursor.getString(cursor.getColumnIndex("NAME")));

                cursor.moveToNext();
            }
            simpleCursorAdapter = new SimpleCursorAdapter(context.getApplicationContext(), R.layout.adapterlayout, cursor, new String[]{"NAME", "SURNAME", "BDAY", "MAJOR","_id"}, new int[]{R.id.txt1,R.id.txt2, R.id.txt3, R.id.txt4, R.id.txt5}, 0);

        }  catch(Exception ex)
        {
            Log.v("sql", " There is a Problem");
        }

        return  simpleCursorAdapter;
    }

    public void deleteStudent(int id) {

        SQLiteDatabase sqLiteDatabase = open();
        sqLiteDatabase.delete("STUDENTS", "_id=" + id,null);

    }

    public void updateSTUDENT(int id, String name, String surname, String bday, String major) {
        ContentValues data = new ContentValues();
        data.put("NAME", name);
        data.put("SURNAME", surname);
        data.put("BDAY", bday);
        data.put("MAJOR", major);

        SQLiteDatabase sqLiteDatabase = open();

        sqLiteDatabase.update("STUDENTS", data,"_id=" + id,null);

    }

}
