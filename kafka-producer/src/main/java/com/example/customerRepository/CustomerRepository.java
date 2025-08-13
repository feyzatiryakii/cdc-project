package com.example.customerRepository;

import com.example.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //belirtilen tarihten sonra eklenen musterileri donecek
    List<Customer> findByEklenmeTarihiAfter(LocalDateTime time);
}