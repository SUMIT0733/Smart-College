package com.example.star_0733.project;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Wel_come extends AppCompatActivity {
    public static final int TIME = 2500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel_come);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(Wel_come.this,login_page.class);
                startActivity(intent);
                finish();
            }
        },TIME);
    }
}
