package com.example.bc_mobile.model.customer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerResponse{
    @SerializedName("status")
    boolean status;
    @SerializedName("pesan")
    private String pesan;
    @SerializedName("data")
    private Customer data;
    public boolean isStatus() { return this.status; }
    public String getMessage() { return this.pesan; }
    public Customer getData(){
        return this.data;
    }
}