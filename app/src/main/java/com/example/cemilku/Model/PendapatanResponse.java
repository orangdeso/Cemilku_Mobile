package com.example.cemilku.Model;

public class PendapatanResponse {
    private int kode;
    private String message;
    public PendapatanData data;

    public PendapatanResponse(int kode, String message, PendapatanData data) {
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

    public PendapatanData getData() {
        return data;
    }

    public void setData(PendapatanData data) {
        this.data = data;
    }
}
