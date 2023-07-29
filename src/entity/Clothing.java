package entity;

public class Clothing extends Product{

    public Clothing(String name) {
        super(name);
    }

    @Override
    public void setTaxRate() {
        if (super.getLocationId().equalsIgnoreCase("CA")){
            setTaxRate(0.0975);
        } else if (super.getLocationId().equalsIgnoreCase("NY")){
            setTaxRate(0);
        }
    }
}
