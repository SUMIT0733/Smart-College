package com.example.star_0733.project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by sumit on 12-03-2018.
 */

public class About_usFragment extends android.support.v4.app.Fragment {
    ImageButton btn;
    BottomNavigationView btm_nav;
    Fragment fragment = null;
    Class fragclass = null;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_us,container,false);
        btm_nav = (BottomNavigationView)view.findViewById(R.id.btm_nav);
        getFragmentManager().beginTransaction().replace(R.id.abt_frame,new About_sumit()).commit();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("About Us");

        btm_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.sumit:
                        fragclass = About_sumit.class;
                        break;
                    case R.id.hardik:
                        fragclass = About_hardik.class;
                        break;
                    case R.id.sanket:
                        fragclass = About_sanket.class;
                        break;
                    case R.id.vivek:
                        fragclass = About_vivek.class;
                        break;
                }
                if(fragclass!=null)
                {
                    try {
                        fragment = (Fragment)fragclass.newInstance();
                    } catch (java.lang.InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                if(fragment!=null)
                {
                    getFragmentManager().beginTransaction().replace(R.id.abt_frame,fragment).commit();
                }
                return true;
            }
        });
    }
}
