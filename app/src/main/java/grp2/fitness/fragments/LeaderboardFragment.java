package grp2.fitness.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.amazonaws.models.nosql.DailyDataDO;

import java.util.ArrayList;

import grp2.fitness.handlers.DailyDataManager;
import grp2.fitness.helpers.StringUtils;
import grp2.fitness.NavigationActivity;
import grp2.fitness.R;

public class LeaderboardFragment extends Fragment implements DailyDataManager.DailyDataListener{

    DailyDataManager dailyDataManager;
    NavigationActivity activity;
    ArrayAdapter<DailyDataDO> leaderboardAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(getActivity() == null || getContext() == null){
            return null;
        }

        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        activity = (NavigationActivity) getActivity();

        ListView list = view.findViewById(R.id.list);
        ArrayList<DailyDataDO> leaderboard = new ArrayList<>();

        dailyDataManager = activity.getDailyDataManager();
        dailyDataManager.setCallback(this);

        leaderboardAdapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                leaderboard
        );

        list.setAdapter(leaderboardAdapter);
        dailyDataManager.syncAllDailyData(StringUtils.getCurrentDateFormatted());

        return view;
    }

    @Override
    public void onPause() {
        dailyDataManager.setCallback(activity);
        super.onPause();
    }

    @Override
    public void onAllDailyDataSynced(final ArrayList<DailyDataDO> allDailyData) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.hideLoadingIcon();
                leaderboardAdapter.clear();
                leaderboardAdapter.addAll(allDailyData);
                leaderboardAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDailyDataSaved(DailyDataDO dailyData) {

    }
}
