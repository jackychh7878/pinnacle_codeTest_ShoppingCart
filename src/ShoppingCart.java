import entity.Product;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCart {
    private List<Product> productList;
    private double subtotal;
    private double tax;
    private double total;

    public ShoppingCart(List<Product> productList) {
        this.productList = productList;
        setSubtotal();
        setTax();
        setTotal();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal() {
        double beforeRoundedSubtotal = 0;
        for (Product product: productList){
            beforeRoundedSubtotal += product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())).doubleValue();
            double roundedSubTotal = Math.round(beforeRoundedSubtotal * 100.0) / 100.0;
            this.subtotal = roundedSubTotal;
        }
    }

    public double getTax() {
        return tax;
    }

    public void setTax() {
        double beforeRoundedTax = 0;
        for (Product product: productList){
            product.setTaxRate();
            beforeRoundedTax += product.getPrice().multiply(BigDecimal.valueOf(product.getTaxRate() * product.getQuantity())).doubleValue();
            double roundedTax = Math.ceil(beforeRoundedTax * 20.0) / 20.0;
            this.tax = roundedTax;
        }
    }

    public double getTotal() {
        return total;
    }

    public void setTotal() {
        double beforeRoundedTotal = subtotal + tax;
        double roundedTotal = Math.round(beforeRoundedTotal * 100.0) / 100.0;
        this.total = roundedTotal;
    }
}
