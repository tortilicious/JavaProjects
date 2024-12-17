package com.example.demo.service;

import com.example.demo.model.Demo;
import com.example.demo.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemoService {

    @Autowired
    private DemoRepository demoRepository;

    public List<Demo> getAllDemos() {
        return demoRepository.findAll();
    }

    public Optional<Demo> getDemoById(String id) {
        return demoRepository.findById(id);
    }

    public Demo saveDemo(Demo demo) {
        return demoRepository.save(demo);
    }

    public void deleteDemo(String id) {
        demoRepository.deleteById(id);
    }
}
