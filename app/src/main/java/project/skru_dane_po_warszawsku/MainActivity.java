package project.skru_dane_po_warszawsku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Lambda kappa = (int a, int b) -> a + b;
    }

}


interface Lambda{
    int addCap (int a, int b);
}
