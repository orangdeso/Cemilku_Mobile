package com.example.cemilku.Model;

import java.util.List;

public class BarangResponse {
    private int kode;
    private String message;
    private List<BarangData> data;

    public BarangResponse(int kode, String message, List<BarangData> listBarang) {
        this.kode = kode;
        this.message = message;
        this.data = listBarang;
    }

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BarangData> getListBarang() {
        return data;
    }

    public void setListBarang(List<BarangData> listBarang) {
        this.data = listBarang;
    }
}
