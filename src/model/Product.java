package model;

import exception.InvalidInputException;

public abstract class Product {
    protected int id;
    protected String name;
    protected double price;

    public Product(int id, String name, double price) {
        this.id = id;
        setName(name);
        setPrice(price);
    }

    public int getId() {
        return id;
    }

    public  String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }


    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price cannot be negative");

        }
    }

    public void displayInfo() {
        System.out.println(name + " - " + price + " KZT");
    }

    public abstract String work();

    public abstract String getCategory();


    @Override
    public String toString() {
        return "ID: " + id +
                ", Name: " + name +
                ", Price: " + price;
    }


}
