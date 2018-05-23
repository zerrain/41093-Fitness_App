package grp2.fitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class InitialCheck extends AppCompatActivity {

    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Perhaps set content view here

        prefs = getSharedPreferences("com.grp2.fitness", MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firststart", true)) {
            prefs.edit().putBoolean("firststart", false).commit();
            startActivity(new Intent(InitialCheck.this , SetupActivity.class));
            finish();
        }
        else {
            startActivity(new Intent(InitialCheck.this , NavigationActivity.class));
            finish();
        }
    }
}