package com.trelloiii.entities;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.Comparator;

@Entity
@Table(name="implementers")
public class Implementer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private int age;
    @Column(name="min_price")
    private int minimalPrice;//min price for get work
    private int experience;


    public void setMinimalPrice(int minimalPrice) {
        this.minimalPrice = minimalPrice;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getMinimalPrice() {
        return minimalPrice;
    }

    public int getExperience() {
        return experience;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }
}
