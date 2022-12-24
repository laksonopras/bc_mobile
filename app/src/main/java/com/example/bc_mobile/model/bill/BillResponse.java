package com.example.bc_mobile.model.bill;

import com.example.bc_mobile.model.customer.Customer;
import com.google.gson.annotations.SerializedName;

public class BillResponse {
    @SerializedName("status")
    boolean status;
    @SerializedName("pesan")
    private String pesan;
    @SerializedName("data")
    private Bill data;
    public boolean isStatus() { return this.status; }
    public String getMessage() { return this.pesan; }
    public Bill getData(){
        return this.data;
    }
}
