import model.*;
import service.*;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static UserService userService = new UserService();
    private static JobService jobService = new JobService();
    private static User currentUser = null;

    public static void main(String[] args) {
        System.out.println("=== Freelancing Platform ===");

        while (true) {
            if (currentUser == null) {
                showMainMenu();
            } else {
                if (currentUser instanceof Freelancer) {
                    showFreelancerMenu((Freelancer) currentUser);
                } else if (currentUser instanceof Client) {
                    showClientMenu((Client) currentUser);
                }
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("\n1. Create Account");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                createAccount();
                break;
            case 2:
                login();
                break;
            case 3:
                System.out.println("Thank you for using our platform!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void createAccount() {
        System.out.println("\nCreate Account");
        System.out.println("1. Create Account as Freelancer");
        System.out.println("2. Create Account as Client");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                createFreelancerAccount();
                break;
            case 2:
                createClientAccount();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void createFreelancerAccount() {
        System.out.println("\n=== Freelancer Registration ===");

        System.out.print("Full Name: ");
        String fullName = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Country: ");
        String country = scanner.nextLine();

        System.out.print("City: ");
        String city = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Confirm Password: ");
        String confirmPassword = scanner.nextLine();

        if (!ValidationService.passwordsMatch(password, confirmPassword)) {
            System.out.println("Passwords do not match!");
            return;
        }

        if (userService.registerFreelancer(fullName, username, country, city,
                                         phoneNumber, email, password)) {
            System.out.println("Freelancer account created successfully!");
        } else {
            System.out.println("Username already exists. Please choose a different username.");
        }
    }

    private static void createClientAccount() {
        System.out.println("\n=== Client Registration ===");

        System.out.print("Full Name: ");
        String fullName = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Country: ");
        String country = scanner.nextLine();

        System.out.print("City: ");
        String city = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Confirm Password: ");
        String confirmPassword = scanner.nextLine();

        System.out.print("Organization Name: ");
        String orgName = scanner.nextLine();

        System.out.print("Organization Place: ");
        String orgPlace = scanner.nextLine();

        System.out.print("Designation: ");
        String designation = scanner.nextLine();

        if (!ValidationService.passwordsMatch(password, confirmPassword)) {
            System.out.println("Passwords do not match!");
            return;
        }

        if (userService.registerClient(fullName, username, country, city,
                                     phoneNumber, email, password,
                                     orgName, orgPlace, designation)) {
            System.out.println("Client account created successfully!");
        } else {
            System.out.println("Username already exists. Please choose a different username.");
        }
    }

    private static void login() {
        System.out.println("\n=== Login ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = userService.login(username, password);
        if (user != null) {
            currentUser = user;
            System.out.println("Login successful! Welcome, " + user.getFullName());
        } else {
            System.out.println("Invalid username or password!");
        }
    }

    private static void showFreelancerMenu(Freelancer freelancer) {
        System.out.println("\n=== Freelancer Dashboard ===");
        System.out.println("Welcome, " + freelancer.getFullName());
        System.out.println("\n1. View Available Jobs");
        System.out.println("2. View My Accepted Jobs");
        System.out.println("3. View Account Details");
        System.out.println("4. Logout");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                viewAvailableJobs(freelancer);
                break;
            case 2:
                viewMyAcceptedJobs(freelancer);
                break;
            case 3:
                viewFreelancerAccountDetails(freelancer);
                break;
            case 4:
                currentUser = null;
                System.out.println("Logged out successfully!");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void viewAvailableJobs(Freelancer freelancer) {
        List<Job> availableJobs = jobService.getAvailableJobs();

        if (availableJobs.isEmpty()) {
            System.out.println("No available jobs at the moment.");
            return;
        }

        System.out.println("\n=== Available Jobs ===");
        for (int i = 0; i < availableJobs.size(); i++) {
            Job job = availableJobs.get(i);
            System.out.println((i + 1) + ". " + job.getTitle() + " - Posted by " +
                             job.getPostedBy().getUsername() + " (Fee: $" + job.getFee() + ")");
        }

        System.out.println((availableJobs.size() + 1) + ". Back to menu");
        System.out.print("Select a job to view details (or back): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice >= 1 && choice <= availableJobs.size()) {
            Job selectedJob = availableJobs.get(choice - 1);
            viewJobDetails(selectedJob, freelancer);
        }
    }

    private static void viewJobDetails(Job job, Freelancer freelancer) {
        System.out.println("\n=== Job Details ===");
        System.out.println("Title: " + job.getTitle());
        System.out.println("Description: " + job.getDescription());
        System.out.println("Fee: $" + job.getFee());
        System.out.println("Posted by: " + job.getPostedBy().getUsername());
        System.out.println("\nClient Details:");
        System.out.println("Name: " + job.getPostedBy().getFullName());
        System.out.println("Organization: " + ((Client)job.getPostedBy()).getOrganizationName());
        System.out.println("Email: " + job.getPostedBy().getEmail());
        System.out.println("Phone: " + job.getPostedBy().getPhoneNumber());

        if (!job.isAccepted()) {
            System.out.println("\n1. Accept Job");
            System.out.println("2. Back");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                if (jobService.acceptJob(job, freelancer)) {
                    System.out.println("Job accepted successfully!");
                } else {
                    System.out.println("Failed to accept job. It might have been taken by someone else.");
                }
            }
        } else {
            System.out.println("\nThis job has already been accepted.");
            System.out.println("Press Enter to go back...");
            scanner.nextLine();
        }
    }

    private static void viewMyAcceptedJobs(Freelancer freelancer) {
        List<Job> acceptedJobs = jobService.getJobsByFreelancer(freelancer);

        if (acceptedJobs.isEmpty()) {
            System.out.println("You haven't accepted any jobs yet.");
            return;
        }

        System.out.println("\n=== My Accepted Jobs ===");
        for (int i = 0; i < acceptedJobs.size(); i++) {
            Job job = acceptedJobs.get(i);
            System.out.println((i + 1) + ". " + job.getTitle() + " - $" + job.getFee());
            System.out.println("   Posted by: " + job.getPostedBy().getUsername());
            System.out.println("   Status: Completed");
            System.out.println();
        }

        System.out.println("Press Enter to go back...");
        scanner.nextLine();
    }

    private static void viewFreelancerAccountDetails(Freelancer freelancer) {
        System.out.println("\n=== Account Details ===");
        System.out.println("Full Name: " + freelancer.getFullName());
        System.out.println("Username: " + freelancer.getUsername());
        System.out.println("Country: " + freelancer.getCountry());
        System.out.println("City: " + freelancer.getCity());
        System.out.println("Phone: " + freelancer.getPhoneNumber());
        System.out.println("Email: " + freelancer.getEmail());
        System.out.println("Total Jobs Completed: " + freelancer.getTotalJobsCompleted());

        System.out.println("\nPress Enter to go back...");
        scanner.nextLine();
    }

    private static void showClientMenu(Client client) {
        System.out.println("\n=== Client Dashboard ===");
        System.out.println("Welcome, " + client.getFullName());
        System.out.println("\n1. Post a New Job");
        System.out.println("2. View My Posted Jobs");
        System.out.println("3. View Other Clients (Limited Info)");
        System.out.println("4. View Account Details");
        System.out.println("5. Logout");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                postNewJob(client);
                break;
            case 2:
                viewMyPostedJobs(client);
                break;
            case 3:
                viewOtherClients(client);
                break;
            case 4:
                viewClientAccountDetails(client);
                break;
            case 5:
                currentUser = null;
                System.out.println("Logged out successfully!");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void postNewJob(Client client) {
        System.out.println("\n=== Post New Job ===");
        System.out.print("Job Title: ");
        String title = scanner.nextLine();

        System.out.print("Job Description: ");
        String description = scanner.nextLine();

        System.out.print("Fee Offered ($): ");
        double fee = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (jobService.createJob(title, description, fee, client)) {
            System.out.println("Job posted successfully!");
        } else {
            System.out.println("Failed to post job. Please check your inputs.");
        }
    }

    private static void viewMyPostedJobs(Client client) {
        List<Job> postedJobs = jobService.getJobsByClient(client);

        if (postedJobs.isEmpty()) {
            System.out.println("You haven't posted any jobs yet.");
            return;
        }

        System.out.println("\n=== My Posted Jobs ===");
        for (int i = 0; i < postedJobs.size(); i++) {
            Job job = postedJobs.get(i);
            System.out.println((i + 1) + ". " + job.getTitle());
            System.out.println("   Description: " + job.getDescription());
            System.out.println("   Fee: $" + job.getFee());
            System.out.println("   Status: " + (job.isAccepted() ? "Accepted by " + job.getAcceptedBy().getUsername() : "Available"));
            if (job.isAccepted()) {
                Freelancer freelancer = job.getAcceptedBy();
                System.out.println("   Accepted by: " + freelancer.getFullName());
                System.out.println("   Freelancer Jobs Completed: " + freelancer.getTotalJobsCompleted());
            }
            System.out.println();
        }

        System.out.println("Press Enter to go back...");
        scanner.nextLine();
    }

    private static void viewOtherClients(Client currentClient) {
        Database database = Database.getInstance();
        System.out.println("\n=== Other Clients ===");

        // This is the fixed line - we need to access the users map properly
        // Since getAllUsers() doesn't exist in original Database, we'll use the existing methods
        for (Job job : database.getAllJobs()) {
            User user = job.getPostedBy();
            if (user instanceof Client && !user.getUsername().equals(currentClient.getUsername())) {
                Client client = (Client) user;
                System.out.println("Username: " + client.getUsername());
                System.out.println("Organization: " + client.getOrganizationName());
                System.out.println("---");
            }
        }

        System.out.println("Press Enter to go back...");
        scanner.nextLine();
    }

    private static void viewClientAccountDetails(Client client) {
        System.out.println("\n=== Account Details ===");
        System.out.println("Full Name: " + client.getFullName());
        System.out.println("Username: " + client.getUsername());
        System.out.println("Country: " + client.getCountry());
        System.out.println("City: " + client.getCity());
        System.out.println("Phone: " + client.getPhoneNumber());
        System.out.println("Email: " + client.getEmail());
        System.out.println("Organization: " + client.getOrganizationName());
        System.out.println("Organization Place: " + client.getOrganizationPlace());
        System.out.println("Designation: " + client.getDesignation());
        System.out.println("Total Jobs Created: " + client.getTotalJobsCreated());

        System.out.println("\nPress Enter to go back...");
        scanner.nextLine();
    }
}
