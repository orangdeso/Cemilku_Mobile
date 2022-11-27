package com.example.cemilku.Model;

public class DataItem {
    private String email;
    private String nama_user;
    private String telpon;
    private String password;
    private String alamat;

    public DataItem(String email, String nama_user, String telpon, String password){
        this.email = email;
        this.nama_user = nama_user;
        this.telpon = telpon;
        this.password = password;
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }

    public String getTelpon() {
        return telpon;
    }

    public void setTelpon(String telpon) {
        this.telpon = telpon;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAlamat(String alamat){
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }
}
