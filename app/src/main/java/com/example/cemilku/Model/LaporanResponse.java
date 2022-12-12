package com.example.cemilku.Model;

import java.util.List;

public class LaporanResponse {

    private int kode;
    private String message;
    public List <LaporanData> data;

    public LaporanResponse(int kode, String message, List<LaporanData> data) {
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

    public List<LaporanData> getData() {
        return data;
    }

    public void setData(List<LaporanData> data) {
        this.data = data;
    }
}
