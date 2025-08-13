package model;

public class Job {
    private String title;
    private String description;
    private double fee;
    private Client postedBy;
    private boolean isAccepted;
    private Freelancer acceptedBy;
    private String jobId;

    public Job(String title, String description, double fee, Client postedBy) {
        this.title = title;
        this.description = description;
        this.fee = fee;
        this.postedBy = postedBy;
        this.isAccepted = false;
        this.jobId = "JOB_" + System.currentTimeMillis();
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getFee() { return fee; }
    public void setFee(double fee) { this.fee = fee; }

    public Client getPostedBy() { return postedBy; }
    public void setPostedBy(Client postedBy) { this.postedBy = postedBy; }

    public boolean isAccepted() { return isAccepted; }
    public void setAccepted(boolean accepted) { isAccepted = accepted; }

    public Freelancer getAcceptedBy() { return acceptedBy; }
    public void setAcceptedBy(Freelancer acceptedBy) { this.acceptedBy = acceptedBy; }

    public String getJobId() { return jobId; }
    public void setJobId(String jobId) { this.jobId = jobId; }
}
