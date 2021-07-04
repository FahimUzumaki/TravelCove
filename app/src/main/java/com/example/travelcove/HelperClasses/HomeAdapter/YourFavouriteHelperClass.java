package com.example.travelcove.HelperClasses.HomeAdapter;

import java.io.Serializable;

public class YourFavouriteHelperClass implements Serializable {
    int image2;
    String title2, desc2;

    public YourFavouriteHelperClass(int image2, String title2, String desc2) {
        this.image2 = image2;
        this.title2 = title2;
        this.desc2 = desc2;
    }

    public int getImage2() {
        return image2;
    }

    public String getTitle2() {
        return title2;
    }

    public String getDesc2() {
        return desc2;
    }
}
