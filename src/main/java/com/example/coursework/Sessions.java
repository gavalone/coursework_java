package com.example.coursework;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
public class Sessions {

    private Long id;
    private String filmname;
    private int price;
    private int quantity;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date dateofsessions;

    protected Sessions(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id){this.id = id;}

    public String getFilmname() {
        return filmname;
    }
    public void setFilmname(String filmname){this.filmname = filmname;}

    public int getPrice() {
        return price;
    }
    public void setPrice(int price){this.price = price;}

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity){this.quantity = quantity;}

    public Date getDateofsessions() { return dateofsessions; }
    public void setDateofsessions(Date dateofsessions){this.dateofsessions = dateofsessions;}
}

