package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewStudentInfoActivity extends AppCompatActivity {

    TextView name, surname, bday, major ;
    Button deletestudent, updatestudent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_info);

        name = findViewById(R.id.name) ;
        surname = findViewById(R.id.surname) ;
        bday = findViewById(R.id.bday) ;
        major = findViewById(R.id.major) ;
        deletestudent = findViewById(R.id.deletestudent) ;
        updatestudent = findViewById(R.id.updatestudent) ;

        final String studentName = getIntent().getExtras().getString("NAME") ;
        final String studentSurname = getIntent().getExtras().getString("SURNAME") ;
        final String studentBDay = getIntent().getExtras().getString("BDAY") ;
        final String studentMajor = getIntent().getExtras().getString("MAJOR") ;
        final int studentid = getIntent().getExtras().getInt("id");

        name.setText(studentName) ;
        surname.setText(studentSurname) ;
        bday.setText(studentBDay) ;
        major.setText(studentMajor) ;

        deletestudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentAppDatabase studentAppDatabase = new StudentAppDatabase(getApplicationContext()) ;
                studentAppDatabase.deleteStudent(studentid);
                Intent intent = new Intent(ViewStudentInfoActivity.this, ViewStudentActivity.class) ;
                startActivity(intent) ;

            }
        });

        updatestudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ViewStudentInfoActivity.this, UpdateStudentActivity.class) ;
                intent.putExtra("NAME", studentName) ;
                intent.putExtra("SURNAME", studentSurname) ;
                intent.putExtra("BDAY", studentBDay) ;
                intent.putExtra("MAJOR", studentMajor) ;
                intent.putExtra("id", studentid);
                startActivity(intent) ;
            }
        });

    }
}