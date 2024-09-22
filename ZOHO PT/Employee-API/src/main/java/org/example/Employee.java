package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Employee {
    private String name;
    private int id;
    private String position;
    private int age;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }

    Employee() {
    }

    Employee(int id, String name, int age, String position) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.position = position;
    }

    @Override
    public String toString() {
        return "\nEmployee Name: " + name + "\nID: " + id + "\nAge: " + age + "\nPosition: " + position;
    }

}
