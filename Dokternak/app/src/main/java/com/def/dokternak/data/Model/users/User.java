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

    public User(int id, String namaUser, String email, String role, String password) {
        this.id = id;
        this.namaUser = namaUser;
        this.email = email;
        this.role = role;
        this.password = password;
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
}
