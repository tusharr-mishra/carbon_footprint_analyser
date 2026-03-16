package carbontracker.model;

import carbontracker.service.EmissionCalculable;

public abstract class Activity implements EmissionCalculable {              // class & interface 

    protected String activityName;
    protected final double emissionFactor;              // taken input by user and is then fixed for that particular vehicle
    protected String category;

    public Activity(String activityName, double emissionFactor, String category) {
        this.activityName = activityName;
        this.emissionFactor = emissionFactor;
        this.category = category;
    }

    public abstract double calculateEmission();

    public String getActivityName()             // getters are used so that other classes can access the variables (encapsulation)
    {
        return activityName;
    }

    public double getEmissionFactor() {
        return emissionFactor;
    }

    public String getCategory() {
        return category;
    }

    public void displayActivity() {
        System.out.println("Activity Name: " + activityName);
        System.out.println("Emission Factor: " + emissionFactor);
        System.out.println("Category: " + category);
    }
}
