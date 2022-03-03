package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    Button viewstudents, addstudent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        viewstudents = findViewById(R.id.viewstudents) ;
        addstudent = findViewById(R.id.addstudent) ;

        viewstudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, ViewStudentActivity.class) ;
                startActivity(intent);

            }
        });

         addstudent.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(MainActivity2.this, AddStudentActivity.class) ;
                 startActivity(intent);
             }
         });
    }
}