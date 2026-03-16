package carbontracker.model;

import carbontracker.exception.InvalidInputException;
import carbontracker.exception.InvalidActivityDataException;
import carbontracker.annotation.HighEmission;

@HighEmission
public class TransportActivity extends Activity {

    private final String vehicleType;                   // vehicleType is taken as input and further made final for that vehicle
    private double distance;

    public TransportActivity(String activityName, double emissionFactor, String category, String vehicleType, double distance) throws InvalidInputException, InvalidActivityDataException {
        super(activityName, emissionFactor, category);

        if (distance < 0) {
            throw new InvalidInputException("Distance cannot be negative...");
        }

        if (emissionFactor < 0) {
            throw new InvalidInputException("Emission Factor cannot be negative...");
        }

        if (vehicleType == null) {
            throw new InvalidActivityDataException("Vehicle type cannot be empty...");
        }

        this.vehicleType = vehicleType;
        this.distance = distance;
    }

    @Override
    public double calculateEmission() {
        return distance * emissionFactor;                   // emission = distance * emission factor 
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public void displayActivity() {
        super.displayActivity();
        System.out.println("Vehicle Type: " + vehicleType);
        System.out.println("Distance Travelled: " + distance);
        System.out.println("Emission Produced: " + calculateEmission());
    }
}
