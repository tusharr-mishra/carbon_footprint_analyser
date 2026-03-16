package carbontracker.model;

import carbontracker.exception.InvalidInputException;
import carbontracker.exception.InvalidActivityDataException;
import carbontracker.annotation.HighEmission;

@HighEmission
public class EnergyActivity extends Activity {

    private double unitsConsumed;

    public EnergyActivity(String activityName, double emissionFactor, String category, double unitsConsumed) throws InvalidInputException, InvalidActivityDataException {
        super(activityName, emissionFactor, category);

        if (unitsConsumed < 0) {
            throw new InvalidInputException("Units consumed cannot be negative...");
        }

        if (emissionFactor < 0) {
            throw new InvalidInputException("Emission Factor cannot be negative...");
        }

        if (unitsConsumed > 10000) {
            throw new InvalidActivityDataException("Units are unusally high...");
        }

        this.unitsConsumed = unitsConsumed;
    }

    @Override
    public double calculateEmission() {
        return unitsConsumed * emissionFactor;                  // emission = units * emission factor
    }

    public double getUnitsConsumed() {
        return unitsConsumed;
    }

    @Override
    public void displayActivity() {
        super.displayActivity();
        System.out.println("Electricity Units: " + unitsConsumed);
        System.out.println("Emission Produced: " + calculateEmission());
    }
}
