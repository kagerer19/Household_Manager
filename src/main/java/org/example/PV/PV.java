package org.example.PV;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class PV {
    List<User> users = new ArrayList<>();

    public User getUserById(int ID) {
        for (User user : users) {
            if (user.getID() == ID) {
                return user;
            }
        }
        return null;
    }

    public User getUserByName(String name) {
        for (User user : users) {
            if (user.getFirstName().equalsIgnoreCase(name) || user.getLastName().equalsIgnoreCase(name)) {
                return user;
            } else {
                System.out.println("No user with the information provided: ");
            }
        }
        throw new NullPointerException("User with the given name not found: " + name);
    }

    public void removeUser(int ID) {
        //iterate the list for ID and then remove
        Iterator<User> userToDelete = users.iterator();
        int removeByID;

        while (userToDelete.hasNext()) {
            removeByID = userToDelete.next().getID();
            if (removeByID == ID) {
                userToDelete.remove();
            }
        }
    }

    public void createNewUser(int ID, String name, String surname) {
        User newUser = new User(ID, name, surname);
        users.add(newUser);
    }

    public void createNewUser(int ID, String name, String surname, String birthday, User.Gender gender) {
        User newUser = new User(ID, name, surname, birthday, gender);
        users.add(newUser);
    }

    public void createNewUser(int ID, String name, String surname, String birthday, User.Gender gender, Address address) {
        User newUser = new User(ID, name, surname, birthday, gender, address);
        users.add(newUser);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (users.isEmpty()) {
            sb.append("is empty");
        } else {
            for (User u : users) {
                sb.append(u.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}