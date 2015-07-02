package com.yey.read.home.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sunnie on 15/6/27.
 */
public class BookList implements Serializable {

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ArrayList<Book> getExclusiveBooks() {
        return exclusiveBooks;
    }

    public void setExclusiveBooks(ArrayList<Book> exclusiveBooks) {
        this.exclusiveBooks = exclusiveBooks;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getListid() {
        return listid;
    }

    public void setListid(String listid) {
        this.listid = listid;
    }

    private String feature;
    private String cover;
    private String listid;
    private String reason;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
    private ArrayList<Book> exclusiveBooks;

}
