package project.skru_dane_po_warszawsku.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import project.skru_dane_po_warszawsku.R;
import project.skru_dane_po_warszawsku.models.ComplaintRequestForm;

public class SummaryActivity extends AppCompatActivity {


    ComplaintRequestForm requestForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Intent withRequestFormIntent = getIntent();
        requestForm = (ComplaintRequestForm) withRequestFormIntent.getSerializableExtra("requestForm");


    }

    public void sendRequest(View view) {
        //METHOD WIRED TO THE BUTTON
        // HERE SHOULD BE  REQUEST WITH COMPLIANT
    }
}
