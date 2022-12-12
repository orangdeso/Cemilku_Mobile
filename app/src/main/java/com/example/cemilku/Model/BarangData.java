package com.example.cemilku.Model;

public class BarangData {

    private String id_products, name_products, category, price, Deskripsi, image, email;

    public BarangData(String id_products, String name_products, String category, String price, String deskripsi, String image, String email) {
        this.id_products = id_products;
        this.name_products = name_products;
        this.category = category;
        this.price = price;
        this.Deskripsi = deskripsi;
        this.image = image;
        this.email = email;
    }

    public String getId_products() {
        return id_products;
    }

    public void setId_products(String id_products) {
        this.id_products = id_products;
    }

    public String getName_products() {
        return name_products;
    }

    public void setName_products(String name_products) {
        this.name_products = name_products;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
