package com.workintech.s18d2.controller;

import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.services.VegetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workintech/vegetables")
public class VegetableController {

    @Autowired
    private VegetableService vegetableService;

    @GetMapping
    public ResponseEntity<List<Vegetable>> getAllVegetablesSortedByPriceAsc() {
        List<Vegetable> vegetables = vegetableService.getByPriceAsc();
        return ResponseEntity.ok(vegetables);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vegetable> getVegetableById(@PathVariable Long id) {
        Vegetable vegetable = vegetableService.getById(id);
        if (vegetable == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vegetable);
    }

    @GetMapping("/desc")
    public ResponseEntity<List<Vegetable>> getAllVegetablesSortedByPriceDesc() {
        List<Vegetable> vegetables = vegetableService.getByPriceDesc();
        return ResponseEntity.ok(vegetables);
    }

    @PostMapping
    public ResponseEntity<Vegetable> saveOrUpdateVegetable(@RequestBody Vegetable vegetable) {
        Vegetable savedVegetable = vegetableService.save(vegetable);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVegetable);
    }

    @PostMapping("/{name}")
    public ResponseEntity<List<Vegetable>> findVegetablesByName(@PathVariable String name) {
        List<Vegetable> vegetables = vegetableService.searchByName(name);
        return ResponseEntity.ok(vegetables);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVegetable(@PathVariable Long id) {
        Vegetable vegetable = vegetableService.getById(id);
        if (vegetable == null) {
            return ResponseEntity.notFound().build();
        }
        vegetableService.delete(id);
        return ResponseEntity.ok().build();
    }
}