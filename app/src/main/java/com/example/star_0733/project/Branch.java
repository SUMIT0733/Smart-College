package com.example.star_0733.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.security.cert.CertPathValidatorException;

public class Branch extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    CardView it,cp,mech,civil,ele,ec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
        it = (CardView)findViewById(R.id.it);
        cp = (CardView)findViewById(R.id.cp);
        mech = (CardView)findViewById(R.id.mech);
        civil = (CardView)findViewById(R.id.civil);
        ele = (CardView)findViewById(R.id.ele);
        ec = (CardView)findViewById(R.id.ec);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Branch");

        floatingActionButton = (FloatingActionButton)findViewById(R.id.float_home);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Branch.this,Home.class);
                startActivity(intent);
            }
        });
        it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Branch.this,Semester.class);
                intent.putExtra("branch","IT");
                startActivity(intent);
            }
        });
        cp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Branch.this,Semester.class);
                intent.putExtra("branch","COMPUTER");
                startActivity(intent);
            }
        });
        mech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Branch.this,Semester.class);
                intent.putExtra("branch","MECHANICAL");
                startActivity(intent);
            }
        });
        civil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Branch.this,Semester.class);
                intent.putExtra("branch","CIVIL");
                startActivity(intent);
            }
        });
        ele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Branch.this,Semester.class);
                intent.putExtra("branch","ELECTRICAL");
                startActivity(intent);
            }
        });
        ec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Branch.this,Semester.class);
                intent.putExtra("branch","EC");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(Branch.this,Home.class);
        startActivity(intent);
    }
}
