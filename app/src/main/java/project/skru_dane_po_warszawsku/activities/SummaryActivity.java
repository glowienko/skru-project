package project.skru_dane_po_warszawsku.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import project.skru_dane_po_warszawsku.R;
import project.skru_dane_po_warszawsku.models.ComplaintRequestForm;

public class SummaryActivity extends AppCompatActivity {

    ComplaintRequestForm requestForm;

    TextView description;
    TextView street;
    TextView coordinates;
    TextView vehicleNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Intent withRequestFormIntent = getIntent();
        requestForm = (ComplaintRequestForm) withRequestFormIntent.getSerializableExtra("requestForm");

        description = (TextView) findViewById(R.id.description);
        street = (TextView) findViewById(R.id.street);
        coordinates = (TextView) findViewById(R.id.coordinates);
        vehicleNumber = (TextView) findViewById(R.id.vehicleNumber);

        fillTextFieldsInUi();
    }

    private void fillTextFieldsInUi() {
        description.setText(requestForm.getDescription());
        street.setText(requestForm.getEventStreet());
        coordinates.setText("( " + requestForm.getLatitude().toString() + ", " + requestForm.getLongitude().toString() + ")");
        vehicleNumber.setText(requestForm.getLine().toString());
    }

    public void sendRequest(View view) {
        //METHOD WIRED TO THE BUTTON
        // HERE SHOULD BE  REQUEST WITH COMPLIANT
    }

    public void rollbackComplaint(View view) {
        this.finish();
    }
}
