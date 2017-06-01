package project.skru_dane_po_warszawsku.activities;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.util.ArrayList;

import project.skru_dane_po_warszawsku.R;
import project.skru_dane_po_warszawsku.location.ClosestVehicleFinder;
import project.skru_dane_po_warszawsku.location.CurrentLocation;
import project.skru_dane_po_warszawsku.models.Vehicle;
import project.skru_dane_po_warszawsku.models.VehiclesWrapper;
import project.skru_dane_po_warszawsku.network.HTTPGetRequest;
import project.skru_dane_po_warszawsku.network.RequestCallback;
import project.skru_dane_po_warszawsku.utils.InVehicleEvents;

public class MainActivity extends AppCompatActivity {

    private static final String UM_APIKEY = "82176bbc-f673-493b-9574-b9385220056b";
    private static final String UM_WARSZAWA_BUS_API_URL = "https://api.um.warszawa.pl/api/action/busestrams_get/" +
            "?resource_id=f2e5503e-927d-4ad3-9500-4ab9e55deb59&type=1&apikey=" + UM_APIKEY;
    private static final String UM_WARSZAWA_TRAM_API_URL = "https://api.um.warszawa.pl/api/action/busestrams_get/" +
            "?resource_id=f2e5503e-927d-4ad3-9500-4ab9e55deb59&type=2&apikey=" + UM_APIKEY;
    private ArrayList<Vehicle> vehicles;

    private GoogleApiClient googleApiClient;
    private CurrentLocation currentLocation;

    private ListView eventsToComplainList;
    private ArrayAdapter<String> eventsListAdapter;

    private ClosestVehicleFinder vehicleFinder;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vehicleFinder = new ClosestVehicleFinder();
        eventsToComplainList = (ListView) findViewById(R.id.eventsList);
        populateEventsToComplainList();
        addChoosingEventListener();
        currentLocation = new CurrentLocation(this);
        googleApiClient = currentLocation.buildGoogleApiClient();
    }

    private void addChoosingEventListener() {
        eventsToComplainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                handleUserRequest((String) adapter.getItemAtPosition(position));
            }
        });
    }

    //users clicked on event and want to make a complaint or intervention is needed
    private void handleUserRequest(String choosenEventName) {


    }

    public void goToProfile(View view) {
        startActivity(new Intent(this, UserProfileActivity.class));
    }

    private void populateEventsToComplainList() {
        eventsListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, InVehicleEvents.eventsDescriptionsList);
        eventsToComplainList.setAdapter(eventsListAdapter);
    }


    public void test(View view) {
        Location location = currentLocation.getLocation();
        downloadVehicles();
    }

    private void downloadVehicles() {
        HTTPGetRequest httpGetRequest = new HTTPGetRequest(new RequestCallback<String>() {
            @Override
            public void updateFromResponse(String response) throws IOException {
                vehicles = objectMapper.readValue(response, VehiclesWrapper.class).getVehicles();
                System.out.println();
            }
        });
        httpGetRequest.execute(UM_WARSZAWA_BUS_API_URL);
    }

    @Override
    public void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (googleApiClient.isConnected())
            currentLocation.startLocationUpdates();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (googleApiClient.isConnected())
            currentLocation.stopLocationUpdates();
    }


}
