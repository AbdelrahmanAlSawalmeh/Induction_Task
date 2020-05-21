package com.progressoft.induction;


public class Snack {

    private SnackType snackType;
    private int quantity;
    private double price;

    public Snack(SnackType snackType, int quantity, double price) {
        this.snackType = snackType;
        this.quantity = quantity;
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int quantity() {
        return this.quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double price() {
        return this.price;
    }

    public void setSnackType(SnackType snackType) {
        this.snackType = snackType;
    }

    public SnackType getSnackType() {
        return this.snackType;
    }

    public void decreaseQuantity() {
        this.quantity -= 1;
    }
}
