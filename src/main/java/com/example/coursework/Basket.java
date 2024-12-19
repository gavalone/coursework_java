package com.example.coursework;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Сущность Basket - корзина покупок пользователя.
 * Включает информацию о фильме, его цене и имени пользователя, добавившего товар в корзину.
 */
@Entity
public class Basket {

    private Long id;
    private String filmname;
    private int price;
    private String username;

    protected Basket(){};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public  void setId(Long id){this.id = id;}

    public String getFilmname() {
        return filmname;
    }
    public  void setFilmname(String filmname){this.filmname = filmname;}

    public int getPrice() {
        return price;
    }
    public  void setPrice(int price){this.price = price;}

    public String getUsername() {
        return username;}

    public  void setUsername(String username){this.username = username;}
}
