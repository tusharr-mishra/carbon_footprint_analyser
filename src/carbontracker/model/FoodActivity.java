package carbontracker.model;

import carbontracker.exception.InvalidInputException;
import carbontracker.exception.InvalidActivityDataException;

// since FoodActivity has less emissions so annotation is omitted for it. However it could be placed too.
public class FoodActivity extends Activity {

    private String foodType;
    private int days;

    public FoodActivity(String activityName, double emissionFactor, String category, String foodType, int days) throws InvalidInputException, InvalidActivityDataException {
        super(activityName, emissionFactor, category);

        if (days < 0) {
            throw new InvalidInputException("Days cannot be negative...");
        }

        if (emissionFactor < 0) {
            throw new InvalidInputException("Emission factor cannot be negative...");
        }

        if (foodType == null) {
            throw new InvalidActivityDataException("Food type cannot be empty...");
        }

        if (days > 365 || days > 366) {
            throw new InvalidActivityDataException("Days cannot exceed 365/366...");
        }

        this.foodType = foodType;
        this.days = days;
    }

    @Override
    public double calculateEmission() {
        return emissionFactor * days;                   // emission = emission factor * number of days 
    }

    public String getFoodType() {
        return foodType;
    }

    public int getDays() {
        return days;
    }

    @Override
    public void displayActivity() {
        super.displayActivity();
        System.out.println("Food Type: " + foodType);
        System.out.println("Days: " + days);
        System.out.println("Emission Produced: " + calculateEmission());
    }
}
