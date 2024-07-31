package com.example.demo.repositories;

import com.example.demo.models.Practice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticeRepo extends JpaRepository<Practice, Long> {
}
