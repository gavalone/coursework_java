package com.example.coursework;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * Репозиторий для управления сущностями Sessions.
 * Предоставляет доступ к базовым CRUD операциям и дополнительным пользовательским запросам.
 */
public interface SessionsRepository extends JpaRepository<Sessions, Long> {
    @Query("SELECT p FROM Sessions p WHERE CONCAT(p.filmname, '') LIKE %?1%")
    List<Sessions> searchFilms(String keyword);
}
