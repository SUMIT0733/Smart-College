package com.example.star_0733.project;

import java.util.Date;

/**
 * Created by sumit on 28-03-2018.
 */

public class NoticeUpload {
    private String name = "";
    private String uri = "";
    private String fac_name = "";
    private long messageTime;

    NoticeUpload(){}


    NoticeUpload(String name, String uri, String fac_name)
    {
        this.name = name;
        this.uri = uri;
        this.fac_name = fac_name;
        messageTime = new Date().getTime();

    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    public String getFac_name() {
        return fac_name;
    }

    public long getMessageTime() {
        return messageTime;
    }
}
