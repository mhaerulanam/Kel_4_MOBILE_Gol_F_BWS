package com.def.dokternak.data.Model.users;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String namaUser;
    @SerializedName("email")
    private String email;
    @SerializedName("is_admin")
    private String role;
    @SerializedName("password")
    private String password;

    @SerializedName("id_peternak")
    private int id_peternak;
    @SerializedName("namadepan_peternak")
    private String namaPeternak;
    @SerializedName("namabelakang_peternak")
    private String namaBelakangPeternak;
    @SerializedName("email_peternak")
    private String emailPeternak;
    @SerializedName("no_hp")
    private String noHp;
    @SerializedName("jenis_kelamin")
    private String jenisKelamin;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("foto_peternak")
    private String fotoPeternak;

    public User(int id, String namaUser, String email, String role, String password, int id_peternak, String namaPeternak, String namaBelakangPeternak, String emailPeternak, String noHp, String jenisKelamin, String alamat, String fotoPeternak) {
        this.id = id;
        this.namaUser = namaUser;
        this.email = email;
        this.role = role;
        this.password = password;
        this.id_peternak = id_peternak;
        this.namaPeternak = namaPeternak;
        this.namaBelakangPeternak = namaBelakangPeternak;
        this.emailPeternak = emailPeternak;
        this.noHp = noHp;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
        this.fotoPeternak = fotoPeternak;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_peternak() {
        return id_peternak;
    }

    public void setId_peternak(int id_peternak) {
        this.id_peternak = id_peternak;
    }

    public String getNamaPeternak() {
        return namaPeternak;
    }

    public void setNamaPeternak(String namaPeternak) {
        this.namaPeternak = namaPeternak;
    }

    public String getNamaBelakangPeternak() {
        return namaBelakangPeternak;
    }

    public void setNamaBelakangPeternak(String namaBelakangPeternak) {
        this.namaBelakangPeternak = namaBelakangPeternak;
    }

    public String getEmailPeternak() {
        return emailPeternak;
    }

    public void setEmailPeternak(String emailPeternak) {
        this.emailPeternak = emailPeternak;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
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

    public String getFotoPeternak() {
        return fotoPeternak;
    }

    public void setFotoPeternak(String fotoPeternak) {
        this.fotoPeternak = fotoPeternak;
    }
}
