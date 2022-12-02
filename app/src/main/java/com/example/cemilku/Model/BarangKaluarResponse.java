package com.example.cemilku.Model;

public class BarangKaluarResponse {

    private int kode;
    private String message;
    public BarangKeluarData data;

    public BarangKaluarResponse(int kode, String message, BarangKeluarData data) {
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

    public BarangKeluarData getData() {
        return data;
    }

    public void setData(BarangKeluarData data) {
        this.data = data;
    }
}
