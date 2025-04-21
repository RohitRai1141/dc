package com.example.demo.repo;

import com.example.demo.model.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepo extends JpaRepository<Demo, String> {
    Demo findByUsernameAndPassword(String username, String password);
}
