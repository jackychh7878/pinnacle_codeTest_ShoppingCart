package entity;

import java.math.BigDecimal;

public abstract class Product {
    private String locationId;
    private String name;
    private BigDecimal price;
    private int quantity;
    private double taxRate;

    public Product(String name) {
        this.locationId = "";
        this.name = name;
        this.price = BigDecimal.valueOf(0);
        this.quantity = 0;
        this.taxRate = 0;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public abstract void setTaxRate();
}
