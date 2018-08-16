package com.example.star_0733.project;

import android.content.Context;
import android.content.SharedPreferences;

import javax.sql.StatementEvent;

/**
 * Created by sumit on 14-03-2018.
 */

public class Pref {
    private Context context,context1;
    public Pref(Context context)
    {
        this.context=context;
    }
    public void saveLoginDetails(String Id, String password) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Login Detail", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Id", Id);
        editor.putString("Password", password);
        editor.commit();
    }
    public void saveProfile(String Name, String Email,String Mob)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Login Detail", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Name", Name);
        editor.putString("Email", Email);
        editor.putString("Mob", Mob);
        editor.commit();
    }

    public String getId() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Login Detail", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Id", "");
    }
    public String getPass() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Login Detail", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Password", "");
    }
    public String getName() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Login Detail", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Name", "");
    }
    public String getEmail() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Login Detail", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Email", "");
    }
//    public String getBranch() {
//        SharedPreferences sharedPreferences = context.getSharedPreferences("Login Detail", Context.MODE_PRIVATE);
//        return sharedPreferences.getString("Branch", "");
//    }
//    public String getSem() {
//        SharedPreferences sharedPreferences = context.getSharedPreferences("Login Detail", Context.MODE_PRIVATE);
//        return sharedPreferences.getString("Sem", "");
//    }
    public String getMob() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Login Detail", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Mob", "");
    }
}
