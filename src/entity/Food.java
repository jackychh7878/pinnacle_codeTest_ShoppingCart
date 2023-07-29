package entity;

public class Food extends Product{
    public Food(String name) {
        super(name);
        setTaxRate();
    }

    @Override
    public void setTaxRate() {
        setTaxRate(0);
    }
}
