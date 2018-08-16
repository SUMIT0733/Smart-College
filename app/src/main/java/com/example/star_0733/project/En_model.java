package com.example.star_0733.project;

/**
 * Created by sumit on 24-03-2018.
 */

public class En_model {
    private String enroll;
    private String email;

    public En_model(){}

    public En_model(String enroll,String email) {
        this.enroll = enroll;
        this.email = email;
    }

    public String getEnroll() {
        return enroll;
    }

    public String getEmail() {
        return email;
    }
}
