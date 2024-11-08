package birzeit.example.birzeit_gym;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText, passwordEditText;
    private Spinner categorySpinner;
    private RadioGroup genderRadioGroup;
    private CheckBox checkBoxSport, checkBoxMusic;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        categorySpinner = findViewById(R.id.categorySpinner);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        checkBoxSport = findViewById(R.id.checkBoxSport);
        checkBoxMusic = findViewById(R.id.checkBoxMusic);
        submitButton = findViewById(R.id.submitButton);

        populateCategorySpinner();

        submitButton.setOnClickListener(v -> onSubmit());
    }

    private void populateCategorySpinner() {
        String[] categories = {"Select your Training ", "Stretching", "Strength Training", "Cardio", "Balance exercises"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categorySpinner.setAdapter(adapter);
    }

    private void onSubmit() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        String exerciseCategory = categorySpinner.getSelectedItem().toString();

        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        String gender = selectedGenderId != -1 ? ((RadioButton) findViewById(selectedGenderId)).getText().toString() : "N/A";

        List<String> interests = new ArrayList<>();
        if (checkBoxSport.isChecked()) interests.add("Sport");
        if (checkBoxMusic.isChecked()) interests.add("Full body");
        String interestsString = interests.isEmpty() ? "N/A" : String.join(", ", interests);

        SharedPreferences sharedPreferences = getSharedPreferences("user_profile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putString("gender", gender);
        editor.putString("interests", interestsString);
        editor.putString("exerciseCategory", exerciseCategory);
        editor.apply();

        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
