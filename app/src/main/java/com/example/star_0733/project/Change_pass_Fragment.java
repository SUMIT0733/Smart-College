package com.example.star_0733.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sumit on 12-03-2018.
 */
public class Change_pass_Fragment extends Fragment {
    EditText old_pass,new_pass,renew_pass;
    CardView save;
    TextView frgt;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View listview = inflater.inflate(R.layout.change_pass,container,false);
        old_pass = (EditText) listview.findViewById(R.id.old_pass);
        new_pass = (EditText) listview.findViewById(R.id.new_pass);
        renew_pass = (EditText) listview.findViewById(R.id.renew_pass);
        save = (CardView)listview.findViewById(R.id.btn_change_password);
        frgt = (TextView)listview.findViewById(R.id.change_frgt);
        frgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),Forget.class);
                startActivity(intent);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpass = old_pass.getText().toString();
                String newpass = new_pass.getText().toString();
                String renewpass = renew_pass.getText().toString();
                if(newpass.equals(renewpass))
                {
                    Toast.makeText(getContext(), "old pass: "+oldpass +"\nnew pass : "+newpass, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "new and renew password doesn't match", Toast.LENGTH_SHORT).show();
                }
                old_pass.setText("");
                new_pass.setText("");
                renew_pass.setText("");
            }
        });

        return listview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Change Password");
    }
}
