package project.skru_dane_po_warszawsku.models;


import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ComplaintRequestForm {

    @JsonProperty(value = "id")
    String resourceId;

    @JsonProperty(value = "apikey")
    String apiKey;

    String description;

    @JsonProperty(value = "street")
    String eventStreet; //mandatory

    @JsonProperty(value = "xCoordWGS84")
    Double latitude; // szerokość geograficzna

    @JsonProperty(value = "yCoordWGS84")
    Double longitude; //długość geograficzna

    //============= below not mandatory fields
    @Nullable
    String eventType; // from event types. eg. ip_transportation_004

    @Nullable
    String subcategory; //always == transportation

    @Nullable
    String email; //probably not mandatory

    @Nullable
    String name; //probably not mandatory

    @Nullable
    String lastName; //probably not mandatory

    @Nullable
    Integer phoneNumber; // probably not mandatory

}
