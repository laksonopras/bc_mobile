package com.example.bc_mobile.model.customer;

import com.google.gson.annotations.SerializedName;

public class Customer {
    private Integer id_pelanggan;
    private String nama;
    private String email;
    private String token;

    public Integer getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(Integer id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

