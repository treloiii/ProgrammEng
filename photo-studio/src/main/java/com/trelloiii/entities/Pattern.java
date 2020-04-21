package com.trelloiii.entities;

import javax.persistence.*;
/**
 * Класс описыващий сущность шаблон в базе данных.
 * Каждое поле класса - столбец в связанной таблице pattern
 * @author trelloiii
 */
@Table(name="pattern")
@Entity
public class Pattern {
    /** Поле id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** Название шаблона*/
    private String name;
    /** Цена в шаблоне*/
    private double price;
    /** Описание в шаблоне*/
    private String description;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
