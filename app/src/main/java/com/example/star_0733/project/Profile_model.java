package com.example.star_0733.project;

import javax.sql.StatementEvent;

/**
 * Created by sumit on 24-03-2018.
 */

public class Profile_model {

    private String name;
    private String mob;
    private int bid;
    private int sid;
    private String enroll;
    private String email;

    public Profile_model(){}

    public Profile_model(String name, String mob, int bid, int sid, String enroll, String email) {
        this.name = name;
        this.mob = mob;
        this.bid = bid;
        this.sid = sid;
        this.enroll = enroll;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getMob() {
        return mob;
    }

    public int getBid() {
        return bid;
    }

    public int getSid() {
        return sid;
    }

    public String getEnroll() {
        return enroll;
    }

    public String getEmail() {
        return email;
    }
}
