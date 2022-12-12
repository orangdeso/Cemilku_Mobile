package com.example.cemilku.Model;

import java.util.List;

public class FpesananMasukResponse {
    private int kode;
    private String message;
    public List<FpesananMasukData> data;

    public FpesananMasukResponse(int kode, String message, List<FpesananMasukData> data) {
        this.kode = kode;
        this.message = message;
        this.data = data;
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

    public List<FpesananMasukData> getData() {
        return data;
    }

    public void setData(List<FpesananMasukData> data) {
        this.data = data;
    }
}
