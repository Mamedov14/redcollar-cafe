package com.example.cafe.repository;
import com.example.cafe.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
    Position findByTitle(String title);
}
