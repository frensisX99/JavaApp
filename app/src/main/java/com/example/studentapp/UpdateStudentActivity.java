package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateStudentActivity extends AppCompatActivity {

EditText upname, upsurname, upbday, upmajor ;
Button upstudent ;
String upstudentname, upstudentsurname, upstudentbday, upstudentmajor ;
int upstudentid ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        upname = findViewById(R.id.upname) ;
        upsurname = findViewById(R.id.upsurname) ;
        upbday = findViewById(R.id.upbday) ;
        upmajor = findViewById(R.id.upmajor) ;
        upstudent = findViewById(R.id.upstudent) ;

        upstudentname = getIntent().getExtras().getString("NAME") ;
        upstudentsurname = getIntent().getExtras().getString("SURNAME") ;
        upstudentbday = getIntent().getExtras().getString("BDAY") ;
        upstudentmajor = getIntent().getExtras().getString("MAJOR") ;
        upstudentid = getIntent().getExtras().getInt("id") ;

        upname.setText(upstudentname) ;
        upsurname.setText(upstudentsurname) ;
        upbday.setText(upstudentbday) ;
        upmajor.setText(upstudentmajor) ;

        final StudentAppDatabase studentAppDatabase = new StudentAppDatabase(getApplicationContext()) ;

        upstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(upname.getText()) || TextUtils.isEmpty(upsurname.getText()) || TextUtils.isEmpty(upbday.getText()) || TextUtils.isEmpty(upmajor.getText()))
                {
                    Toast.makeText(getApplicationContext(), "Please update all the fields", Toast.LENGTH_SHORT).show();
                }

                else {
                    upstudentname = upname.getText().toString();
                    upstudentsurname = upsurname.getText().toString();
                    upstudentbday = upbday.getText().toString();
                    upstudentmajor = upmajor.getText().toString();

                    studentAppDatabase.updateSTUDENT(upstudentid, upstudentname, upstudentsurname, upstudentbday, upstudentmajor);

                    Intent intent = new Intent(UpdateStudentActivity.this, ViewStudentActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}