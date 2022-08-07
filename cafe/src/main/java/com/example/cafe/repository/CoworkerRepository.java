package com.example.cafe.repository;

import com.example.cafe.entity.Coworker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoworkerRepository extends JpaRepository<Coworker, Long> {
    Coworker findByFio(String fio);
}
