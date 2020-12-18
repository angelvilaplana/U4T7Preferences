package dam.android.angelvilaplana.u4t7preferences;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private final String MY_PREFS = "MyPrefs";
    private EditText etPlayerName;
    private Spinner spinnerLevel;
    private EditText etScore;
    private Button btQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
    }

    public void setUI() {
        etPlayerName = findViewById(R.id.etPlayerName);

        // Level spinner, set adapter from string-array resources
        spinnerLevel = findViewById(R.id.spinnerLevel);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.levels,
                                                                            android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(spinnerAdapter);

        etScore = findViewById(R.id.etScore);

        btQuit = findViewById(R.id.btQuit);
        btQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // Save activity data to preferences file
    @Override
    protected void onPause() {
        super.onPause();

        // Get preference file
        SharedPreferences myPreferences = getSharedPreferences(MY_PREFS, MODE_PRIVATE);

        // Save UI data to file
        SharedPreferences.Editor editor = myPreferences.edit();

        editor.putString("PlayerName", etPlayerName.getText().toString());
        editor.putInt("Level", spinnerLevel.getSelectedItemPosition());
        editor.putInt("Score", Integer.parseInt(etScore.getText().toString()));

        editor.commit();
    }

    // Read activity data from preferences file
    @Override
    protected void onResume() {
        super.onResume();

        // Get preferences file
        SharedPreferences myPreferences = getSharedPreferences(MY_PREFS, MODE_PRIVATE);

        // Set UI values reading data from file
        etPlayerName.setText(myPreferences.getString("PlayerName", "unknown"));
        spinnerLevel.setSelection(myPreferences.getInt("Level", 0));
        etScore.setText(String.valueOf(myPreferences.getInt("Score", 0)));
    }
}