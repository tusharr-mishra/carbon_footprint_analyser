package carbontracker.model;

import carbontracker.exception.InvalidInputException;
import carbontracker.exception.InvalidActivityDataException;

public class User {

    private String name;
    private int age;
    private String country;
    private final int userId;
    private static int userCounter = 1;

    public User(String name, int age, String country) throws InvalidInputException, InvalidActivityDataException {
        if (age < 0) {
            throw new InvalidInputException("Age cannot be negative...");
        }

        if (name == null) {
            throw new InvalidActivityDataException("Name cannot be empty...");
        }

        if (country == null) {
            throw new InvalidActivityDataException("Country cannot be empty...");
        }

        this.name = name;
        this.age = age;
        this.country = country;
        this.userId = userCounter;
        userCounter++;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public int getUserId() {
        return userId;
    }

    public void displayUser() {
        System.out.println("User ID: " + userId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Country: " + country);
    }
}
