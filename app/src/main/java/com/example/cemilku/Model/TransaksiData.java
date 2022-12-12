package com.example.cemilku.Model;

public class TransaksiData {
    private String id_orders, nama_user, method, total_price, placed_on, payment_status;

    public String getId_orders() {
        return id_orders;
    }

    public void setId_orders(String id_orders) {
        this.id_orders = id_orders;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getPlaced_on() {
        return placed_on;
    }

    public void setPlaced_on(String placed_on) {
        this.placed_on = placed_on;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }
}
