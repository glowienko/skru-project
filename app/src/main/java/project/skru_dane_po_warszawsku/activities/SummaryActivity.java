package project.skru_dane_po_warszawsku.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import lombok.SneakyThrows;
import project.skru_dane_po_warszawsku.R;
import project.skru_dane_po_warszawsku.models.ComplaintRequestForm;
import project.skru_dane_po_warszawsku.network.HTTPPostRequest;
import project.skru_dane_po_warszawsku.network.RequestCallback;

public class SummaryActivity extends AppCompatActivity {


    private static final String url = "https://apibaas-trial.apigee.net/GASitAPP/skru/complaints";
    ComplaintRequestForm requestForm;

    TextView description;
    TextView coordinates;
    TextView line;
    TextView brigade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Intent withRequestFormIntent = getIntent();
        requestForm = (ComplaintRequestForm) withRequestFormIntent.getSerializableExtra("requestForm");

        description = (TextView) findViewById(R.id.description);
        coordinates = (TextView) findViewById(R.id.coordinates);
        line = (TextView) findViewById(R.id.line);
        brigade = (TextView) findViewById(R.id.brigade);

        fillTextFieldsInUi();
    }

    private void fillTextFieldsInUi() {
        description.setText(requestForm.getDescription());
        coordinates.setText("( " + requestForm.getLatitude().toString() + ", " + requestForm.getLongitude().toString() + ")");
        brigade.setText(requestForm.getBrigade());
        line.setText(requestForm.getLine());
    }

    @SneakyThrows(JsonProcessingException.class)
    public void sendRequest(View view) {
        HTTPPostRequest httpPostRequest = new HTTPPostRequest(new RequestCallback<String>() {
            @Override
            public void updateFromResponse(String response) throws IOException {
                String responseCode = response;
                finish();
            }
        });
        ObjectMapper objectMapper = new ObjectMapper();
        httpPostRequest.execute(url, "POST", objectMapper.writeValueAsString(requestForm));

    }

    public void rollbackComplaint(View view) {
        finish();
    }
}
