package com.def.dokternak.ui.contohpetugas;

public class DataFilter {
    private String Nama;
    private int ImageID;

    DataFilter(String Nama, int ImageID){
        this.Nama = Nama;
        this.ImageID = ImageID;
    }

    String getNama() {
        return Nama;
    }

    int getImageID() {
        return ImageID;
    }
}
