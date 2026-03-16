package carbontracker.main;

import java.util.Scanner;
import java.util.InputMismatchException;
import carbontracker.model.*;
import carbontracker.service.CarbonTrackerService;
import carbontracker.exception.*;
import carbontracker.thread.ReportGeneratorThread;
import carbontracker.service.EmissionCalculable;
import carbontracker.util.FileUtil;
import carbontracker.ui.CarbonUI;

public class CarbonTrackerApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Mode:");
        System.out.println("1 Terminal Mode");
        System.out.println("2 GUI Mode");
        System.out.print("Enter the specificied mode of display:");
        int mode = sc.nextInt();
        sc.nextLine();
        if (mode == 2) {
            new CarbonUI();
            return;
        }
        CarbonTrackerService service = new CarbonTrackerService();
        User user = null;                   // created to make it accessible after object creation and exception handling (a reference)

        try {
            System.out.println("Enter User Name: ");
            String name = sc.nextLine();
            System.out.println("Enter Age: ");
            int age = sc.nextInt();
            sc.nextLine();                  // clear input buffer
            System.out.println("Enter Country: ");
            String country = sc.nextLine();
            user = new User(name, age, country);
        } catch (InvalidInputException | InvalidActivityDataException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {                    //built in 
            System.out.println("Invalid Input Type...");
        } finally {
            System.out.println("User creation attempt finished...");
        }

        int choice = 0;
        do {
            System.out.println("\n===== Carbon Tracker Menu =====");
            System.out.println("1 Add Transport Activity");
            System.out.println("2 Add Energy Activity");
            System.out.println("3 Add Food Activity");
            System.out.println("4 Display Activities");
            System.out.println("5 Total Emission");
            System.out.println("6 Exit");
            choice = sc.nextInt();

            try {
                if (choice == 1) {
                    sc.nextLine();
                    System.out.println("Vehicle type:");
                    String vehicle = sc.nextLine();
                    System.out.println("Distance:");
                    double distance = sc.nextDouble();
                    System.out.println("Emission factor:");
                    double factor = sc.nextDouble();
                    TransportActivity t = new TransportActivity("Transport", factor, "Transport", vehicle, distance);
                    service.addActivity(t);
                } else if (choice == 2) {
                    System.out.println("Units consumed:");
                    double units = sc.nextDouble();
                    System.out.println("Emission factor:");
                    double factor = sc.nextDouble();
                    EnergyActivity e = new EnergyActivity("Energy", factor, "Energy", units);
                    service.addActivity(e);
                } else if (choice == 3) {
                    sc.nextLine();
                    System.out.println("Food type:");
                    String type = sc.nextLine();
                    System.out.println("Days:");
                    int days = sc.nextInt();
                    System.out.println("Emission factor:");
                    double factor = sc.nextDouble();
                    FoodActivity f = new FoodActivity("Food", factor, "Food", type, days);
                    service.addActivity(f);
                } else if (choice == 4) {
                    service.displayAllActivities();
                } else if (choice == 5) {
                    ReportGeneratorThread t = new ReportGeneratorThread(service);
                    t.start();
                    EmissionCalculable lambdaEmission = () -> service.calculateTotalEmission();
                    System.out.println("Lambda Calculated Emission: " + lambdaEmission.calculateEmission());
                    FileUtil.saveReport(service.calculateTotalEmission());
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong Input Type...");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (choice != 6);
        System.out.println("Program ended.");
    }
}
