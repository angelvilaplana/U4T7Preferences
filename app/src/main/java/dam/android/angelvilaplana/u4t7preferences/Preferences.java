package dam.android.angelvilaplana.u4t7preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences  {

    private SharedPreferences modePrefs;

    private Activity activity;

    private Context context;

    public Preferences(Activity activity) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
    }

    public Preferences(Context context) {
        this.context = context;
    }

    public SharedPreferences getActualPreferences() {
        // Activity 2 - New preferences to get the chosen preference
        modePrefs = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        int preferenceSelect = modePrefs.getInt("Mode", R.id.rbGetSharedPreferences);
        if (preferenceSelect == R.id.rbGetSharedPreferences) {
            // Get "shared_prefs" as "Prefs" = Name put in the first parameter
            return context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        } else if (preferenceSelect == R.id.rbGetPreferences && activity != null) {
            // Get "shared_prefs" as "MainActivity" = Name Class
            return activity.getPreferences(Context.MODE_PRIVATE);
        }  else if (preferenceSelect == R.id.rbGetPreferences) {
            // Get "shared_prefs" as "MainActivity" = Name Class
            return context.getSharedPreferences("MainActivity", Context.MODE_PRIVATE);
        } else if (preferenceSelect == R.id.rbGetDefaultSharedPreferences) {
            // Get "shared_prefs" as "dam.android.angelvilaplana.u4t7preferences_preferences" = Package project
            return   PreferenceManager.getDefaultSharedPreferences(context);
        }
        return null;
    }

    public SharedPreferences getModePrefs() {
        return modePrefs;
    }

}
