package com.def.dokternak.data.Model.kategori;

import com.google.gson.annotations.SerializedName;

public class KategoriHewan {
    @SerializedName("id_ktg")
    private int id;
    @SerializedName("kategori_artikel")
    private String kategori_artikel;

    public KategoriHewan(int id, String kategori_artikel) {
        this.id = id;
        this.kategori_artikel = kategori_artikel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKategori_artikel() {
        return kategori_artikel;
    }

    public void setKategori_artikel(String kategori_artikel) {
        this.kategori_artikel = kategori_artikel;
    }
}
