package com.example.demo;

import java.util.Objects;

public class Person {

    private String name;

    private int age;

    private String personID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public Person(String name, int age, String personID) {
        this.name = name;
        this.age = age;
        this.personID = personID;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return age == person.age &&
            Objects.equals(name, person.name) &&
            Objects.equals(personID, person.personID);
    }

    @Override public int hashCode() {
        return Objects.hash(name, age, personID);
    }

    @Override public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", personID='" + personID + '\'' +
            '}';
    }
}
