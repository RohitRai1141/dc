package com.example.demo.service;

import com.example.demo.model.Demo;
import com.example.demo.repo.DemoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    @Autowired
    private DemoRepo repo;

    public Demo log(String username, String password) {
        return repo.findByUsernameAndPassword(username, password);
    }
}
