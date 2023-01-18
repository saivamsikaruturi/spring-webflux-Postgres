package com.example.ReactivewithPostgresql.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="customer")
@Data
public class Customer {

    @Id
    private int id;
    private String name;
}
