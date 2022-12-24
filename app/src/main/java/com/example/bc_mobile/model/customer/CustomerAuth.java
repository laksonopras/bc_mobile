package com.example.bc_mobile.model.customer;

import com.google.gson.annotations.SerializedName;

public class CustomerAuth {
    @SerializedName("status")
    boolean status;
    @SerializedName("pesan")
    private String pesan;
    @SerializedName("auth")
    private Integer id;
    public boolean isStatus() { return this.status; }
    public String getPesan() { return this.pesan; }
    public Integer getAuth(){
        return this.id;
    }
}
