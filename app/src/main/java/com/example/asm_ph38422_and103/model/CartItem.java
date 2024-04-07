package com.example.asm_ph38422_and103.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CartItem {
    private String id; // ID của sản phẩm
    private String name;
    private String quantity;
    private String price;
    private String description;
    private ArrayList<String> image;

    public CartItem(String id, String name, String quantity, String price, String description, ArrayList<String> image) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getImage() {
        return image;
    }

    public void setImage(ArrayList<String> image) {
        this.image = image;
    }

    // Phương thức để tăng số lượng sản phẩm trong giỏ hàng
    public void increaseQuantity() {
        int quantityInt = Integer.parseInt(quantity);
        quantityInt++;
        quantity = String.valueOf(quantityInt);
    }

    // Phương thức để giảm số lượng sản phẩm trong giỏ hàng
    public void decreaseQuantity() {
        int quantityInt = Integer.parseInt(quantity);
        if (quantityInt > 0) {
            quantityInt--;
            quantity = String.valueOf(quantityInt);
        }
    }
}
