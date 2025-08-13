package com.example.entity;

/*
bu sınıf veri tabanındaki 'customers' tablosuyla eşleşecek
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name ="customers")
@NoArgsConstructor
@AllArgsConstructor

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //bu anahtarın değeri otomatik yuklenecek, parantez ici de auto increment yapsın demek
    private Long id;

    @Column(name="isim")
    private String name;

    @Column(name="soyisim")
    private String surname;

    @Column(name="sifre")
    private String password;

    @Column(name="mail")
    private String mail;

    @Column(name = "eklenme_tarihi")
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
