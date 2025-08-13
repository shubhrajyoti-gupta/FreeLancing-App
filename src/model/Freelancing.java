package model;

import java.util.ArrayList;
import java.util.List;

public class Freelancer extends User {
    private List<Job> acceptedJobs;
    private int totalJobsCompleted;

    public Freelancer(String fullName, String username, String country, String city,
                      String phoneNumber, String email, String password) {
        super(fullName, username, country, city, phoneNumber, email, password, "freelancer");
        this.acceptedJobs = new ArrayList<>();
        this.totalJobsCompleted = 0;
    }

    public List<Job> getAcceptedJobs() {
        return acceptedJobs;
    }

    public void addAcceptedJob(Job job) {
        this.acceptedJobs.add(job);
        this.totalJobsCompleted++;
    }

    public int getTotalJobsCompleted() {
        return totalJobsCompleted;
    }

    public void setTotalJobsCompleted(int totalJobsCompleted) {
        this.totalJobsCompleted = totalJobsCompleted;
    }
}
