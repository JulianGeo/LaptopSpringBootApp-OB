package com.example.demo.service;

import com.example.demo.entity.Laptop;
import com.example.demo.repository.LaptopRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LaptopService {

    private LaptopRepository laptopRepository;

    public LaptopService(LaptopRepository laptopRepository) {
        this.laptopRepository=laptopRepository;
    }

    public List<Laptop> getLaptops() {
        return laptopRepository.findAll();
    }

    public Laptop saveLaptop(Laptop laptop) {
        return laptopRepository.save(laptop);
    }
}
