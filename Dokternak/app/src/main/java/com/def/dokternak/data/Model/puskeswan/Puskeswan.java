package com.def.dokternak.data.Model.puskeswan;

import com.google.gson.annotations.SerializedName;

public class Puskeswan {
    @SerializedName("id_puskeswan")
    private int id;
    @SerializedName("nama_puskeswan")
    private String namaPuskeswan;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("jam_kerja")
    private String jamKerja;
    @SerializedName("gambar")
    private String gambar;
    @SerializedName("maps")
    private String maps;

    public Puskeswan(int id, String namaPuskeswan, String alamat, String jamKerja, String gambar, String maps) {
        this.id = id;
        this.namaPuskeswan = namaPuskeswan;
        this.alamat = alamat;
        this.jamKerja = jamKerja;
        this.gambar = gambar;
        this.maps = maps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaPuskeswan() {
        return namaPuskeswan;
    }

    public void setNamaPuskeswan(String namaPuskeswan) {
        this.namaPuskeswan = namaPuskeswan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJamKerja() {
        return jamKerja;
    }

    public void setJamKerja(String jamKerja) {
        this.jamKerja = jamKerja;
    }


    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getMaps() {
        return maps;
    }

    public void setMaps(String maps) { this.maps = maps;
    }

}
