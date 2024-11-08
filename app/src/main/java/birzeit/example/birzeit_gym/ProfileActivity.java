package birzeit.example.birzeit_gym;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView nameTextView, emailTextView, genderTextView, interestsTextView, exerciseCategoryTextView;
    private Button logoutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameTextView = findViewById(R.id.nameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        genderTextView = findViewById(R.id.genderTextView);
        interestsTextView = findViewById(R.id.interestsTextView);
        exerciseCategoryTextView = findViewById(R.id.exerciseCategoryTextView);
        logoutButton = findViewById(R.id.logoutButton);

        SharedPreferences sharedPreferences = getSharedPreferences("user_profile", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "N/A");
        String email = sharedPreferences.getString("email", "N/A");
        String gender = sharedPreferences.getString("gender", "N/A");
        String interests = sharedPreferences.getString("interests", "N/A");
        String exerciseCategory = sharedPreferences.getString("exerciseCategory", "N/A");

        nameTextView.setText("Name: " + name);
        emailTextView.setText("Email: " + email);
        genderTextView.setText("Gender: " + gender);
        interestsTextView.setText("Interests: " + interests);
        exerciseCategoryTextView.setText("Exercise Category: " + exerciseCategory);

        logoutButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply();

            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }}



