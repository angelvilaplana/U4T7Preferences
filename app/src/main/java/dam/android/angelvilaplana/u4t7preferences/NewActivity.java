package dam.android.angelvilaplana.u4t7preferences;

import android.content.SharedPreferences;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        setUI();
    }

    public void setUI() {
        TextView tvInfo = findViewById(R.id.tvInfo);

        Preferences preferences = new Preferences(getApplicationContext());
        SharedPreferences sharedPreferences = preferences.getActualPreferences();
        if (sharedPreferences == null) {
            tvInfo.setText(R.string.tvInfoEmpty);
            return;
        }

        String playerName = sharedPreferences.getString("PlayerName", "unknown");
        int level = sharedPreferences.getInt("Level", 0);
        int score = sharedPreferences.getInt("Score", 0);
        int preferencesMode = preferences.getModePrefs().getInt("Mode" , R.id.rbGetDefaultSharedPreferences);
        int color = sharedPreferences.getInt("Background" , 0);
        int difficulty = sharedPreferences.getInt("Difficulty" , R.id.rbEasy);
        boolean soundActive = sharedPreferences.getBoolean("SoundActive", true);


        tvInfo.setText("PLAYER: " + playerName +
                "\nLEVEL: " + level +
                "\nSCORE: " + score +
                "\nPREFERENCE MODE: " + getPreferencesString(preferencesMode) +
                "\nCOLOR: " + getResources().getStringArray(R.array.backgroundColor)[color] +
                "\nDIFFICULTY: " + getDifficultyString(difficulty) +
                "\nSOUND: " + soundActive);
    }

    private String getPreferencesString(int id) {
        if (id == R.id.rbGetDefaultSharedPreferences) {
            return getString(R.string.rbGetDefaultSharedPreferences);
        } else if (id == R.id.rbGetPreferences) {
            return getString(R.string.rbGetPreferences);
        } else if (id == R.id.rbGetSharedPreferences) {
            return  getString(R.string.rbGetSharedPreferences);
        }
        return null;
    }

    private String getDifficultyString(int id) {
        if (id == R.id.rbEasy) {
            return getString(R.string.rbEasy);
        } else if (id == R.id.rbNormal) {
            return getString(R.string.rbNormal);
        } else if (id == R.id.rbHard) {
            return  getString(R.string.rbHard);
        } else if (id == R.id.rbVeryHard) {
            return getString(R.string.rbVeryHard);
        } else if (id == R.id.rbExpert) {
            return  getString(R.string.rbExpert);
        }
        return null;
    }

}
