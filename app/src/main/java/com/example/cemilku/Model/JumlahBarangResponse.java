package com.example.cemilku.Model;

import java.util.List;

public class JumlahBarangResponse {
    private int kode;
    private String message;
    public JumlahBarangData data;

    public JumlahBarangResponse(int kode, String message, JumlahBarangData data) {
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

    public JumlahBarangData getData() {
        return data;
    }

    public void setData(JumlahBarangData data) {
        this.data = data;
    }
}
