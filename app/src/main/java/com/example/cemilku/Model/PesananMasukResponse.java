package com.example.cemilku.Model;

public class PesananMasukResponse {
    private int kode;
    private String message;
    public PesananMasukData data;

    public PesananMasukResponse(int kode, String message, PesananMasukData data) {
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

    public PesananMasukData getData() {
        return data;
    }

    public void setData(PesananMasukData data) {
        this.data = data;
    }
}
