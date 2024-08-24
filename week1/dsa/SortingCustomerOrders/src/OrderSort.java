/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Arrays;

class Order {
    String orderId;
    String customerName;
    double totalPrice;

    public Order(String orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Customer Name: " + customerName + ", Total Price: " + totalPrice;
    }
}

public class OrderSort {

    // Bubble Sort implementation
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].totalPrice > orders[j + 1].totalPrice) {
                    // Swap orders[j] and orders[j + 1]
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    // Quick Sort implementation
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);

            // Recursively sort elements before
            // partition and after partition
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].totalPrice;
        int i = (low - 1); // Index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (orders[j].totalPrice <= pivot) {
                i++;

                // Swap orders[i] and orders[j]
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }

        // Swap orders[i + 1] and orders[high] (or pivot)
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        Order[] orders = {
            new Order("O001", "Alice", 250.75),
            new Order("O002", "Bob", 125.50),
            new Order("O003", "Charlie", 300.20),
            new Order("O004", "David", 450.00),
            new Order("O005", "Eve", 220.30)
        };

        System.out.println("Original Orders:");
        for (Order order : orders) {
            System.out.println(order);
        }

        // Bubble Sort
        bubbleSort(orders);
        System.out.println("\nOrders Sorted by Bubble Sort:");
        for (Order order : orders) {
            System.out.println(order);
        }

        // Reinitialize the array for quick sort example
        orders = new Order[] {
            new Order("O001", "Alice", 250.75),
            new Order("O002", "Bob", 125.50),
            new Order("O003", "Charlie", 300.20),
            new Order("O004", "David", 450.00),
            new Order("O005", "Eve", 220.30)
        };

        // Quick Sort
        quickSort(orders, 0, orders.length - 1);
        System.out.println("\nOrders Sorted by Quick Sort:");
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
