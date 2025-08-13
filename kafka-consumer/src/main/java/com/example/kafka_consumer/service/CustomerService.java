package com.example.kafka_consumer.service;

//bu katmanda kafkadan dinledigim mesajın işlemini yapicam --> db kaydedebilirim / baska dosyaya loglayabilirim vs

import com.example.kafka_consumer.entity.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    //  dosya yolu
    private static final Path filePath = Path.of("/app/logs/customerdeneme.log");

    public void processMessage(Customer customer) { //gelen nesneyi isleyip dosyaya yazacak
        try {
            //burada klasör var mı die bakicam
            Files.createDirectories(filePath.getParent());

            //customerı json stringe çevir
            String json = objectMapper.writeValueAsString(customer);

            // stringi dosyaya ekle
            Files.writeString(
                    filePath,
                    json + System.lineSeparator() ,
                    StandardOpenOption.CREATE, //dosya yoksa olusturucak
                    StandardOpenOption.APPEND //varsa sonuna eklicek
            );

            logger.info("Customer dosyaya eklendi: {}", customer);
        } catch (JsonProcessingException e) {
            logger.error("JSON işlenirken hata oluştu: {}", e.getMessage(), e);
        } catch (IOException e) {
            logger.error("Dosya yazılırken hata oluştu: {}", e.getMessage(), e);
        }
    }
}
