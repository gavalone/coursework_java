package com.example.coursework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Сервисный класс для управления корзиной покупок.
 * Предоставляет методы для выполнения CRUD операций над объектами Basket.
 */
@Service
public class BasketService {
    @Autowired
    private BasketRepository repo;

    public List<Basket> ListAll() {
        return repo.findAll();
    }

    public void save(Basket basket) {
        repo.save(basket);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void deletebyuser(String username){
        repo.deletebyuser(username);
    }
}
