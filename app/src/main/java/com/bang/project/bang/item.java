package com.bang.project.bang;

import java.security.Key;

/**
 * Created by mad on 16-5-15.
 */
public class item {
    private String title;
    private String describe;
    private String mysize;
    private String owner_name;
    private boolean HaveKey;
    private String key;
    private String url;
    private String type;

    public String getUrl() {
        return url;
    }

    public String getType() {return type;}

    public boolean HasKey() {
        return HaveKey;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public String getDescribe() {
        return describe;
    }

    public String getMysize() {
        return mysize;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setMysize(String mysize) {
        this.mysize = mysize;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public void setHaveKey(boolean k) {
        this.HaveKey = k;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
