package com.example.demo.controller;

import com.example.demo.entity.Laptop;
import com.example.demo.service.LaptopService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LaptopController {

    private LaptopService service;

    public LaptopController(LaptopService service) {
        this.service=service;
    }

    @GetMapping("laptops")
    public List<Laptop> getLaptops(){
        return service.getLaptops();
    }

    @PostMapping("laptops")
    public Laptop saveLaptop(@RequestBody Laptop laptop){
        return service.saveLaptop(laptop);
    }
}
