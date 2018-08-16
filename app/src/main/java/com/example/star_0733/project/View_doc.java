package com.example.star_0733.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class View_doc extends AppCompatActivity {
    String branch,sem;
    ListView lst;
    List<NoticeUpload> list;
    DatabaseReference ref;
    ProgressDialog dia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doc);

        branch = getIntent().getStringExtra("Branch");
        sem = getIntent().getStringExtra("Sem");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(branch+" / "+sem);
        //Toast.makeText(this, branch+" / "+sem, Toast.LENGTH_SHORT).show();
        dia = new ProgressDialog(View_doc.this);
        dia.setMessage("please wait...");
        dia.show();

        lst = (ListView)findViewById(R.id.lst);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ref = FirebaseDatabase.getInstance().getReference("profile").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("download");
                NoticeUpload noticeUpload = list.get(i);
                Dwn_model dwn_model = new Dwn_model("Education",noticeUpload.getName(),noticeUpload.getFac_name());
                ref.push().setValue(dwn_model);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(noticeUpload.getUri()));
                startActivity(intent);
            }
        });

        list = new ArrayList<>();
        list.clear();
        ref = FirebaseDatabase.getInstance().getReference("education").child(branch).child(sem);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren())
                {
                    NoticeUpload noticeUpload = data.getValue(NoticeUpload.class);
                    list.add(noticeUpload);
                }
                NoticeAdapter adapter = new NoticeAdapter(View_doc.this,list);
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
        Intent intent = new Intent(View_doc.this,Semester.class);
        intent.putExtra("branch",branch);
        startActivity(intent);
    }
}
