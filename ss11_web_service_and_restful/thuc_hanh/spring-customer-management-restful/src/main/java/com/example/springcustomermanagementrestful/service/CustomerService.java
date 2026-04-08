package com.example.springcustomermanagementrestful.service;

import com.example.springcustomermanagementrestful.entity.Customer;
import com.example.springcustomermanagementrestful.repository.ICustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService{
    private final ICustomerRepository customerRepository;
    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public Iterable<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return this.customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        this.customerRepository.deleteById(id);
    }
}
