package com.example.coursework;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Репозиторий для управления сущностями Basket.
 * Предоставляет доступ к базовым CRUD операциям и дополнительным пользовательским запросам.
 */
public interface BasketRepository extends JpaRepository<Basket, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Basket e WHERE e.username = :value")
    void deletebyuser(String value);
}