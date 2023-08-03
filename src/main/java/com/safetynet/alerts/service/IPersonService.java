package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;

import java.util.List;

public interface IPersonService {
    List<Person> getAllPersons();

    Person save(Person person);

    Boolean update(Person person);

    Boolean delete(String firstName, String lastName);

    List<Person> getPersonsByAddress(String address);

    List<Person> getPersonsByCity(String city);

}
