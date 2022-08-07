package com.example.cafe.repository;

import com.example.cafe.entity.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {
    Contractor findByName(String name);
}
