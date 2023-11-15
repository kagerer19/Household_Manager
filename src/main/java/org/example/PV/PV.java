package org.example.PV;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class PV {
    List<Person> people = new ArrayList<>();

    public Person getUserById(int ID) {
        for (Person person : people) {
            if (person.getID() == ID) {
                return person;
            }
        }
        return null;
    }

    public Person getUserByName(String name) throws NullPointerException {
        for (Person person : people) {
            if (person.getFirstName().equalsIgnoreCase(name) || person.getLastName().equalsIgnoreCase(name)) {
                return person;
            }
        }
        throw new NullPointerException("User with the given name not found: " + name);
    }

    public void removeUser(int ID) {
        //iterate the list for ID and then remove
        Iterator<Person> userToDelete = people.iterator();
        int removeByID;

        while (userToDelete.hasNext()) {
            removeByID = userToDelete.next().getID();
            if (removeByID == ID) {
                userToDelete.remove();
            }
        }
    }

    public void createNewUser(int ID, String name, String surname) {
        Person newPerson = new Person(ID, name, surname);
        people.add(newPerson);
    }

    public void createNewUser(int ID, String name, String surname, String birthday, Person.Gender gender) {
        Person newPerson = new Person(ID, name, surname, birthday, gender);
        people.add(newPerson);
    }

    public void createNewUser(int ID, String name, String surname, String birthday, Person.Gender gender, Household household) {
        Person newPerson = new Person(ID, name, surname, birthday, gender, household);
        people.add(newPerson);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (people.isEmpty()) {
            sb.append("is empty");
        } else {
            for (Person u : people) {
                sb.append(u.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}