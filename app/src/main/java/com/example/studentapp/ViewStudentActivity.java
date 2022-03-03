package com.example.studentapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewStudentActivity extends AppCompatActivity {

Button button5 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        button5 = findViewById(R.id.button5) ;

        final ListView listview = findViewById(R.id.listview) ;

        StudentAppDatabase studentAppDatabase = new StudentAppDatabase(getApplicationContext()) ;

        SQLiteDatabase sqLiteDatabase = studentAppDatabase.open() ;

        ArrayList<String> allstudents = new ArrayList<String>() ;

        SimpleCursorAdapter simpleCursorAdapter = studentAppDatabase.readData(sqLiteDatabase, getApplicationContext()) ;

        if(simpleCursorAdapter!=null) {
            listview.setAdapter(simpleCursorAdapter);
        }
        else Toast.makeText(getApplicationContext(),"There are no students available",Toast.LENGTH_LONG).show();



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object student = listview.getItemAtPosition(i) ;

                Cursor cursor = (Cursor)student ;

                int id = cursor.getInt(cursor.getColumnIndex("_id")) ;
                String name = cursor.getString(cursor.getColumnIndex("NAME")) ;
                String surname = cursor.getString(cursor.getColumnIndex("SURNAME")) ;
                String bday = cursor.getString(cursor.getColumnIndex("BDAY")) ;
                String major = cursor.getString(cursor.getColumnIndex("MAJOR")) ;

                Intent intent = new Intent(ViewStudentActivity.this, ViewStudentInfoActivity.class) ;
                intent.putExtra("NAME", name);
                intent.putExtra("SURNAME", surname);
                intent.putExtra("BDAY", bday);
                intent.putExtra("MAJOR", major);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewStudentActivity.this, MainActivity2.class) ;
                startActivity(intent);
            }
        });
    }
}