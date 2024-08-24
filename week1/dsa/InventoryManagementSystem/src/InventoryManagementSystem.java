import java.util.HashMap;

class Product {
    String productId;
    String productName;
    int quantity;
    double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "Product ID: " + productId + ", Name: " + productName + ", Quantity: " + quantity + ", Price: " + price;
    }
}

public class InventoryManagementSystem {
    private HashMap<String, Product> inventory = new HashMap<>();

    public void addProduct(Product product) {
        inventory.put(product.productId, product);
        System.out.println("Added: " + product);
    }

    public void updateProduct(String productId, Product product) {
        if (inventory.containsKey(productId)) {
            inventory.put(productId, product);
            System.out.println("Updated: " + product);
        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }
    }

    public void deleteProduct(String productId) {
        if (inventory.containsKey(productId)) {
            Product removedProduct = inventory.remove(productId);
            System.out.println("Deleted: " + removedProduct);
        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }
    }

    public Product getProduct(String productId) {
        return inventory.get(productId);
    }
    
    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();
        
        // Adding products
        Product product1 = new Product("P001", "Laptop", 10, 1500.00);
        Product product2 = new Product("P002", "Smartphone", 25, 800.00);
        Product product3 = new Product("P003", "Tablet", 15, 600.00);
        ims.addProduct(product1);
        ims.addProduct(product2);
        ims.addProduct(product3);

        // Retrieving and displaying a product
        Product retrievedProduct = ims.getProduct("P002");
        if (retrievedProduct != null) {
            System.out.println("Retrieved: " + retrievedProduct);
        } else {
            System.out.println("Product not found.");
        }

        // Updating a product
        Product updatedProduct = new Product("P003", "Tablet", 20, 550.00);
        ims.updateProduct("P003", updatedProduct);
        retrievedProduct = ims.getProduct("P003");
        System.out.println("Updated product retrieved: " + retrievedProduct);
        // Deleting a product
        ims.deleteProduct("P001");

        // Trying to retrieve a deleted product
        Product deletedProduct = ims.getProduct("P001");
        if (deletedProduct != null) {
            System.out.println("Retrieved: " + deletedProduct);
        } else {
            System.out.println("Product not found.");
        }
    }
}
