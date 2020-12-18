package dam.android.angelvilaplana.u4t7preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // Activity 2 - New preferences to get the chosen preference
    private Preferences preferences;

    // Activity 1 - Spinner to change the background color
    private Spinner spinnerBackground;

    private EditText etPlayerName;
    private Spinner spinnerLevel;
    private EditText etScore;

    // Activity 1 - Save RadioButtons item selected
    private RadioGroup rbPreferences;
    private RadioGroup rbDifficulty;

    // Activity 1 - CheckBox
    private CheckBox cbSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Activity 2 - Set preferences
        preferences = new Preferences(this);
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

        // Activity 1 - Spinner to change the background color
        spinnerBackground = findViewById(R.id.spinnerBackground);
        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.backgroundColor,
                android.R.layout.simple_spinner_item);
        spinnerBackground.setAdapter(spinnerAdapter);
        spinnerBackground.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                view = getWindow().getDecorView();
                switch (position) {
                    case 0:
                        view.setBackgroundColor(Color.WHITE);
                        break;
                    case 1:
                        view.setBackgroundColor(Color.RED);
                        break;
                    case 2:
                        view.setBackgroundColor(Color.BLUE);
                        break;
                    case 3:
                        view.setBackgroundColor(Color.GREEN);
                        break;
                    case 4:
                        view.setBackgroundColor(Color.rgb(255, 165,0));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Activity 1 - Set RadioButtons
        rbDifficulty = findViewById(R.id.rbDifficulty);
        rbPreferences = findViewById(R.id.rbPreferences);

        // Activity 1 - Set checkbox
        cbSound = findViewById(R.id.cbSound);

        // Activity 2 - Replace button "Quit" by "Show Preferences"
        Button btShowPreferences = findViewById(R.id.btShowPreferences);
        btShowPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewActivity.class);
                startActivity(intent);
            }
        });
    }

    // Save activity data to preferences file
    @Override
    protected void onPause() {
        super.onPause();

        // Activity 2 - Save mode preferences
        SharedPreferences.Editor editor = preferences.getModePrefs().edit();
        editor.putInt("Mode", rbPreferences.getCheckedRadioButtonId());
        editor.commit();

        // Activity 2 - Modify other SharedPreferences
        // Get preferences file
        SharedPreferences sharedPreferences = preferences.getActualPreferences();
        if (sharedPreferences == null) {
            return;
        }

        // Save UI data to file
        editor = sharedPreferences.edit();
        editor.putString("PlayerName", etPlayerName.getText().toString());
        editor.putInt("Level", spinnerLevel.getSelectedItemPosition());
        editor.putInt("Score", Integer.parseInt(etScore.getText().toString()));
        editor.putInt("Background", spinnerBackground.getSelectedItemPosition());
        editor.putInt("Difficulty", rbDifficulty.getCheckedRadioButtonId());
        editor.putBoolean("SoundActive", cbSound.isChecked());
        editor.commit();
    }

    // Read activity data from preferences file
    @Override
    protected void onResume() {
        super.onResume();

        // Activity 2 - Modify other SharedPreferences
        // Get preferences file
        SharedPreferences sharedPreferences = preferences.getActualPreferences();
        if (sharedPreferences == null) {
            return;
        }

        // Set UI values reading data from file
        etPlayerName.setText(sharedPreferences.getString("PlayerName", "unknown"));
        spinnerLevel.setSelection(sharedPreferences.getInt("Level", 0));
        etScore.setText(String.valueOf(sharedPreferences.getInt("Score", 0)));
        rbPreferences.check(preferences.getModePrefs().getInt("Mode" , R.id.rbGetDefaultSharedPreferences));
        spinnerBackground.setSelection(sharedPreferences.getInt("Background" , 0));
        rbDifficulty.check(sharedPreferences.getInt("Difficulty" , R.id.rbEasy));
        cbSound.setChecked(sharedPreferences.getBoolean("SoundActive", true));
    }

}
