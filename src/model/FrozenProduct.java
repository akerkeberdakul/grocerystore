package model;

import exception.InvalidInputException;


public class FrozenProduct extends Product {
    private int storageTemp;

    public FrozenProduct(int id, String name, double price, int storageTemp){
        super(id, name, price);
        this.storageTemp = storageTemp;
    }
    public int getStorageTemp(){return storageTemp;}
    @Override
    public String work() {
        return "Frozen product: store at " + storageTemp + "°C.";
    }

    @Override
    public String getCategory(){
        return "Frozen model.Product";
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Storage Temp: " + storageTemp + "°C" +
                ", Type: Frozen";
    }
}
