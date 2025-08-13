package com.example.scheduler;

import com.example.customerRepository.CustomerRepository;
import com.example.entity.Customer;
import com.example.kafka.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

/*
belirli araliklar ile veri tabanini kontrol edip yeni customer varsa kafkaya gonderecek
 */

@Component
public class CustomerScheduler {

    private final Logger logger = LoggerFactory.getLogger(CustomerScheduler.class);
    private final CustomerRepository customerRepository;
    private final KafkaProducerService kafkaProducerService;

    private LocalDateTime lastRun = LocalDateTime.now().minusMinutes(400);

    public CustomerScheduler(CustomerRepository customerRepository, KafkaProducerService kafkaProducerService) {
        this.customerRepository = customerRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Scheduled(fixedDelay = 10000)
    public void checkNewCustomersAndSend() {

        List<Customer> newCustomers = customerRepository.findByEklenmeTarihiAfter(lastRun);
        logger.info("Yeni müşteri sayısı (Kafka'ya gidecek): {}", newCustomers.size());

        if (newCustomers.isEmpty()) {
            logger.info("Yeni müşteri bulunamadı.");
        } else {
            newCustomers.forEach(customer -> {
                kafkaProducerService.sendCustomer(customer);
                logger.info("Kafka'ya gönderildi → ID: {}, İsim: {}, Mail: {}",
                        customer.getId(),
                        customer.getName(),
                        customer.getMail());
            });
        }
        lastRun = LocalDateTime.now(); //zamani guncelle
    }


}
