package com.example.Insurance.Service;

import com.example.Insurance.Entity.Customer;
import com.example.Insurance.Payload.CustomerDTO;
import com.example.Insurance.Repository.CustomerRepository;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@Service
public class CustomerserviceImp implements Customerservice{

    private CustomerRepository customerRepository;

    public CustomerserviceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }

    @Override
    public CustomerDTO addcustomer(Customer customer) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String generatedid = UUID.randomUUID().toString();
        String encodedpassword=encoder.encode(customer.getPassword());

        customer.setId(generatedid);
        customer.setPassword(encodedpassword);

        Customer save = customerRepository.save(customer);

        return entitytoDTO(save);
    }

    @Override
    public String login(String username, String password) {
        EmailService emailService = new EmailService();
        OtpService otpService = new OtpService();
        Customer findbyusername = customerRepository.findbyusername(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean matches = encoder.matches(password, findbyusername.getPassword());
        if (matches==true){
            String s = otpService.generateOtp(findbyusername.getEmail());
            emailService.sendOtpEmail(findbyusername.getEmail(),s);
            return "generated otp please proceed for otp validation with in 5 mins else otp will expire.";
        }
        else {
            return "username or password invalid!...";
        }
    }

    public CustomerDTO entitytoDTO(Customer customer){
        CustomerDTO customerDTO= new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setAge(customer.getAge());
        customerDTO.setUsername(customer.getUsername());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobile(customer.getMobile());
        return customerDTO;
    }
}
