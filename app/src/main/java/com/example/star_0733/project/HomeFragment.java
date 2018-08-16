package com.example.star_0733.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by sumit on 13-03-2018.
 */

public class HomeFragment extends Fragment {
    CardView edu,chat,notice;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View listview = inflater.inflate(R.layout.homefra,container,false);
        edu = (CardView)listview.findViewById(R.id.educational);
        chat = (CardView)listview.findViewById(R.id.chat);
        notice = (CardView)listview.findViewById(R.id.notice);
        return listview;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");
        edu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                Intent intent = new Intent(getContext(),Branch.class);
                startActivity(intent);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                Intent intent = new Intent(getContext(),Chat.class);
                startActivity(intent);
            }
        });
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                Intent intent = new Intent(getContext(),doc_notice.class);
                startActivity(intent);
            }
        });
    }
}
