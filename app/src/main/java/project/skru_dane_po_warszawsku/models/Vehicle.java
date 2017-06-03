package project.skru_dane_po_warszawsku.models;

import android.location.Location;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vehicle {

    @JsonProperty("Lat")
    private double latitude;

    @JsonProperty("Lon")
    private double longitude;

    @JsonProperty("Lines")
    private String line;

    @JsonProperty("Brigade")
    private String brigade;

    @JsonProperty("Time")
    private String updateTime;

    public Location getLocation() {
        Location toReturn = new Location("vehicle location provider");
        toReturn.setLatitude(latitude);
        toReturn.setLongitude(longitude);

        return toReturn;
    }
}