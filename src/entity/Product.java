package entity;

public class Product {
    int id;
    String name;
    String unit;
    double price;
    double firstMonthPrice;
    int inventory;

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getFirstMonthPrice() {
        return firstMonthPrice;
    }

    public void setFirstMonthPrice(double firstMonthPrice) {
        this.firstMonthPrice = firstMonthPrice;
    }
}
