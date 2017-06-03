package project.skru_dane_po_warszawsku.models;


import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintRequestForm implements Serializable {

    @JsonProperty(value = "id")
    private String resourceId;

    @JsonProperty(value = "apikey")
    private String apiKey;

    private String description;

    @JsonProperty(value = "event")
    private String eventTypeCode; // from event types. eg. ip_transportation_004

    private String subcategory; //always == transportation

    @JsonProperty(value = "street")
    private String eventStreet;

    @JsonProperty(value = "xCoordWGS84")
    private Double latitude; // szerokość geograficzna

    @JsonProperty(value = "yCoordWGS84")
    private Double longitude; //długość geograficzna

    private String line;

    private String brigade;

    private String updateTime;


    //============= below not mandatory fields
    @Nullable
    private String email;

    @Nullable
    private String name;

    @Nullable
    private String lastName;

    @Nullable
    private Integer phoneNumber;

}
