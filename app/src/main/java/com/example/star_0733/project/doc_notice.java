package com.example.star_0733.project;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class doc_notice extends AppCompatActivity {
    ListView lst;
    List<NoticeUpload> list;
    DatabaseReference ref;
    ProgressDialog dia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_notice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notice");

        dia = new ProgressDialog(doc_notice.this);
        dia.setMessage("please wait...");
        dia.show();



        lst = (ListView)findViewById(R.id.lst);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                NoticeUpload noticeUpload = list.get(i);
                ref = FirebaseDatabase.getInstance().getReference("profile").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("download");
                Dwn_model dwn_model = new Dwn_model("Notice",noticeUpload.getName(),noticeUpload.getFac_name());
                ref.push().setValue(dwn_model);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(noticeUpload.getUri()));
                startActivity(intent);

            }
        });

        list = new ArrayList<>();

        ref = FirebaseDatabase.getInstance().getReference("notice");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren())
                {
                    NoticeUpload noticeUpload = data.getValue(NoticeUpload.class);
                    list.add(noticeUpload);
                }
                NoticeAdapter adapter = new NoticeAdapter(doc_notice.this,list);
                lst.setAdapter(adapter);
                dia.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dia.dismiss();
            }
        });


    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(doc_notice.this,Home.class);
        startActivity(intent);
    }
}



