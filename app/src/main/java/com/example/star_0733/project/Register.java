package com.example.star_0733.project;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    EditText en,email,pass;
    TextInputEditText pass1;
    CardView reg;
    FirebaseAuth auth;
    DatabaseReference ref;
    ProgressDialog dia;
    ProgressBar p_bar;
    boolean data,wifi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo networkInfo1 = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        wifi = networkInfo.isConnected();
        data = networkInfo1.isConnected();

        auth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("enroll");
        dia = new ProgressDialog(Register.this);
        dia.setCancelable(false);
        dia.setMessage("Registering...");
        p_bar = (ProgressBar)findViewById(R.id.progress_bar);
        p_bar.setVisibility(View.GONE);

        en = (EditText) findViewById(R.id.reg_en);
        email= (EditText) findViewById(R.id.login);
        //pass = (EditText) findViewById(R.id.password);
        pass1 = (TextInputEditText) findViewById(R.id.pass1);
        reg = (CardView) findViewById(R.id.btn_registration);
        //
        // Toast.makeText(this, pass1.getText().toString(), Toast.LENGTH_SHORT).show();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dia.show();
                p_bar.setVisibility(View.VISIBLE);
                reg.setVisibility(View.GONE);
                final String enroll = en.getText().toString();
                final String id = email.getText().toString();
                final String pas = pass1.getText().toString();


                if(TextUtils.isEmpty(id)  || TextUtils.isEmpty(pas) || TextUtils.isEmpty(enroll) || enroll.length()!= 12)
                {
                    p_bar.setVisibility(View.GONE);
                    reg.setVisibility(View.VISIBLE);
                    //dia.dismiss();
                    Toast.makeText(Register.this, "Please Fill up all Detail", Toast.LENGTH_SHORT).show();
                }
                else if(!wifi && !data)
                {
                    Toast.makeText(Register.this, "No internet connection", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    auth.createUserWithEmailAndPassword(id,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                p_bar.setVisibility(View.VISIBLE);
                                reg.setVisibility(View.GONE);
                                auth.signInWithEmailAndPassword(id,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            En_model en_model = new En_model(enroll,id);
                                            FirebaseUser user = auth.getCurrentUser();
                                            ref.child(user.getUid()).setValue(en_model);
                                            Toast.makeText(Register.this, "Successfully Registered...", Toast.LENGTH_SHORT).show();
                                            //dia.dismiss();
                                            finish();
                                            Intent intent = new Intent(Register.this,Home.class);
                                            startActivity(intent);
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        p_bar.setVisibility(View.GONE);
                                        reg.setVisibility(View.VISIBLE);
                                        Toast.makeText(Register.this, e.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else
                            {
                                p_bar.setVisibility(View.GONE);
                                reg.setVisibility(View.VISIBLE);
                                Toast.makeText(Register.this, "Registration error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
