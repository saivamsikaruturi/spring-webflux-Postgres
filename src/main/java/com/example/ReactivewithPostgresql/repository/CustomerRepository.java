package com.example.ReactivewithPostgresql.repository;

import com.example.ReactivewithPostgresql.model.Customer;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer,Integer> {
}
