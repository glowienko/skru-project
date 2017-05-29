package project.skru_dane_po_warszawsku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import project.skru_dane_po_warszawsku.Location.CurrentLocation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CurrentLocation currentLocation = new CurrentLocation(this);
        currentLocation.getLocation();
    }

}

