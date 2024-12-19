package com.example.coursework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Сервисный класс для управления сеансами.
 * Предоставляет методы для выполнения CRUD операций над объектами Sessions.
 */
@Service
public class SessionsService {
    @Autowired
    private SessionsRepository repo;

    public List<Sessions> ListAll(String keyword) {
        if (keyword != null) {
            return repo.searchFilms(keyword);
        }
        return repo.findAll();
    }

    public void save(Sessions sessions) {
        repo.save(sessions);
    }

    public Sessions get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}