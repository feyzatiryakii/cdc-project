package com.example.kafka_consumer.listener;
import com.example.kafka_consumer.entity.Customer;
import com.example.kafka_consumer.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j

public class ConsumerListener
{
    private static final Logger logger = LoggerFactory.getLogger(ConsumerListener.class);

    @Autowired
    private final CustomerService customerService;

    public ConsumerListener(CustomerService customerService) {
        this.customerService = customerService;
    }

    @KafkaListener(topics = "${kafka.topic}", groupId = "customer-group")
    public void consumeMessage(String message) { //kafkadan gelen mesajları dinleyecek kisim
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule()); //eklenme tarihini duzgun deserialize edebilmek icin lazim

            Customer customer = objectMapper.readValue(message, Customer.class);
            //kafkadan mesaj json formatinda gelicek -- readValue aldigi mesaji customer sinifina ceviriyo (

            logger.info("mesaj işleniyor: {}", customer);
            customerService.processMessage(customer);
        } catch (Exception e) {
            logger.error("mesaj işlenirken hata oluştu: {}", e.getMessage(), e);
        }
    }
}
