package com.trelloiii.entities;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.Comparator;

/**
 * Класс описыващий сущность исполнитель в базе данных.
 * Каждое поле класса - столбец в связанной таблице implementers
 * @author trelloiii
 */
@Entity
@Table(name="implementers")
public class Implementer {
    /** Поле id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** Имя исполнителя*/
    private String name;
    /** Фамилия исполнителя*/
    private String surname;
    /** Номер телефона исполнителя*/
    private String phone;
    /** Адрес электронной почты исполнителя*/
    private String email;
    /** Возраст исполнителя*/
    private int age;
    /** Минимальная цена заказа, за который готов взяться исполнитель*/
    @Column(name="min_price")
    private int minimalPrice;//min price for get work
    /** Количество лет опыта работы исполнителя*/
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
