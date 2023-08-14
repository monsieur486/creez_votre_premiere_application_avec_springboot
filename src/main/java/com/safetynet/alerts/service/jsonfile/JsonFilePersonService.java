package com.safetynet.alerts.service.jsonfile;

import com.safetynet.alerts.configuration.Constants;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;
import com.safetynet.alerts.utils.ElementsFromJsonFile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class JsonFilePersonService implements PersonService {

    private final List<Person> persons;

    public JsonFilePersonService() {
        this.persons = ElementsFromJsonFile.getJsonData(Constants.JSON_DATA_FILE_NAME).getPersons();
    }

    @Override
    public List<Person> getAllPersons() {
        return persons;
    }

    @Override
    public Person save(Person person) {
        Person personToSave = new Person();
        personToSave.setFirstName(person.getFirstName());
        personToSave.setLastName(person.getLastName());
        personToSave.setAddress(person.getAddress());
        personToSave.setCity(person.getCity());
        personToSave.setZip(person.getZip());
        personToSave.setPhone(person.getPhone());
        personToSave.setEmail(person.getEmail());
        persons.add(personToSave);

        return personToSave;
    }

    @Override
    public Boolean update(Person person) {
        String firstName = "";
        String lastName = "";
        boolean flag = false;
        for (int i = 0; i < persons.size(); i++) {
            if (Objects.equals(
                    persons.get(i).getFirstName(), person.getFirstName())
                    && Objects.equals(persons.get(i).getLastName(),
                    person.getLastName())) {
                firstName = person.getFirstName();
                lastName = person.getLastName();
                Person personToUpdate = new Person();
                personToUpdate.setFirstName(firstName);
                personToUpdate.setLastName(lastName);
                personToUpdate.setAddress(person.getAddress());
                personToUpdate.setCity(person.getCity());
                personToUpdate.setZip(person.getZip());
                personToUpdate.setPhone(person.getPhone());
                personToUpdate.setEmail(person.getEmail());
                persons.set(i, personToUpdate);
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public Boolean delete(String firstName, String lastName) {
        boolean flag = false;
        for (int i = 0; i < persons.size(); i++) {
            if (Objects.equals(
                    persons.get(i).getFirstName().toLowerCase() , firstName.toLowerCase())
                    && Objects.equals(persons.get(i).getLastName().toLowerCase(),
                    lastName.toLowerCase())) {
                persons.remove(i);
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public List<Person> getPersonsByAddress(String address) {
        return persons.stream()
                .filter(person -> Objects.equals(person.getAddress().toLowerCase(), address.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> getPersonsByCity(String city) {
        return persons.stream()
                .filter(person -> Objects.equals(person.getCity().toLowerCase(), city.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> getPeronsByFirstNameAndLastName(String firstName, String lastName) {
        return persons.stream()
                .filter(person -> Objects.equals(person.getFirstName().toLowerCase(), firstName.toLowerCase())
                        && Objects.equals(person.getLastName(), lastName.toLowerCase()))
                .collect(Collectors.toList());
    }

}
