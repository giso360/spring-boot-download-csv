package com.example.demo;

import java.util.List;
import java.util.Objects;

public class DemoResponse {

    private List<Person> peopleList;

    public DemoResponse(List<Person> peopleList) {
        this.peopleList = peopleList;
    }

    public List<Person> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(List<Person> peopleList) {
        this.peopleList = peopleList;
    }

    @Override public String toString() {
        return "DemoResponse{" +
            "peopleList=" + peopleList +
            '}';
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DemoResponse that = (DemoResponse) o;
        return Objects.equals(peopleList, that.peopleList);
    }

    @Override public int hashCode() {
        return Objects.hash(peopleList);
    }
}
