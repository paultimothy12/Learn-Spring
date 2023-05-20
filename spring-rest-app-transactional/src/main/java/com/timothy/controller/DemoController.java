package com.timothy.controller;

import com.timothy.entities.CustomerEN;
import com.timothy.entities.CustomerRepo;
import com.timothy.entities.OrderEN;
import com.timothy.entities.OrderRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class DemoController {
    CustomerRepo customerRepo;
    OrderRepo orderRepo;
    @Autowired
    public DemoController(CustomerRepo customerRepo, OrderRepo orderRepo) {
        this.customerRepo = customerRepo;
        this.orderRepo = orderRepo;
    }

    @PostMapping("/api/post-customer-data")
    public ResponseEntity<String> addNewCustomer(@RequestBody CustomerEN customer){
        customerRepo.save(customer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customer.getCustomerId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/api/get-customer-data")
    public List<CustomerEN> getCustomerData(){
        return customerRepo.findAll();
    }

    @GetMapping("/api/get-order-data")
    public List<OrderEN> getOrderData(){
        return orderRepo.findAll();
    }

    @PostMapping("/api/post-bill-checkout")
    @Transactional
    public ResponseEntity<String> billCheckout(@RequestBody OrderEN order){
        orderRepo.save(order);

        CustomerEN customer = customerRepo.findById(order.getCustomerId()).get();
        customer.setCredit(customer.getCredit()- order.getTotalBill());
        customerRepo.save(customer);




        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getCustomerId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
