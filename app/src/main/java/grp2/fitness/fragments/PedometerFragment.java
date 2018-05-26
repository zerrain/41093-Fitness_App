package grp2.fitness.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Value;
import com.google.android.gms.fitness.request.OnDataPointListener;

import grp2.fitness.handlers.DailyDataManager;
import grp2.fitness.handlers.GoogleFitApi;
import grp2.fitness.NavigationActivity;
import grp2.fitness.R;

import static com.amazonaws.mobile.auth.core.internal.util.ThreadUtils.runOnUiThread;

public class PedometerFragment extends Fragment implements
        OnDataPointListener,
        GoogleFitApi.GoogleFitApiCallback{

    private static final String AUTH_STATE_PENDING = "auth_state_pending";
    private GoogleFitApi googleFitApi;

    private NavigationActivity activity;

    private TextView steps;
    private ProgressBar stepPB;

    private int goalSteps;
    private int currentSteps;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(getActivity() == null){
            return null;
        }

        View view = inflater.inflate(R.layout.fragment_pedometer, container, false);
        activity = (NavigationActivity) getActivity();

        steps = view.findViewById(R.id.step_text);
        stepPB = view.findViewById(R.id.step_progress);

        googleFitApi = activity.getGoogleFitApi(this);

        if (savedInstanceState != null) {
            googleFitApi.setAuthState(savedInstanceState.getBoolean(AUTH_STATE_PENDING));
        }
        goalSteps = getGoalSteps();
        stepPB.setMax(goalSteps);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        googleFitApi.connect();
        googleFitApi.registerSensorListener(this, new DataType[]{DataType.TYPE_STEP_COUNT_CUMULATIVE});
    }

    @Override
    public void onStop() {
        super.onStop();
        googleFitApi.disconnect();

        activity.getDailyDataManager().setColumn(DailyDataManager.DailyDataColumn.STEPS, String.valueOf(currentSteps));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(AUTH_STATE_PENDING, googleFitApi.getAuthState());
    }

    @Override
    public void onDataPoint(DataPoint dataPoint) {
        for( final Field field : dataPoint.getDataType().getFields() ) {
            final Value value = dataPoint.getValue( field );
            runOnUiThread(() -> {
                currentSteps = Integer.parseInt(value.toString());

                String stepString = goalSteps + " - " + value.toString() + " = " + (goalSteps - currentSteps);
                steps.setText(stepString);

                stepPB.setProgress(currentSteps);
                activity.hideLoadingIcon();
            });
        }
    }

    private int getGoalSteps() {
        SharedPreferences sharedPreferences = activity.getSharedPreferences();
        String energyGoalKey = activity.getString(R.string.pref_key_goal_steps);
        return (int) Double.parseDouble(sharedPreferences.getString(energyGoalKey, "0"));
    }

    //Unused callbacks
    @Override
    public void onConnectionSuspended() {}
    @Override
    public void onConnectionFailed() {}
    @Override
    public void onConnected() {}

}
