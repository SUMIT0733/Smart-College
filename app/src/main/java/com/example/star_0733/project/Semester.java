package com.example.star_0733.project;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Semester extends AppCompatActivity {
    FloatingActionButton float_home;
    String branch="",sem="";
    CardView sem1,sem2,sem3,sem4,sem5,sem6,sem7,sem8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester);
        branch = getIntent().getStringExtra("branch");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(branch);

        float_home = (FloatingActionButton)findViewById(R.id.float_home);
        float_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Semester.this,Home.class);
                startActivity(intent);
            }
        });

        sem1 = (CardView)findViewById(R.id.sem1);
        sem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sem = "SEMESTER 1";
                finish();
                Intent intent = new Intent(Semester.this,View_doc.class);
                intent.putExtra("Branch",branch);
                intent.putExtra("Sem",sem);
                startActivity(intent);
            }
        });
        sem2 = (CardView)findViewById(R.id.sem2);
        sem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sem = "SEMESTER 2";
                finish();
                Intent intent = new Intent(Semester.this,View_doc.class);
                intent.putExtra("Branch",branch);
                intent.putExtra("Sem",sem);
                startActivity(intent);
            }
        });
        sem3 = (CardView)findViewById(R.id.sem3);
        sem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sem = "SEMESTER 3";
                finish();
                Intent intent = new Intent(Semester.this,View_doc.class);
                intent.putExtra("Branch",branch);
                intent.putExtra("Sem",sem);
                startActivity(intent);
            }
        });
        sem4 = (CardView)findViewById(R.id.sem4);
        sem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sem = "SEMESTER 4";
                finish();
                Intent intent = new Intent(Semester.this,View_doc.class);
                intent.putExtra("Branch",branch);
                intent.putExtra("Sem",sem);
                startActivity(intent);
            }
        });
        sem5 = (CardView)findViewById(R.id.sem5);
        sem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sem = "SEMESTER 5";
                finish();
                Intent intent = new Intent(Semester.this,View_doc.class);
                intent.putExtra("Branch",branch);
                intent.putExtra("Sem",sem);
                startActivity(intent);
            }
        });
        sem6 = (CardView)findViewById(R.id.sem6);
        sem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sem = "SEMESTER 6";
                finish();
                Intent intent = new Intent(Semester.this,View_doc.class);
                intent.putExtra("Branch",branch);
                intent.putExtra("Sem",sem);
                startActivity(intent);
            }
        });
        sem7 = (CardView)findViewById(R.id.sem7);
        sem7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sem = "SEMESTER 7";
                finish();
                Intent intent = new Intent(Semester.this,View_doc.class);
                intent.putExtra("Branch",branch);
                intent.putExtra("Sem",sem);
                startActivity(intent);
            }
        });
        sem8 = (CardView)findViewById(R.id.sem8);
        sem8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sem = "SEMESTER 8";
                finish();
                Intent intent = new Intent(Semester.this,View_doc.class);
                intent.putExtra("Branch",branch);
                intent.putExtra("Sem",sem);
                startActivity(intent);
            }
        });



    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(Semester.this,Branch.class);
        startActivity(intent);
    }
}
