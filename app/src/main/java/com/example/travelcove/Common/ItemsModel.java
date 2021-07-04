package com.example.travelcove.Common;

import java.io.Serializable;

public class ItemsModel implements Serializable {
    private String name;
    private String desc, desc2;
    public int image;

    public ItemsModel(String name, String desc, int image,  String desc2) {
        this.name = name;
        this.desc = desc;
        this.image = image;
        this.desc2 = desc2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
