package com.example.coursework;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired; //Для сбора классов-бинсов
import org.springframework.stereotype.Service; //Для обнаружения зависимостей

@Service
public class FilmsService {
    @Autowired
    private FilmsRepository repo;

    public List<Films> ListAll(String keyword) {
        if (keyword != null) {
            return repo.searchFilms(keyword);
        }
        return repo.findAll();
    }

    public void save(Films films) {
        repo.save(films);
    }

    public Films get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}