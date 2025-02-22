package com.example.Insurance.Controller;

import com.example.Insurance.Entity.Customer;
import com.example.Insurance.Payload.CustomerDTO;
import com.example.Insurance.Service.Customerservice;
import com.example.Insurance.Service.JWTtokenservice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CustomerAPI/v1/")
public class CustomerController {


    private Customerservice customerservice;

    public CustomerController(Customerservice customerservice) {
        this.customerservice = customerservice;
    }

    //http://localhost:8080/CustomerAPI/v1/addcustomer
    @PostMapping("/addcustomer")
    public ResponseEntity<CustomerDTO> addcustomer(@RequestBody Customer customer){
        CustomerDTO addcustomer = customerservice.addcustomer(customer);
        return new ResponseEntity<>(addcustomer, HttpStatus.CREATED);
    }


    //http://localhost:8080/CustomerAPI/v1/login
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){
        String login = customerservice.login(username, password);
        return login;
    }
    //http://localhost:8080/CustomerAPI/v1/{username}
    @DeleteMapping("{userid}")
    public String deletecustomer(@PathVariable("userid") String id){

        String deletecustomer = customerservice.deletecustomer(id);
        return deletecustomer;
    }




}
