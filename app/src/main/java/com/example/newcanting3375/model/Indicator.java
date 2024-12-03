package com.example.newcanting3375.model;

public class Indicator {
    private int icon;
    private String title;
    private String stat;
    private String desc;

    public Indicator(int icon, String title, String stat, String desc) {
        this.icon = icon;
        this.title = title;
        this.stat = stat;
        this.desc = desc;
    }

    public int getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public String getStat() {
        return stat;
    }

    public String getDesc() {
        return desc;
    }
}

