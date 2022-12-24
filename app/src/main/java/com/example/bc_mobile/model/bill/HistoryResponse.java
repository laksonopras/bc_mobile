package com.example.bc_mobile.model.bill;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HistoryResponse {
    @SerializedName("status")
    boolean status;
    @SerializedName("pesan")
    private String pesan;
    @SerializedName("data")
    private ArrayList<Bill> data;
    public boolean isStatus() { return this.status; }
    public String getMessage() { return this.pesan; }
    public ArrayList<Bill> getData(){
        return this.data;
    }
}
