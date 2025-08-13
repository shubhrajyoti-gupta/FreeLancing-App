package model;

import java.util.ArrayList;
import java.util.List;

public class Client extends User {
    private String organizationName;
    private String organizationPlace;
    private String designation;
    private List<Job> postedJobs;
    private int totalJobsCreated;

    public Client(String fullName, String username, String country, String city,
                  String phoneNumber, String email, String password,
                  String organizationName, String organizationPlace, String designation) {
        super(fullName, username, country, city, phoneNumber, email, password, "client");
        this.organizationName = organizationName;
        this.organizationPlace = organizationPlace;
        this.designation = designation;
        this.postedJobs = new ArrayList<>();
        this.totalJobsCreated = 0;
    }

    // Getters and Setters for client-specific fields
    public String getOrganizationName() { return organizationName; }
    public void setOrganizationName(String organizationName) { this.organizationName = organizationName; }

    public String getOrganizationPlace() { return organizationPlace; }
    public void setOrganizationPlace(String organizationPlace) { this.organizationPlace = organizationPlace; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public List<Job> getPostedJobs() {
        return postedJobs;
    }

    public void addPostedJob(Job job) {
        this.postedJobs.add(job);
        this.totalJobsCreated++;
    }

    public int getTotalJobsCreated() {
        return totalJobsCreated;
    }

    public void setTotalJobsCreated(int totalJobsCreated) {
        this.totalJobsCreated = totalJobsCreated;
    }
}
