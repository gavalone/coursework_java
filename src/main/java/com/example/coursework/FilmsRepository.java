package com.example.coursework;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Репозиторий для управления сущностями Films.
 * Предоставляет доступ к базовым CRUD операциям и дополнительным пользовательским запросам.
 */
public interface FilmsRepository extends JpaRepository<Films, Long> {
    @Query("SELECT p FROM Films p WHERE CONCAT(p.filmname, '', p.year, '', p.director, '') LIKE %?1%")
    List<Films> searchFilms(String keyword);
}
