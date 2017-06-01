package project.skru_dane_po_warszawsku.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VehiclesWrapper {

    @JsonProperty("result")
    public ArrayList<Vehicle> vehicles;
}
