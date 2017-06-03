package project.skru_dane_po_warszawsku.location;



import android.location.Location;

import java.util.List;

import lombok.NoArgsConstructor;
import project.skru_dane_po_warszawsku.models.Vehicle;

/**
 * Class has method which will find in which public transport vehicle is currently user
 * based on current position and position of the closes vehicle
 */
@NoArgsConstructor
public class ClosestVehicleFinder {

    public static Vehicle findCurrentUserVehicleNumber(List<Vehicle> vehicles, Location currentLocation) {


        Vehicle nearestVehicle = null;
        float shortestDistance = -1;
        for (Vehicle vehicle : vehicles) {
            float distance = currentLocation.distanceTo(vehicle.getLocation());

            if (shortestDistance == -1 || distance < shortestDistance) {
                shortestDistance = distance;
                nearestVehicle = vehicle;
            }
        }
        return nearestVehicle;

    }
}
