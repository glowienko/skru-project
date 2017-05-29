package project.skru_dane_po_warszawsku.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by jaroslaw on 29.05.2017.
 */

@Getter
@Setter
public class Vehicle {

    /*
        Lat: 52.2587814,
        Lines: "6",
        Brigade: "3",
        Lon: 21.0023174,
        Time: "2017-05-29 08:51:47"
     */
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


}
