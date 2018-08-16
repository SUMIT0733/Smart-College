package com.example.star_0733.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Upload_document extends AppCompatActivity {
    private static final int PDF = 234;
    EditText fac_name,filename;
    Spinner type,branch,sem;
    CardView upload;
    ProgressDialog dia;
    StorageReference ref;
    DatabaseReference mdatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_document);

        dia = new ProgressDialog(Upload_document.this);
        ref = FirebaseStorage.getInstance().getReference("document");
       // mdatabase = FirebaseDatabase.getInstance().getReference("notice");

        fac_name = (EditText) findViewById(R.id.up_name);
        filename = (EditText)findViewById(R.id.up_filename);
        branch = (Spinner)findViewById(R.id.up_branch);
        sem = (Spinner)findViewById(R.id.up_sem);

        type = (Spinner) findViewById(R.id.up_type);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
       @Override
       public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
           if (type.getSelectedItem().equals("GENERAL NOTICE")) {
               mdatabase = FirebaseDatabase.getInstance().getReference("notice");
               branch.setEnabled(false);
               sem.setEnabled(false);
               //Toast.makeText(Upload_document.this, "Toast is : " + type.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
           }
           else if(type.getSelectedItem().equals("EDUCATIONAL"))
           {
               mdatabase = FirebaseDatabase.getInstance().getReference("education");
               branch.setEnabled(true);
               sem.setEnabled(true);
               //Toast.makeText(Upload_document.this, "Toast is : " + type.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
           }
       }

       @Override
       public void onNothingSelected(AdapterView<?> adapterView) {
           Toast.makeText(Upload_document.this, "Please Select The Item...", Toast.LENGTH_SHORT).show();
       }
   });

        upload = (CardView)findViewById(R.id.btn_upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filename != null || fac_name!=null)
                {
                    Toast.makeText(Upload_document.this, "Provide File name and faculty name", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent();
                    intent.setType("pdf/*");
                    intent.setAction(intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "select PDF"), PDF);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PDF && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            dia.setMessage("uploading...");
            Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();
            dia.show();


            if (filename != null || fac_name!=null) {
                ref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        dia.dismiss();
                        Toast.makeText(Upload_document.this, "uploaded", Toast.LENGTH_SHORT).show();
                        NoticeUpload noticeUpload = new NoticeUpload(filename.getText().toString().trim(), taskSnapshot.getDownloadUrl().toString(), fac_name.getText().toString());
                        if (type.getSelectedItem().equals("GENERAL NOTICE")) {
                            mdatabase.push().setValue(noticeUpload);
                            Toast.makeText(Upload_document.this, "Upload To the Database", Toast.LENGTH_SHORT).show();
                            fac_name.setText("");
                            filename.setText("");
                        } else if (type.getSelectedItem().equals("EDUCATIONAL")) {
                            mdatabase.child(branch.getSelectedItem().toString()).child(sem.getSelectedItem().toString()).push().setValue(noticeUpload);
                            Toast.makeText(Upload_document.this, "Upload To the Database", Toast.LENGTH_SHORT).show();
                            fac_name.setText("");
                            filename.setText("");
                        }

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double pro = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        dia.setMessage((int) pro + "% uploaded");
                    }
                });
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this,faculty_home.class));
    }
}
