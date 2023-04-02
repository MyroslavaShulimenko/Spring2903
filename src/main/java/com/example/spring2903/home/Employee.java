package com.example.spring2903.home;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Employee {

    private String id;

    private String name;

    private String surname;

    private int age;

    public Employee(String id, String name, String surname, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Employee(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
