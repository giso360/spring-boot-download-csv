package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class PersonService {

    public List<Person> getAllPeople() {
        List<Person> people = new ArrayList<>();
        Person person1 = new Person("gio", 200, "XY111");
        Person person2 = new Person("per", 300, "XY112");
        people.add(person1);
        people.add(person2);
        return people;
    }

}
