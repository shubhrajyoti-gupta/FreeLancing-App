package service;

import model.*;

public class UserService {
    private Database database;

    public UserService() {
        this.database = Database.getInstance();
    }

    public boolean registerFreelancer(String fullName, String username, String country, String city,
                                    String phoneNumber, String email, String password) {
        if (database.isUsernameExists(username)) {
            return false; // Username already exists
        }

        Freelancer freelancer = new Freelancer(fullName, username, country, city,
                                             phoneNumber, email, password);
        database.addUser(freelancer);
        return true;
    }

    public boolean registerClient(String fullName, String username, String country, String city,
                                String phoneNumber, String email, String password,
                                String organizationName, String organizationPlace, String designation) {
        if (database.isUsernameExists(username)) {
            return false; // Username already exists
        }

        Client client = new Client(fullName, username, country, city, phoneNumber,
                                 email, password, organizationName, organizationPlace, designation);
        database.addUser(client);
        return true;
    }

    public User login(String username, String password) {
        return database.authenticateUser(username, password);
    }
}
