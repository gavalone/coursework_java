package com.example.coursework;

import jakarta.persistence.*;

import java.util.Base64;

@Entity
public class Films {
    private Long id;
    private String filmname;
    private String description;

    private int year;
    private String director;


    @Lob
    private byte[] image;


    protected Films(){}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public  void setId(Long id){this.id = id;}
    public String getDescription(){return description;}
    public  void setDescription(String description){this.description = description;}

    public String getFilmname(){return filmname;}
    public  void setFilmname(String filmname){this.filmname = filmname;}

    public String getDirector(){return director;}
    public  void setDirector(String director){this.director = director;}

    public int getYear(){return year;}
    public  void setYear(int year){this.year = year;}

    public byte[] getImage(){return image;}
    public  void setImage(byte[] image){this.image = image;}


    @Transient
    public String getImageBase64() {
        if (image != null) {
            return Base64.getEncoder().encodeToString(image);
        }
        return null;
    }

}
