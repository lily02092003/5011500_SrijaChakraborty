/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class DependencyInjectionTest {
    public static void main(String[] args) {
        // Create the repository
        CustomerRepository repository = new CustomerRepositoryImpl();

        // Inject the repository into the service
        CustomerService service = new CustomerService(repository);

        // Use the service to find a customer
        Customer customer = service.getCustomer(1);

        // Display customer details
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Customer ID: " + customer.getId());
        System.out.println("Customer Membership: " + customer.getMembership());
    }
}