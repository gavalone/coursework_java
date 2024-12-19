package com.example.coursework;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для управления сущностями Myappuser.
 * Предоставляет доступ к базовым CRUD операциям и дополнительным пользовательским запросам.
 */
@Repository
public interface MyAppUserRepository extends JpaRepository<Myappuser, Long>{

    Optional<Myappuser> findByUsername(String username);
}