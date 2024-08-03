/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;
import java.util.Comparator;

class Product {
    String productId;
    String productName;
    String category;

    public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + ", Name: " + productName + ", Category: " + category;
    }
}

public class ProductSearch {
    
    // Linear Search implementation
    public static Product linearSearch(Product[] products, String productName) {
        for (Product product : products) {
            if (product.productName.equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }

    // Binary Search implementation
    public static Product binarySearch(Product[] products, String productName) {
        int left = 0, right = products.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compare = products[mid].productName.compareToIgnoreCase(productName);
            if (compare == 0) {
                return products[mid];
            } else if (compare < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Product[] products = {
            new Product("P001", "Laptop", "Electronics"),
            new Product("P002", "Smartphone", "Electronics"),
            new Product("P003", "Tablet", "Electronics"),
            new Product("P004", "Monitor", "Electronics"),
            new Product("P005", "Keyboard", "Accessories")
        };

        // Linear search
        String searchName1 = "Tablet";
        Product result1 = linearSearch(products, searchName1);
        System.out.println("Linear Search Result: " + (result1 != null ? result1 : "Product not found"));

        // For binary search, the array must be sorted by productName
        Arrays.sort(products, Comparator.comparing(product -> product.productName));

        // Binary search
        String searchName2 = "Monitor";
        Product result2 = binarySearch(products, searchName2);
        System.out.println("Binary Search Result: " + (result2 != null ? result2 : "Product not found"));
    }
}
