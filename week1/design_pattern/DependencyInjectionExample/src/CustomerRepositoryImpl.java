/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public Customer findCustomerById(int id) {
        // Simulate fetching a customer from a database
        // In a real application, this would involve database operations
        return new Customer("John Doe", id, "Premium");
    }
}