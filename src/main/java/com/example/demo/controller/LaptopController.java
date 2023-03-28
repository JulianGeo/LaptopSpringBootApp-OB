package com.example.demo.controller;

import com.example.demo.entity.Laptop;
import com.example.demo.service.LaptopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Retention;
import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private LaptopService service;

    public LaptopController(LaptopService service) {
        this.service=service;
    }

    @GetMapping("laptops")
    public ResponseEntity<List<Laptop>> getLaptops(){
        return ResponseEntity.ok().body(service.getLaptops());
    }

    @GetMapping("laptops/{id}")
    public ResponseEntity<Optional<Laptop>> getLaptopById(@PathVariable String id){
        return ResponseEntity.ok().body(service.getLaptopByID(id));
    }
    @PostMapping("laptops")
    public ResponseEntity<Laptop> saveLaptop(@RequestBody Laptop laptop){
        if (laptop.getLaptopID() != null){return  ResponseEntity.badRequest().build();}
        return ResponseEntity.ok().body(service.saveLaptop(laptop));
    }

    @PutMapping("laptops/{id}")
    public ResponseEntity<Laptop> updateLaptop(@RequestBody Laptop laptop){
        if (laptop.getLaptopID() == null){return  ResponseEntity.badRequest().build();}
        return ResponseEntity.ok().body(service.updateLaptop(laptop));
    }

    @DeleteMapping("laptops")
    public ResponseEntity<String> deleteLaptops(){
        service.deleteAll();
        return ResponseEntity.ok().body("All laptops were deleted");
    }

    @DeleteMapping("laptops/{id}")
    public ResponseEntity<String> deleteLaptops(@PathVariable String id){
        service.deleteByID(id);
        return ResponseEntity.ok().body("Laptop with ID: "+id+", was deleted");

    }
}
