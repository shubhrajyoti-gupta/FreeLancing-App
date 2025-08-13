package model;

import java.util.*;

public class Database {
    private static Database instance;
    private Map<String, User> users;
    private List<Job> jobs;

    private Database() {
        this.users = new HashMap<>();
        this.jobs = new ArrayList<>();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public boolean isUsernameExists(String username) {
        return users.containsKey(username);
    }

    public void addJob(Job job) {
        jobs.add(job);
    }

    public List<Job> getAllJobs() {
        return new ArrayList<>(jobs);
    }

    public List<Job> getAvailableJobs() {
        List<Job> availableJobs = new ArrayList<>();
        for (Job job : jobs) {
            if (!job.isAccepted()) {
                availableJobs.add(job);
            }
        }
        return availableJobs;
    }

    public List<Job> getJobsByClient(Client client) {
        List<Job> clientJobs = new ArrayList<>();
        for (Job job : jobs) {
            if (job.getPostedBy().equals(client)) {
                clientJobs.add(job);
            }
        }
        return clientJobs;
    }

    public List<Job> getJobsByFreelancer(Freelancer freelancer) {
        List<Job> freelancerJobs = new ArrayList<>();
        for (Job job : jobs) {
            if (job.getAcceptedBy() != null && job.getAcceptedBy().equals(freelancer)) {
                freelancerJobs.add(job);
            }
        }
        return freelancerJobs;
    }

    public User authenticateUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
