package project.skru_dane_po_warszawsku.utils;


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

    String desctiption;

    String email;

    String eventType;

    String subcategory;


}
