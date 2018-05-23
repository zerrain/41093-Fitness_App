package grp2.fitness.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import grp2.fitness.NavigationActivity;
import grp2.fitness.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        CardView diaryCard = view.findViewById(R.id.diarycard);
        diaryCard.setOnClickListener(this);

        CardView pedometerCard = view.findViewById(R.id.pedometercard);
        pedometerCard.setOnClickListener(this);

        CardView leaderboardCard = view.findViewById(R.id.leaderboardcard);
        leaderboardCard.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        if(getActivity() == null){
            return;
        }

        switch(view.getId()){
            case R.id.diarycard :
                ((NavigationActivity)getActivity()).updateView(DiaryFragment.class);
                getActivity().setTitle("Diary");
                break;
            case R.id.pedometercard :
                ((NavigationActivity)getActivity()).updateView(PedometerFragment.class);
                getActivity().setTitle("Pedometer");
                break;
            case R.id.leaderboardcard :
                ((NavigationActivity)getActivity()).updateView(LeaderboardFragment.class);
                getActivity().setTitle("Leaderboard");
                break;
        }

    }
}
