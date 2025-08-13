package com.example.kafka_consumer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //bu anahtarın değeri otomatik yuklenecek, parantez ici de auto increment yapsın demek
    private Long id;

    private String name;
    private String surname;
    private String password;
    private String mail;

   @Column(name="eklenme_tarihi")
    private LocalDateTime eklenmeTarihi;

    public Long getId() {
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getSurname(){
        return this.surname;
    }
    public String getPassword(){
        return this.password;
    }
    public String getMail(){
        return this.mail;
    }
    public LocalDateTime getEklenmeTarihi() {
        return this.eklenmeTarihi;
    }

}
