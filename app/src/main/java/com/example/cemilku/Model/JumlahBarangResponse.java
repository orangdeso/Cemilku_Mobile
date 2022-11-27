package com.example.cemilku.Model;

import java.util.List;

public class JumlahBarangResponse {
    private int kode;
    private String message;
    public List<JumlahBarangData> data;

    public JumlahBarangResponse(byte kode, String message, List<JumlahBarangData> data) {
        this.kode = kode;
        this.message = message;
        this.data = data;
    }

    public int getKode() {
        return kode;
    }

    public void setKode(byte kode) {
        this.kode = kode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<JumlahBarangData> getData() {
        return data;
    }

    public void setData(List<JumlahBarangData> data) {
        this.data = data;
    }
}
