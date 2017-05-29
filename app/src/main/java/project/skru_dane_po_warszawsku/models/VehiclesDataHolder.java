package project.skru_dane_po_warszawsku.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaroslaw on 29.05.2017.
 */

public class VehiclesDataHolder {

    @JsonProperty("result")
    public ArrayList<Vehicle> vehicles;

    public static ArrayList<Vehicle> getVehiclesFromJson(String json) throws IOException {
        VehiclesDataHolder dataHolder = new ObjectMapper().readValue(json, VehiclesDataHolder.class);

        return dataHolder.vehicles;
    }
}
