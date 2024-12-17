package com.example.demo.controller;

import com.example.demo.model.Demo;
import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/demos")
public class DemoController {

    @Autowired
    private DemoService demoService;

    // Obtener todos los objetos Demo
    @GetMapping
    public List<Demo> getAllDemos() {
        return demoService.getAllDemos();
    }

    // Obtener un Demo por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Demo> getDemoById(@PathVariable String id) {
        Optional<Demo> demo = demoService.getDemoById(id);
        return demo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo Demo
    @PostMapping
    public ResponseEntity<Demo> createDemo(@RequestBody Demo demo) {
        Demo savedDemo = demoService.saveDemo(demo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDemo);
    }

    // Eliminar un Demo por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDemo(@PathVariable String id) {
        demoService.deleteDemo(id);
        return ResponseEntity.noContent().build();
    }
}
