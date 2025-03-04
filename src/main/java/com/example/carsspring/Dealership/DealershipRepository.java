package com.example.carsspring.Dealership;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealershipRepository extends JpaRepository<Dealership, Long> {


}
