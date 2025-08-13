package com.example.customerService;

/*
iş mantığını burda kur
kafka template bu kısımda
 */

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    /*sprivate final KafkaTemplate<String, Object> kafkaTemplate; //ilki key ikincisi value
                                                                //aynı key --> aynı partition

    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    public CustomerRepository customerRepository;

    public CustomerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Object message){
        this.kafkaTemplate.send(topic, message);
        System.out.println("mesaj gonderildi: " + message);
    }*/

}
