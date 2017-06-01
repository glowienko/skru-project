package project.skru_dane_po_warszawsku.utils;


import com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class InVehicleEvents {
    public static final String RECKLESS_DRIVER_COMPLAINT = "Kierowca prowadzi pojazd bardzo niebezpiecznie";
    private static final String OVERFILLED_VEHICLE_COMPLAINT = "Pojazd jest niesamowicie przepełniony";
    private static final String BROKEN_TICKET_MACHINE_COMPLAINT = "Maszyna do biletów nie działa";
    private static final String VEHICLE_BAD_TECHNICAL_CONDITION_COMPLAINT = "Zły stan techniczny pojazdu";
    private static final String GRAFFITI_COMPLAINT = "Dewastacja za pomocą graffiti";
    private static final String FIGHT_IN_VEHICLE = "Bójka w pojeździe";

    private static final String ALCOHOL_IN_THE_VEHICLE = "Picie alkoholu w pojeździe";
    private static final String RECKLESS_DRIVER_CODE = "ip_transportation_003";
    private static final String OVERFILLED_VEHICLE_CODE = "ip_transportation_001";
    private static final String BROKEN_TICKET_MACHINE_CODE = "ip_transportation_004";
    private static final String VEHICLE_BAD_TECHNICAL_CONDITION_CODE = "ip_transportation_005";
    private static final String GRAFFITI_CODE = "ip_damage_004";
    private static final String OTHERS_CODE = "ip_others_001";

    public static final Map<String, String> eventsMap = ImmutableMap.<String, String>builder()
            .put(RECKLESS_DRIVER_COMPLAINT, RECKLESS_DRIVER_CODE)
            .put(OVERFILLED_VEHICLE_COMPLAINT, OVERFILLED_VEHICLE_CODE)
            .put(BROKEN_TICKET_MACHINE_COMPLAINT, BROKEN_TICKET_MACHINE_CODE)
            .put(VEHICLE_BAD_TECHNICAL_CONDITION_COMPLAINT, VEHICLE_BAD_TECHNICAL_CONDITION_CODE)
            .put(GRAFFITI_COMPLAINT, GRAFFITI_CODE)
            .put(FIGHT_IN_VEHICLE, OTHERS_CODE)
            .put(ALCOHOL_IN_THE_VEHICLE, OTHERS_CODE)
            .build();

    public static final List<String> eventsDescriptionsList = Arrays.asList(
            RECKLESS_DRIVER_COMPLAINT,
            OVERFILLED_VEHICLE_COMPLAINT,
            BROKEN_TICKET_MACHINE_COMPLAINT,
            VEHICLE_BAD_TECHNICAL_CONDITION_COMPLAINT,
            GRAFFITI_COMPLAINT,
            FIGHT_IN_VEHICLE,
            ALCOHOL_IN_THE_VEHICLE);
}
