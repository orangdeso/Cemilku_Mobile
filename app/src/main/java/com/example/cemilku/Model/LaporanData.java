package com.example.cemilku.Model;

public class LaporanData {

    private String id_orders;
    private String placed_on;
    private String total_products;
    private String total_price;
    private String nama_user;

    public String getId_orders() {
        return id_orders;
    }

    public void setId_orders(String id_orders) {
        this.id_orders = id_orders;
    }

    public String getPlaced_on() {
        return placed_on;
    }

    public void setPlaced_on(String placed_on) {
        this.placed_on = placed_on;
    }

    public String getTotal_products() {
        return total_products;
    }

    public void setTotal_products(String total_products) {
        this.total_products = total_products;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }
}
