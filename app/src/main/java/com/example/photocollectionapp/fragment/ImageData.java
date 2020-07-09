package com.example.photocollectionapp.fragment;

public class ImageData {

    private String imgDes;
    private String imgUrl;

    public ImageData(String imgDes, String imgUrl) {
        this.imgDes = imgDes;
        this.imgUrl = imgUrl;
    }

    public String getImgDes() {
        return imgDes;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
