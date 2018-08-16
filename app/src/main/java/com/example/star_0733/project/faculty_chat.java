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

public class faculty_chat extends AppCompatActivity {
    List<Chat_model> list;
    ChatAdapter adapter;
    FirebaseAuth auth;
    DatabaseReference ref;
    TextView messageUser,messageText,time;
    LinearLayout pachat;
    FirebaseUser user;
    ListView lst;
    ProgressDialog dia;
    ImageButton send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_chat);
        auth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("chat");
        user = auth.getCurrentUser();

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo networkInfo1 = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean wifi = networkInfo.isConnected();
        boolean data = networkInfo1.isConnected();
        if(!wifi && !data)
        {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chat");

        list = new ArrayList<>();

        dia = new ProgressDialog(faculty_chat.this);
        dia.setMessage("Message Loading...");
        dia.show();
        send=(ImageButton)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                EditText input=(EditText)findViewById(R.id.msg);
                ref.push().setValue(new Chat_model(input.getText().toString(),
                        user.getEmail()));
                input.setText("");
            }
        });
        displayChatMessages();
    }

    private void displayChatMessages() {
        lst = (ListView) findViewById(R.id.lst);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dia.dismiss();
                for (DataSnapshot chat : dataSnapshot.getChildren()) {
                    Chat_model info = chat.getValue(Chat_model.class);
                    list.add(info);
                }
                adapter = new ChatAdapter(faculty_chat.this, list, user.getEmail());
                lst.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
//    private void displayChatMessages() {
//        lst = (ListView)findViewById(R.id.lst);
//
//        adapter = new FirebaseListAdapter<Chat_model>(this, Chat_model.class, R.layout.chat_list_row, ref) {
//            @Override
//            protected void populateView(View v, Chat_model model, int position) {
//                // Get references to the views of message.xml
//                dia.dismiss();
//
//                pachat = (LinearLayout)v.findViewById(R.id.parentchatback);
//                messageText = (TextView)v.findViewById(R.id.message_text);
//                messageUser = (TextView)v.findViewById(R.id.message_user);
//                //time = (TextView)v.findViewById(R.id.message_time);
//                if(model.getMessageUser().equals(user.getEmail()))
//                {
//
//                    pachat.setGravity(Gravity.RIGHT);
//                    messageUser.setText(null);
//                    messageUser.setVisibility(View.GONE);
//                    messageText.setBackgroundResource(R.drawable.se_back);
//                    messageText.setText(model.getMessageText());
//                    messageText.setGravity(Gravity.RIGHT);
//                    time.setVisibility(View.GONE);
//                    //time.setGravity(Gravity.LEFT);
//                    //time.setText(DateFormat.format("dd-MM-yyyy",model.getMessageTime()));
//                }
//                else
//                {
//                    pachat.setGravity(Gravity.LEFT);
//                    messageText.setBackgroundResource(R.drawable.back);
//                    messageUser.setText(model.getMessageUser());
//                    messageText.setText(model.getMessageText());
//                    messageText.setGravity(Gravity.LEFT);
//                    time.setVisibility(View.GONE);
//                    //time.setText(DateFormat.format("dd-MM-yyyy",model.getMessageTime()));
//                }
//            }
//        };
//
//        lst.setAdapter(adapter);
//    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(faculty_chat.this,faculty_home.class);
        startActivity(intent);
    }
}
