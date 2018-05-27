package grp2.fitness.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class HeartRateFragment extends Fragment implements
        OnDataPointListener,
        GoogleFitApi.GoogleFitApiCallback{

    private static final String AUTH_STATE_PENDING = "auth_state_pending";
    private GoogleFitApi googleFitApi;
    private TextView heartRate;
    private NavigationActivity activity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(getActivity() == null){
            return null;
        }

        View view = inflater.inflate(R.layout.fragment_heartrate, container, false);
        activity = (NavigationActivity) getActivity();
        heartRate = view.findViewById(R.id.heartRate);
        activity.hideLoadingIcon();

        googleFitApi = activity.getGoogleFitApi(this);

        if (savedInstanceState != null) {
            googleFitApi.setAuthState(savedInstanceState.getBoolean(AUTH_STATE_PENDING));
        }

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        googleFitApi.connect();
        googleFitApi.registerSensorListener(this, new DataType[]{DataType.TYPE_HEART_RATE_BPM});
    }

    @Override
    public void onStop() {
        super.onStop();
        googleFitApi.disconnect();

        String heartRateString = heartRate.getText().toString();
        if(!heartRateString.equals("")){
            activity.getDailyDataManager().setColumn(DailyDataManager.DailyDataColumn.HEART_RATE, heartRateString);
        }
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
                heartRate.setText(value.toString());
                activity.hideLoadingIcon();
            });
        }
    }

    //Unused callbacks
    @Override
    public void onConnectionSuspended() {}

    @Override
    public void onConnectionFailed() {}

    @Override
    public void onConnected() {}

}
