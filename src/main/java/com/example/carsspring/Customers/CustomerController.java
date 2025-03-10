package com.example.carsspring.Customers;

import com.example.carsspring.Dealership.Dealership;
import com.example.carsspring.Dealership.DealershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DealershipRepository dealershipRepository;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        return customerRepository.findById(id).map(customer -> {
            customer.setName(customerDetails.getName());
            customer.setEmail(customerDetails.getEmail());
            customer.setPhone(customerDetails.getPhone());
            return customerRepository.save(customer);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }

    // ✅ Add a Dealership to an existing Customer
    @PutMapping("/{customerId}/addDealership/{dealershipId}")
    public Customer addDealershipToCustomer(@PathVariable Long customerId, @PathVariable Long dealershipId) {
        return customerRepository.findById(customerId).map(customer -> {
            Dealership dealership = dealershipRepository.findById(dealershipId)
                    .orElseThrow(() -> new RuntimeException("Dealership not found"));

            customer.getDealerships().add(dealership); // Add dealership to customer's list
            return customerRepository.save(customer);
        }).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}
