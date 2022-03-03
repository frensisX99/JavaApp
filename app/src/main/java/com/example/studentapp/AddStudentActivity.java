package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddStudentActivity extends AppCompatActivity {

    EditText addname, addsurname, addbday, addmajor ;
    Button addst1 ;
    String addname1, addsurname1, addbday1, addmajor1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        addname = findViewById(R.id.addname) ;
        addsurname = findViewById(R.id.addsurname) ;
        addbday = findViewById(R.id.addbday) ;
        addmajor = findViewById(R.id.addmajor) ;
        addst1 = findViewById(R.id.adds1) ;

        final StudentAppDatabase studentAppDatabase = new StudentAppDatabase(getApplicationContext()) ;

        addst1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(addname.getText()) || TextUtils.isEmpty(addsurname.getText()) || TextUtils.isEmpty(addbday.getText()) || TextUtils.isEmpty(addmajor.getText()))
                {
                    Toast.makeText(getApplicationContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else {

                    addname1 = addname.getText().toString();
                    addsurname1 = addsurname.getText().toString();
                    addbday1 = addbday.getText().toString();
                    addmajor1 = addmajor.getText().toString();

                    studentAppDatabase.insertStudent(studentAppDatabase.open(), addname1, addsurname1, addbday1, addmajor1);
                    Intent intent = new Intent(AddStudentActivity.this, ViewStudentActivity.class);

                    startActivity(intent);
                     }

            }
        });




    }
}