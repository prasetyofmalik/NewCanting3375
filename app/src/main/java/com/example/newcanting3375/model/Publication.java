package com.example.newcanting3375.model;

public class Publication {
    private String pub_id;
    private String title;
    private String _abstract;
    private String issn;
    private String rl_date;
    private String cover;
    private String pdf;

    public Publication(String cover, String title, String date) {
        this.cover = cover;
        this.title = title;
        this.rl_date = date;
    }

    public String getCover() {
        return cover;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return rl_date;
    }
}

