package project.skru_dane_po_warszawsku.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import project.skru_dane_po_warszawsku.R;
import project.skru_dane_po_warszawsku.database.ProfilesDatabaseHelper;
import project.skru_dane_po_warszawsku.models.UserProfile;

public class UserProfileActivity extends AppCompatActivity {

    private ProfilesDatabaseHelper dbHelper;
    private UserProfile userProfile;

    private EditText name;
    private EditText lastName;
    private EditText email;
    private EditText phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        dbHelper = new ProfilesDatabaseHelper(this);

        userProfile = dbHelper.getAllProfiles();

        name = (EditText) findViewById(R.id.name);
        lastName = (EditText) findViewById(R.id.last_name);
        email = (EditText) findViewById(R.id.mail);
        phoneNumber = (EditText) findViewById(R.id.phone_number);

        setProfileToFields();
    }

    private void setProfileToFields() {
        if (userProfile != null) {
            try {
                name.setText(userProfile.getName());
                lastName.setText(userProfile.getLastName());
                email.setText(userProfile.getEmail());
                phoneNumber.setText(userProfile.getPhoneNumber());
            } catch (NullPointerException e) {
                Log.d("PROFILE", "missing some field in profile");
            }

        }
    }

    public void backToMain(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void addProfile(View view) {
        userProfile.setName(String.valueOf(name.getText()));
        userProfile.setLastName(String.valueOf(lastName.getText()));
        userProfile.setEmail(String.valueOf(email.getText()));
        userProfile.setPhoneNumber(Integer.valueOf(String.valueOf(phoneNumber.getText())));

        dbHelper.addProfile(userProfile);
    }


}
