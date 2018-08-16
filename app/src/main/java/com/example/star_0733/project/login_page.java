package com.example.star_0733.project;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class login_page extends AppCompatActivity {
    TextView  frgt,tc,regis,student,faculty;
    CardView btn_login;
    EditText login,password;
    FirebaseAuth auth;
    ProgressDialog dia;
    boolean wifi,data;
    Pref pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        student=(TextView)findViewById(R.id.st_login);
        faculty=(TextView)findViewById(R.id.fac_login);

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo networkInfo1 = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        wifi = networkInfo.isConnected();
        data = networkInfo1.isConnected();

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            auth.signOut();
        }
        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);

        regis = (TextView) findViewById(R.id.text_registration);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_page.this, Register.class);
                startActivity(intent);
            }
        });
        frgt = (TextView) findViewById(R.id.frgt);
        frgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_page.this, Forget.class);
                startActivity(intent);
            }
        });

        tc = (TextView) findViewById(R.id.tc);
        tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_page.this, tc.class);
                startActivity(intent);
            }
        });
        dia = new ProgressDialog(login_page.this);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dia.setMessage("Authenticating...");
                dia.show();
                String id = login.getText().toString();
                String pas = password.getText().toString();
                if(TextUtils.isEmpty(id)  || TextUtils.isEmpty(pas))
                {
                    dia.dismiss();
                    Toast.makeText(login_page.this, "Provide Email And Password", Toast.LENGTH_SHORT).show();
                }
                else if(!wifi && !data)
                {
                    dia.dismiss();
                    Toast.makeText(login_page.this, "No internet connection", Toast.LENGTH_SHORT).show();
                }
                else if(id.startsWith("faculty")){
                    dia.dismiss();
                    Toast.makeText(login_page.this, "You are Faculty\nPlease login in Faculty.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    auth.signInWithEmailAndPassword(id,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                dia.dismiss();
                                finish();
                                Intent intent = new Intent(login_page.this,Home.class);
                                startActivity(intent);
                            }
                            else
                            {
                                dia.dismiss();
                                Toast.makeText(login_page.this, "Invalid Credentials or Network Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

     });
        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dia.setMessage("Authenticating...");
                dia.show();
                String id = login.getText().toString();
                String pas = password.getText().toString();
                if(TextUtils.isEmpty(id)  || TextUtils.isEmpty(pas) )
                {
                    dia.dismiss();
                    Toast.makeText(login_page.this, "Provide Email And Password", Toast.LENGTH_SHORT).show();
                }
                else if(!wifi && !data)
                {
                    dia.dismiss();
                    Toast.makeText(login_page.this, "No internet connection", Toast.LENGTH_SHORT).show();
                }
                else if(!id.startsWith("faculty")){
                    dia.dismiss();
                    Toast.makeText(login_page.this, "Please sign in Student.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    auth.signInWithEmailAndPassword(id,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                dia.dismiss();
                                finish();
                                Intent intent = new Intent(login_page.this,faculty_home.class);
                                startActivity(intent);
                            }
                            else
                            {
                                dia.dismiss();
                                Toast.makeText(login_page.this, "Invalid Credentials or Network Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

     });
    }
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(login_page.this);
        builder.setTitle("Alert");
        builder.setMessage("do you want to Exit?");
        builder.setCancelable(false);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create();
        builder.show();
    }
}
