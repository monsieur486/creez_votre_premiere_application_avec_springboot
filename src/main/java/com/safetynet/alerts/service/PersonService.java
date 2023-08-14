package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;

import java.util.List;

/**
 * The interface Person service.
 */
public interface PersonService {
    /**
     * Gets all persons.
     *
     * @return the all persons
     */
    List<Person> getAllPersons();

    /**
     * Save person.
     *
     * @param person the person
     * @return the person
     */
    Person save(Person person);

    /**
     * Update boolean.
     *
     * @param person the person
     * @return the boolean
     */
    Boolean update(Person person);

    /**
     * Delete boolean.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the boolean
     */
    Boolean delete(String firstName, String lastName);

    /**
     * Gets persons by address.
     *
     * @param address the address
     * @return the persons by address
     */
    List<Person> getPersonsByAddress(String address);

    /**
     * Gets persons by city.
     *
     * @param city the city
     * @return the persons by city
     */
    List<Person> getPersonsByCity(String city);

    /**
     * Gets perons by first name and last name.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the perons by first name and last name
     */
    List<Person> getPeronsByFirstNameAndLastName(String firstName, String lastName);

}
