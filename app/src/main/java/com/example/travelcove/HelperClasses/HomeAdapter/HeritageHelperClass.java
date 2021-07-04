package com.example.travelcove.HelperClasses.HomeAdapter;

import java.io.Serializable;

public class HeritageHelperClass implements Serializable {
    int image;
    String title, desc;

    public HeritageHelperClass(int image, String title, String desc) {
        this.image = image;
        this.title = title;
        this.desc = desc;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}
