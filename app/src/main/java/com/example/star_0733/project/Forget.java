package com.example.star_0733.project;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forget extends AppCompatActivity {
    EditText email;
    CardView btn;
    FirebaseAuth auth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        progressBar = (ProgressBar)findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);
        email = (EditText)findViewById(R.id.frgt_email);
        btn = (CardView) findViewById(R.id.frgt_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(email.getText().toString()))
                {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Forget.this, "Provide Email", Toast.LENGTH_SHORT).show();
                }
                else{
                    AlertDialog.Builder bui = new AlertDialog.Builder(Forget.this);
                    bui.setCancelable(false);
                    bui.setTitle("Confirm Reset");
                    bui.setMessage(Html.fromHtml("Password reset link is send to the "+"<b>"+email.getText().toString()+"</b>\n, Please check your inbox.."));

                   // bui.setMessage("Password varification link is send to the "+email.getText().toString()+ "\n, Please check your inbox..");
                    bui.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            progressBar.setVisibility(View.VISIBLE);
                            auth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        progressBar.setVisibility(View.GONE);
                                        auth.signOut();
                                        finish();
                                        Intent intent = new Intent(Forget.this,login_page.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }
                    });
                    bui.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                    bui.show();
                }
            }

        });
    }
}
