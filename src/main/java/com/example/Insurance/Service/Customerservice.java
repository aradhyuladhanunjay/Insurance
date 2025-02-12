package com.example.Insurance.Service;

import com.example.Insurance.Entity.Customer;
import com.example.Insurance.Payload.CustomerDTO;

public interface Customerservice {

    public CustomerDTO addcustomer(Customer customer);

    public String login(String username, String password);
}
