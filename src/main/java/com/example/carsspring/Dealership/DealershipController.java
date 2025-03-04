package com.example.carsspring.Dealership;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dealerships")
public class DealershipController {

    @Autowired
    DealershipRepository dealershipRepository;


    @GetMapping
    public List<Dealership> getAllDealerships() {
        return dealershipRepository.findAll();
    }


    @GetMapping("/{id}")
    public Optional<Dealership> getDealershipById(@PathVariable Long id) {
        return dealershipRepository.findById(id);
    }


    @PostMapping
    public Dealership createDealership(@RequestBody Dealership dealership) {
        return dealershipRepository.save(dealership);
    }


    @PutMapping("/{id}")
    public Dealership updateDealership(@PathVariable Long id, @RequestBody Dealership updatedDealership) {
        return dealershipRepository.findById(id)
                .map(dealership -> {
                    dealership.setDealerName(updatedDealership.getDealerName());
                    dealership.setDealLoc(updatedDealership.getDealLoc());
                    return dealershipRepository.save(dealership);
                })
                .orElseThrow(() -> new RuntimeException("Dealership not found with id: " + id));
    }


    @DeleteMapping("/{id}")
    public void deleteDealership(@PathVariable Long id) {
        dealershipRepository.deleteById(id);
    }
}
