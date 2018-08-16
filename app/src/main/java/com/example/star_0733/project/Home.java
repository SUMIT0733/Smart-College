package com.example.star_0733.project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        Fragment fragment = null;
        Class fragmentClass = null;
        FirebaseAuth auth;
        DatabaseReference ref;
        TextView en;
        FirebaseUser user;
        alert obalert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        en = (TextView)view.findViewById(R.id.en);
        ref = FirebaseDatabase.getInstance().getReference("enroll");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 En_model en_model = dataSnapshot.child(user.getUid()).getValue(En_model.class);
                assert en_model != null;
                String in = en_model.getEnroll();
                en.setText(in);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        loadHomeFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
            builder.setTitle("Alert");
            builder.setMessage("do you want to Logout?");
            builder.setCancelable(false);
            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    auth.signOut();
                    finish();
                    Intent intent = new Intent(Home.this,login_page.class);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.create();
            builder.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        if (id == R.id.profile) {
            fragmentClass = ProfileFragment.class;
        } else if (id == R.id.dwn) {
            fragmentClass = DownloadFragment.class;
            //obalert = new alert("DownloadFragment", "Your List Coming Soon...");
        } else if (id == R.id.change_pass) {
            fragmentClass = Change_pass_Fragment.class;
            //obalert = new alert("Information", "Your Information Coming Soon...");
        } else if (id == R.id.abt) {
            fragmentClass = About_usFragment.class;
            //obalert = new alert("About Us", "Information Coming Soon...");
        }else if(id == R.id.home){
            fragmentClass = HomeFragment.class;
            //obalert = new alert("About Us", "Information Coming Soon...");
        }else if (id == R.id.lout) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
            builder.setTitle("Alert");
            builder.setMessage("do you want to Logout?");
            builder.setCancelable(false);
            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    auth.signOut();
                    finish();
                    Intent intent = new Intent(Home.this,login_page.class);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.create();
            builder.show();
        } else if (id == R.id.share) {
            //fragmentClass = ShareFragment.class;
            //obalert = new alert("Share", "Your ProfileFragment Coming Soon...");
        } else if (id == R.id.ct) {
            obalert = new alert("Conatact us ", "Plaese contact");
        } else if (id == R.id.send) {
            obalert = new alert("Feedback", "Give feedback on play store");
        }
        try {
            if (fragmentClass != null)
                fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (fragment != null) {
            //Toast.makeText(this, fragment.toString(), Toast.LENGTH_SHORT).show();
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.screen, fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class alert {
        public alert(String Title, String msg) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
            builder.setTitle(Title);
            builder.setMessage(msg);
            builder.setCancelable(true);
            builder.show();
        }
    }
    private void loadHomeFragment() {
        fragmentClass = HomeFragment.class;
        try {
            if (fragmentClass != null)
                fragment = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragment != null)
            fragmentManager.beginTransaction().replace(R.id.screen, fragment).commit();
    }
}
