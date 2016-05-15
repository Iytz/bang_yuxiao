package com.bang.project.bang;

/**
 * Created by mad on 16-5-15.
 */
public class item {
    private String title;
    private String describe;
    private Double mysize;
    private Integer owner_id;

    public String getTitle() {
        return title;
    }

    public String getDescribe() {
        return describe;
    }

    public Double getMysize() {
        return mysize;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setMysize(Double mysize) {
        this.mysize = mysize;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }
}
