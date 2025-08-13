package com.example.customerController;

import com.example.entity.Customer;
import com.example.kafka.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController //hem controller hem de response body
@RequestMapping(path = "/rest/api")
public class CustomerController {

    private final KafkaProducerService kafkaProducerService;
    private static final Logger log = LoggerFactory.getLogger(KafkaProducerService.class);

    public CustomerController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping(path = "/send")
    public ResponseEntity<String> sendCustomerEvent(@RequestBody Customer customer) {
        log.info("Yeni müşteri alındı: {}", customer);

        try {
            kafkaProducerService.sendCustomer(customer);
            return ResponseEntity.ok("Mesaj Kafka'ya gönderildi.");
        } catch (Exception e) {
            log.error("Kafka'ya mesaj gönderilemedi: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body("Mesaj gönderilemedi.");
        }
    }


}
