package com.example.cemilku.Model;

import java.util.List;

public class TransaksiResponse {
    private int kode;
    private String message;
    public List<TransaksiData> data;

    public TransaksiResponse(int kode, String message, List<TransaksiData> data) {
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

    public List<TransaksiData> getData() {
        return data;
    }

    public void setData(List<TransaksiData> data) {
        this.data = data;
    }
}
