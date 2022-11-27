package com.example.cemilku.Model;

import java.util.List;

public class ProdukReadyResponse {
    private int kode;
    private String message;
    private List<ProdukReadyData> data;

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

    public List<ProdukReadyData> getData() {
        return data;
    }

    public void setData(List<ProdukReadyData> data) {
        this.data = data;
    }
}
