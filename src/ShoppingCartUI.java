import entity.Clothing;
import entity.Food;
import entity.OtherProduct;
import entity.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShoppingCartUI {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        do{
            //      Hard code the available product library
            List<Product> productList = new ArrayList<>();
            productList.add(new Food("potato chips"));
            productList.add(new Clothing("shirt"));
            productList.add(new OtherProduct("book"));
            productList.add(new OtherProduct("pencil"));


            //        Request input from user
            System.out.print("Input: ");
            String inputString = keyboard.nextLine();
            String locationId = parseLocation(inputString);
            String[][] productsInputList = parseProducts(inputString);


//            Setup the productList Library for product name, quantity, and price
            for (Product product: productList){
                product.setLocationId(locationId);
                for (String[] productInput: productsInputList){
                    // Use contains instead of equals to avoid missing case e.g. pencil vs pencils
                    if (productInput[1].toLowerCase().contains(product.getName())){
                        product.setQuantity(Integer.parseInt(productInput[0]));
                        product.setPrice(BigDecimal.valueOf(Double.parseDouble(productInput[2])));
                    }
                }
            }

//            Assign the productList to the shopping cart
            ShoppingCart shoppingCart = new ShoppingCart(productList);

//            Print the output
            System.out.printf("%-20s $%-9s %2s\n", "Item", "Price", "Qty");
            for (Product product: shoppingCart.getProductList()){
                if (product.getQuantity() > 0){
                    System.out.printf("%-20s $%-9.2f %2s\n", product.getName(), product.getPrice(), product.getQuantity());
                }
            }
            System.out.printf("%-29s $%2.2f\n", "subtotal", shoppingCart.getSubtotal());
            System.out.printf("%-29s $%2.2f\n", "tax", shoppingCart.getTax());
            System.out.printf("%-29s $%2.2f\n", "total", shoppingCart.getTotal());
        }while(true);
    }

    public static String parseLocation(String input){
        Pattern pattern = Pattern.compile("Location:\\s*(\\w+)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()){
            return matcher.group(1);
        }
        return null;
    }

    public static String[][] parseProducts(String input){
        Pattern pattern = Pattern.compile("(\\d+)\\s+(.+?)\\s+at\\s+(\\d+\\.\\d+)");
        Matcher matcher = pattern.matcher(input);

        int productCount = 0;
        while (matcher.find()) {
            productCount++;
        }
        String[][] productInfo = new String[productCount][3];
        matcher.reset();
        int index = 0;

        while (matcher.find()){
            productInfo[index][0] = matcher.group(1);
            productInfo[index][1] = matcher.group(2).trim();
            productInfo[index][2] = matcher.group(3);
            index++;
        }
        return productInfo;
    }
}