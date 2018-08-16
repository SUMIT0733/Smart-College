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

public class Dwn_Adapter extends ArrayAdapter<Dwn_model>{

    Activity context;
    List<Dwn_model> list;

    public Dwn_Adapter(@NonNull Activity context, @NonNull List<Dwn_model> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listview = LayoutInflater.from(getContext()).inflate(R.layout.dwn_list_raw,null);
        Dwn_model dwn_model = getItem(position);

        TextView name = (TextView)listview.findViewById(R.id.doc_name);
        TextView fac = (TextView)listview.findViewById(R.id.fac_name);
        TextView time = (TextView)listview.findViewById(R.id.doc_time);
        TextView type = (TextView)listview.findViewById(R.id.doc_type);

        name.setText(dwn_model.getName());
        type.setText("(" +dwn_model.getType()+" )");
        fac.setText(dwn_model.getFac_name());
        time.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", dwn_model.getMessageTime()));


        return listview;
    }
}
