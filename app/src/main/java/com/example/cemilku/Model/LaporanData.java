package com.example.cemilku.Model;

import com.google.gson.annotations.SerializedName;

public class LaporanData{

    @SerializedName("total_price")
    private String totalPrice;

    @SerializedName("qty")
    private String qty;

    @SerializedName("placed_on")
    private String placedOn;

    @SerializedName("nama_user")
    private String namaUser;

    @SerializedName("id_orders")
    private String idOrders;

    public void setTotalPrice(String totalPrice){
        this.totalPrice = totalPrice;
    }

    public String getTotalPrice(){
        return totalPrice;
    }

    public void setQty(String qty){
        this.qty = qty;
    }

    public String getQty(){
        return qty;
    }

    public void setPlacedOn(String placedOn){
        this.placedOn = placedOn;
    }

    public String getPlacedOn(){
        return placedOn;
    }

    public void setNamaUser(String namaUser){
        this.namaUser = namaUser;
    }

    public String getNamaUser(){
        return namaUser;
    }

    public void setIdOrders(String idOrders){
        this.idOrders = idOrders;
    }

    public String getIdOrders(){
        return idOrders;
    }
}
