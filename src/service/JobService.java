package service;

import model.*;
import java.util.List;

public class JobService {
    private Database database;

    public JobService() {
        this.database = Database.getInstance();
    }

    public boolean createJob(String title, String description, double fee, Client client) {
        if (title == null || title.trim().isEmpty() ||
            description == null || description.trim().isEmpty() ||
            fee <= 0) {
            return false;
        }

        Job job = new Job(title, description, fee, client);
        database.addJob(job);
        client.addPostedJob(job);
        return true;
    }

    public List<Job> getAvailableJobs() {
        return database.getAvailableJobs();
    }

    public boolean acceptJob(Job job, Freelancer freelancer) {
        if (job.isAccepted()) {
            return false; // Job already accepted
        }

        job.setAccepted(true);
        job.setAcceptedBy(freelancer);
        freelancer.addAcceptedJob(job);
        return true;
    }

    public List<Job> getJobsByClient(Client client) {
        return database.getJobsByClient(client);
    }

    public List<Job> getJobsByFreelancer(Freelancer freelancer) {
        return database.getJobsByFreelancer(freelancer);
    }
}
