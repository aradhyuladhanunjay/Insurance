package com.example.Insurance.Repository;

import com.example.Insurance.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query("SELECT c FROM Customer c WHERE c.username = :username")
    public Customer findbyusername(@Param("username") String username);
}