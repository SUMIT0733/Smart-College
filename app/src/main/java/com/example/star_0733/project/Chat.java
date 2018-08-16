package com.example.star_0733.project;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity {
    LinearLayout pachat;
    TextView messageUser,messageText,time;
    FirebaseAuth auth;
    DatabaseReference ref;
    FirebaseUser user;
    ListView lst;
    ProgressDialog dia;
    ImageButton send;
    List<Chat_model> list;
    ChatAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        auth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("chat");
        user = auth.getCurrentUser();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chat");

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo networkInfo1 = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean wifi = networkInfo.isConnected();
        boolean data = networkInfo1.isConnected();
        if(!wifi && !data)
        {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        }

        dia = new ProgressDialog(Chat.this);
        dia.setMessage("Message Loading...");
        dia.show();
        send=(ImageButton)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                EditText input=(EditText)findViewById(R.id.msg);
                    ref.push().setValue(new Chat_model(input.getText().toString(),
                            user.getEmail()));
                    input.setText("");
                    displayChatMessages();
                            }
        });
        list = new ArrayList<>();
        displayChatMessages();
    }
    private void displayChatMessages() {
        lst = (ListView) findViewById(R.id.lst);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                dia.dismiss();
                for (DataSnapshot chat : dataSnapshot.getChildren()) {
                    Chat_model info = chat.getValue(Chat_model.class);
                    list.add(info);
                }
                adapter = new ChatAdapter(Chat.this, list, user.getEmail());
                lst.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(Chat.this,Home.class);
        startActivity(intent);
    }
}
