package com.def.dokternak.data.Model.petugas;

import com.google.gson.annotations.SerializedName;

public class Petugas {
    @SerializedName("id_dokter")
    private int id;
    @SerializedName("nama_dokter")
    private String namaDokter;
    @SerializedName("email")
    private String email;
    @SerializedName("jenis_kelamin")
    private String jenisKelamin;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("tempat")
    private String tempat;
    @SerializedName("telpon")
    private String telpon;
    @SerializedName("foto")
    private String foto;
    @SerializedName("sertifikasi")
    private String sertifikasi;
    @SerializedName("id_jabatan")
    private String idJabatan;
    @SerializedName("jadwal_kerja")
    private String jadwalKerja;
    @SerializedName("verifikasi")
    private String verifikasi;

    public Petugas(int id, String namaDokter, String email, String jenisKelamin, String alamat, String tempat, String telpon, String foto, String sertifikasi, String idJabatan, String jadwalKerja, String verifikasi) {
        this.id = id;
        this.namaDokter = namaDokter;
        this.email = email;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
        this.tempat = tempat;
        this.telpon = telpon;
        this.foto = foto;
        this.sertifikasi = sertifikasi;
        this.idJabatan = idJabatan;
        this.jadwalKerja = jadwalKerja;
        this.verifikasi = verifikasi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getTelpon() {
        return telpon;
    }

    public void setTelpon(String telpon) {
        this.telpon = telpon;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getSertifikasi() {
        return sertifikasi;
    }

    public void setSertifikasi(String sertifikasi) {
        this.sertifikasi = sertifikasi;
    }

    public String getIdJabatan() {
        return idJabatan;
    }

    public void setIdJabatan(String idJabatan) {
        this.idJabatan = idJabatan;
    }

    public String getJadwalKerja() {
        return jadwalKerja;
    }

    public void setJadwalKerja(String jadwalKerja) {
        this.jadwalKerja = jadwalKerja;
    }

    public String getVerifikasi() {
        return verifikasi;
    }

    public void setVerifikasi(String verifikasi) {
        this.verifikasi = verifikasi;
    }
}
