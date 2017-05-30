package project.skru_dane_po_warszawsku;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;

import project.skru_dane_po_warszawsku.models.Vehicle;
import project.skru_dane_po_warszawsku.models.VehiclesDataHolder;
import project.skru_dane_po_warszawsku.network.HTTPGetRequest;
import project.skru_dane_po_warszawsku.network.RequestCallback;


import project.skru_dane_po_warszawsku.Location.CurrentLocation;

public class MainActivity extends AppCompatActivity {

    private static final String UM_APIKEY = "82176bbc-f673-493b-9574-b9385220056b";
    private static final String UM_WARSZAWA_BUS_API_URL = "https://api.um.warszawa.pl/api/action/busestrams_get/" +
            "?resource_id=f2e5503e-927d-4ad3-9500-4ab9e55deb59&type=1&apikey="+UM_APIKEY;
    private static final String UM_WARSZAWA_TRAM_API_URL = "https://api.um.warszawa.pl/api/action/busestrams_get/" +
            "?resource_id=f2e5503e-927d-4ad3-9500-4ab9e55deb59&type=2&apikey="+UM_APIKEY;
    private ArrayList<Vehicle> vehicles;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CurrentLocation currentLocation = new CurrentLocation(this);
        currentLocation.getLocation();

        Button btnTest = (Button) findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadVehicles();
            }
        });
    }

    private void downloadVehicles() {

        HTTPGetRequest httpGetRequest = new HTTPGetRequest(new RequestCallback<String>() {
            @Override
            public void updateFromResponse(String response) throws IOException {
                vehicles = VehiclesDataHolder.getVehiclesFromJson(response);
                System.out.println();
            }
        });
        httpGetRequest.execute(UM_WARSZAWA_BUS_API_URL);
    }

}

