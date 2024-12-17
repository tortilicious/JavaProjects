package com.example.demo.repository;

import com.example.demo.model.Demo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DemoRepository extends MongoRepository<Demo, String> {
    // Aquí se pueden agregar consultas personalizadas si es necesario
}
