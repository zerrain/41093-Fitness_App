package grp2.fitness.Fragments;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import grp2.fitness.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
