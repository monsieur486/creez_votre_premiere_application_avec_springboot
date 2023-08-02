package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAllPersons();
    Person save(Person person);
    Boolean update(Person person);
    Boolean delete(String firstName, String lastName);

}