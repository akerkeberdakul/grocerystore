package model;

import exception.InvalidInputException;
import util.Discountable;


public class PackagedProduct extends Product implements Discountable {
    private String brand;

    public PackagedProduct(int id, String name, double price, String brand) {
        super(id, name, price);
        this.brand = brand;
    }
    @Override
    public double applyDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            return price;
        }
        price = price - (price * percent / 100);
        return price;
    }
    @Override
    public String work() {
        return "Packaged product:long shelf life, branded item.";
    }
    @Override
    public String getCategory(){
        return "Packaged model.Product";
    }
    @Override
    public String toString() {
        return super.toString() +
                ", Brand: " + brand +
                ", Type: Packaged";
    }
}
