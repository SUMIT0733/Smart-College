package com.example.star_0733.project;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * Created by sumit on 12-03-2018.
 */

public class DownloadFragment extends Fragment {
    ProgressDialog dia;
    List<Dwn_model> list;
    DatabaseReference ref;
    ListView lst;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View listview = inflater.inflate(R.layout.download, container, false);
        getActivity().setTitle("Downloads");

        dia = new ProgressDialog(getActivity());
        dia.setMessage("please wait...");
        dia.show();

        lst = (ListView)listview.findViewById(R.id.lst);
        list = new ArrayList<>();

        ref = FirebaseDatabase.getInstance().getReference("profile").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("download");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren())
                {
                    //Toast.makeText(getActivity(), (int) data.getChildrenCount(), Toast.LENGTH_SHORT).show();
                    Dwn_model dwn_model = data.getValue(Dwn_model.class);
                    list.add(dwn_model);
                }

                Dwn_Adapter adapter = new Dwn_Adapter(getActivity(),list);
                lst.setAdapter(adapter);
                dia.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dia.dismiss();
            }
        });
        return listview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Download");
    }

}
