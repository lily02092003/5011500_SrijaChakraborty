/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Customer {
    private String name;
    private int id;
    private String membership;

    public Customer(String name, int id, String membership) {
        this.name = name;
        this.id = id;
        this.membership = membership;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getMembership() {
        return membership;
    }
}