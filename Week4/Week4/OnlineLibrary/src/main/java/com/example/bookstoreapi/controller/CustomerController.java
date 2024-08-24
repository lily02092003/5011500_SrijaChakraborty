package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.CustomerDTO;
import com.example.bookstoreapi.model.Customer;
import com.example.bookstoreapi.service.CustomerMapper;
import com.example.bookstoreapi.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    // POST: /customers (Accept JSON request body)
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        Customer createdCustomer = customerService.save(customer);
        CustomerDTO createdCustomerDTO = customerMapper.customerToCustomerDTO(createdCustomer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/customers/" + createdCustomerDTO.getId());
        return new ResponseEntity<>(createdCustomerDTO, headers, HttpStatus.CREATED);
    }

    // POST: /customers/form (Accept form data)
    @PostMapping("/form")
    public ResponseEntity<String> createCustomerFromForm(HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(name);
        customerDTO.setEmail(email);
        customerDTO.setPhone(phone);
        customerDTO.setAddress(address);

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customerService.save(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body("Customer registered successfully");
    }
}
