package project.skru_dane_po_warszawsku;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import project.skru_dane_po_warszawsku.Location.CurrentLocation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD

        CurrentLocation currentLocation = new CurrentLocation(this);
        currentLocation.getLocation();
=======
>>>>>>> 7aae9fd4405896673b221fc6fb70321bedeca3d4
    }

}

