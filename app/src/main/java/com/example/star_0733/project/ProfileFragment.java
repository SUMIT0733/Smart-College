package com.example.star_0733.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * Created by sumit on 12-03-2018.
 */

public class ProfileFragment extends Fragment {
    TextView en_number;
    EditText name, email, mob;
    Spinner branch, sem;
    int bid,sid;
    CardView sub;
    FirebaseAuth auth;
    DatabaseReference ref;
    String db_email;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View listview = inflater.inflate(R.layout.profile, container, false);
        getActivity().setTitle("Profile");

        auth = FirebaseAuth.getInstance();

        en_number = (TextView) listview.findViewById(R.id.st_en_number);
        name = (EditText) listview.findViewById(R.id.st_name);
        name.setText("");
        email = (EditText) listview.findViewById(R.id.st_email);
        email.setEnabled(false);
        mob = (EditText) listview.findViewById(R.id.st_mobile);
        mob.setText("");

        branch = (Spinner) listview.findViewById(R.id.st_branch);
        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bid = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        sem = (Spinner) listview.findViewById(R.id.st_sem);
        sem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sid = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        sub = (CardView) listview.findViewById(R.id.btn_submit);

        //initialize the enroll no and email from register
        ref = FirebaseDatabase.getInstance().getReference("enroll");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                En_model en_model = dataSnapshot.child(auth.getCurrentUser().getUid()).getValue(En_model.class);
                en_number.setText(en_model.getEnroll());
                db_email = en_model.getEmail();
                email.setText(en_model.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        //if profile is available then show it to the student.
        ref = FirebaseDatabase.getInstance().getReference("profile");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Profile_model profile_model= dataSnapshot.child(auth.getCurrentUser().getUid()).getValue(Profile_model.class);
                if(profile_model == null) {
                    Toast.makeText(getContext(), "Profile Not available...please provide", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    name.setText(profile_model.getName());
                    mob.setText(profile_model.getMob());
                    branch.setSelection(profile_model.getBid());
                    sem.setSelection(profile_model.getSid());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        return listview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //when student change the profile

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref = FirebaseDatabase.getInstance().getReference("profile");
                Profile_model profile_model = new Profile_model(name.getText().toString(),mob.getText().toString(),bid,sid,en_number.getText().toString(),db_email);
                FirebaseUser user = auth.getCurrentUser();
                ref.child(user.getUid()).setValue(profile_model);
                Toast.makeText(getActivity(), "Profile Update Successfully", Toast.LENGTH_SHORT).show();

//                new SweetAlertDialog(getActivity())
//                        .setTitleText("Here's a message!")
//                        .show();
                //Toast.makeText(getContext(), "Profile Update Successfully", Toast.LENGTH_LONG).show();
            }
        });
    }
}
