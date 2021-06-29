package com.def.dokternak.data.Model.artikel;

import com.google.gson.annotations.SerializedName;

public class Artikel {
    @SerializedName("id_artikel")
    private int id;
    @SerializedName("id_ktg")
    private int idKtg;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("nama_penulis")
    private String namaPenulis;
    @SerializedName("judul")
    private String judul;
    @SerializedName("isi")
    private String isi;
    @SerializedName("gambar")
    private String gambar;
    @SerializedName("sumber")
    private String sumber;
    @SerializedName("status")
    private String status;

    public Artikel(int id, int idKtg, String tanggal, String namaPenulis, String judul, String isi, String gambar, String sumber, String status) {
        this.id = id;
        this.idKtg = idKtg;
        this.tanggal = tanggal;
        this.namaPenulis = namaPenulis;
        this.judul = judul;
        this.isi = isi;
        this.gambar = gambar;
        this.sumber = sumber;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdKtg() {
        return idKtg;
    }

    public void setIdKtg(int idKtg) {
        this.idKtg = idKtg;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNamaPenulis() {
        return namaPenulis;
    }

    public void setNamaPenulis(String namaPenulis) {
        this.namaPenulis = namaPenulis;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

