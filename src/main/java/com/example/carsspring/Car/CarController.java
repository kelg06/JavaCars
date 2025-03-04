package com.example.carsspring.Car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarRepository carRepository;


    @GetMapping
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }


    @GetMapping("/{id}")
    public Optional<Car> getCarById(@PathVariable Long id) {
        return carRepository.findById(id);
    }


    @PostMapping
    public Car addCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        return carRepository.findById(id)
                .map(car -> {
                    car.setMake(updatedCar.getMake());
                    car.setColor(updatedCar.getColor());
                    car.setYear(updatedCar.getYear());
                    car.setPrice(updatedCar.getPrice());
                    return carRepository.save(car);
                })
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
    }


    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
    }
}
