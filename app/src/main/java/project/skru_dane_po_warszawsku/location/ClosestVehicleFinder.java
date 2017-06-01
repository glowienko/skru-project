package project.skru_dane_po_warszawsku.location;


import lombok.NoArgsConstructor;

/**
 * Class has method which will find in which public transport vehicle is currently user
 * based on current position and position of the closes vehicle
 */
@NoArgsConstructor
public class ClosestVehicleFinder {

    public Long findCurrentUserVehicleNumber() {

        // todo: find the mean of transport ( number of line eg. 10) in which user is currently

        return Long.valueOf(10);
    }
}
