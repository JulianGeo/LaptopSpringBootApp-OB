package com.example.demo.service;

import com.example.demo.entity.Laptop;
import com.example.demo.repository.LaptopRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public Optional<Laptop> getLaptopByID(String id) {
        return laptopRepository.findById(id);
    }

    public Laptop saveLaptop(Laptop laptop) {
        return laptopRepository.save(laptop);
    }


    public Laptop updateLaptop(Laptop laptop) {
        //Laptop oldLaptop = laptopRepository.findById(laptop.getLaptopID()).orElseThrow();
        return laptopRepository.save(laptop);
    }

    public void deleteByID(String id) {
        laptopRepository.deleteById(id);
    }

    public void deleteAll() {
        laptopRepository.deleteAll();
    }

}
