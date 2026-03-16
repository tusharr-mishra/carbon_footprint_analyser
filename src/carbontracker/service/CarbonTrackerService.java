package carbontracker.service;

import java.util.ArrayList;                 // a collection framework
import java.util.List;
import carbontracker.model.Activity;
import java.util.Queue;
import java.util.LinkedList;

public class CarbonTrackerService {

    private List<Activity> activityList;                    // List to store all activities demonstrating Polymorphism using Activity type
    private Queue<Activity> activityQueue;                  //Queue used to process activities in FIFO

    public CarbonTrackerService() {
        activityList = new ArrayList<>();                   // Dynamic growth of an array                  
        activityQueue = new LinkedList<>();                 // Queue implementation using linked list 
    }

    public void addActivity(Activity activity) {
        activityQueue.add(activity);
        activityList.add(activity);
    }

    public void displayAllActivities() {
        if (activityList.isEmpty()) {
            System.out.println("No activities recorded...");
            return;
        }
        for (Activity activity : activityList) {
            System.out.println("-------------------");
            activity.displayActivity();                 // each object will call its own displayActivity
        }
    }

    public synchronized double calculateTotalEmission() // method dispatch, where each class calls its own formula but within same method and one thread access at a time
    {                                                                       // synchronisation stops multiple access to activityList and protects it (shared resource concept)   
        double total = 0;

        for (Activity activity : activityList) {
            total += activity.calculateEmission();
        }
        return total;
    }

    public void processQueue() {
        if (activityQueue.isEmpty()) {
            System.out.println("No activities in queue.");
            return;
        }
        System.out.println("Processing activities in FIFO order:");
        while (!activityQueue.isEmpty()) {
            Activity activity = activityQueue.poll();                   // poll() retrieves and removes the first queue element and hence used here to display if non empty. 
            activity.displayActivity();
        }
    }
}
