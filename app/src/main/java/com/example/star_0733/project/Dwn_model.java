package com.example.star_0733.project;

import java.util.Date;

/**
 * Created by sumit on 29-03-2018.
 */

public class Dwn_model  {
    private String type="";
    private String name = "";
    private String fac_name = "";
    private long messageTime;

    Dwn_model(){}



    Dwn_model(String type, String name, String fac_name)
    {
        this.type = type;
        this.name = name;
        this.fac_name = fac_name;
        messageTime = new Date().getTime();
    }

    public String getName() {
        return name;
    }
    public String getFac_name() {
        return fac_name;
    }
    public long getMessageTime() {
        return messageTime;
    }
    public String getType() {
        return type;
    }
}
