package com.example.star_0733.project;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sumit on 28-03-2018.
 */

public class NoticeAdapter extends ArrayAdapter<NoticeUpload>{

    Activity context;
    List<NoticeUpload> list;

    public NoticeAdapter(@NonNull Activity context, @NonNull List<NoticeUpload> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listview = LayoutInflater.from(getContext()).inflate(R.layout.doc_list,null);
        NoticeUpload noticeUpload = getItem(position);

        TextView name = (TextView)listview.findViewById(R.id.doc_name);
        TextView fac = (TextView)listview.findViewById(R.id.fac_name);
        TextView time = (TextView)listview.findViewById(R.id.doc_time);

        name.setText(noticeUpload.getName());
        fac.setText(noticeUpload.getFac_name());
        time.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", noticeUpload.getMessageTime()));


        return listview;
    }
}
