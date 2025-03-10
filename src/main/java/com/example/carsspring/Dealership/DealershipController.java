package com.example.carsspring.Dealership;

import com.example.carsspring.Car.Car;
import com.example.carsspring.Car.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/dealerships")
public class DealershipController {

    @Autowired
    private DealershipRepository dealershipRepository;

    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public List<Dealership> getAllDealerships() {
        return dealershipRepository.findAll();
    }

    @GetMapping("/{id}")
    public Dealership getDealershipById(@PathVariable Long id) {
        return dealershipRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Dealership createDealership(@RequestBody Dealership dealership) {
        return dealershipRepository.save(dealership);
    }

    @PutMapping("/{id}")
    public Dealership updateDealership(@PathVariable Long id, @RequestBody Dealership dealershipDetails) {
        return dealershipRepository.findById(id).map(dealership -> {
            dealership.setDealerName(dealershipDetails.getDealerName());
            dealership.setDealLoc(dealershipDetails.getDealLoc());
            return dealershipRepository.save(dealership);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteDealership(@PathVariable Long id) {
        dealershipRepository.deleteById(id);
    }

    // ✅ Add a Car to an existing Dealership
    @PutMapping("/{dealershipId}/addCar")
    public Dealership addCarToDealership(@PathVariable Long dealershipId, @RequestBody Car car) {
        return dealershipRepository.findById(dealershipId).map(dealership -> {
            car.setDealership(dealership); // Associate car with dealership
            carRepository.save(car); // Save new car
            dealership.getCars().add(car); // Add car to dealership's collection
            return dealershipRepository.save(dealership);
        }).orElseThrow(() -> new RuntimeException("Dealership not found"));
    }
}
