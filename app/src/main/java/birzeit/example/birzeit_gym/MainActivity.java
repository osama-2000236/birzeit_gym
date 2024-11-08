package birzeit.example.birzeit_gym;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView welcomeTextView;
    private Button loginLogoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeTextView = findViewById(R.id.welcomeTextView);
        loginLogoutButton = findViewById(R.id.loginLogoutButton);

        SharedPreferences sharedPreferences = getSharedPreferences("user_profile", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            loginLogoutButton.setText("Logout");
            welcomeTextView.setText("Welcome to Birzeit Gym!");
        } else {
            loginLogoutButton.setText("Login");
            welcomeTextView.setText("Please log in");
        }

        loginLogoutButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (isLoggedIn) {
                editor.putBoolean("isLoggedIn", false); // Log out
            }
            editor.apply();

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
