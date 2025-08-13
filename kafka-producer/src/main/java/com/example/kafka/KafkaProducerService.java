package com.example.kafka;

import com.example.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate; //kafka ile iletisim kurmamizi saglayan kutuphane
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducerService.class);

    private final KafkaTemplate<String, String> kafkaTemplate; // key-value
    private final ObjectMapper objectMapper;
    private static final String TOPIC = "customer-events";

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendCustomer(Customer customer) {
        try {
            String message = objectMapper.writeValueAsString(customer); //kafka string/byte mesajlari ile calistigi
                                                                        //icin dogrudan java nesnesi gondermiyoruz. customer-->string


            //mesaj gondermeyi asenkron yapiyoruz cunku vakit alabilir uyg calismaya devam etsin
            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC, customer.getId().toString(), message);

            //mesaj gonderme islemi tamamlandiginda buraya gecer(.whenComplete)
            future.whenComplete((result, ex) -> { //islem basarili ise result, hatali ise ex doldurulur
                if (ex != null) {
                    log.error("Mesaj gönderilemedi! Key={}, Hata={}", customer.getId(), ex.getMessage());
                } else {
                    log.info("Mesaj gönderildi. Key={}, Partition={}, Offset={}",
                            customer.getId(),
                            result.getRecordMetadata().partition(),
                            result.getRecordMetadata().offset());
                }
            });

        } catch (Exception e) {
            log.error("Mesaj işlenirken hata oluştu: {}", e.getMessage(), e);
        }
    }

}
