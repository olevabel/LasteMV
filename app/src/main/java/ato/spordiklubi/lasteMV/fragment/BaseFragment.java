package ato.spordiklubi.lasteMV.fragment;


import android.os.Bundle;

import ato.spordiklubi.lasteMV.LasteMVApplication;
import ato.spordiklubi.lasteMV.database.DatabaseHandler;


/**
 * Created by Olev on 26.07.2015.
 */
public class BaseFragment extends android.support.v4.app.Fragment {

    private static  final String LOG_TAG = BaseFragment.class.getName() + ".LOG_TAG";

    public final int AGE_MINI = 2;
    public final int AGE_THREE = 3;
    public final int AGE_FOUR = 4;
    public final int AGE_FIVE = 5;
    public final int AGE_SIX = 6;
    public final int AGE_SEVEN = 7;
    public final int AGE_EIGHT = 8;
    public final int AGE_NINE = 9;
    public final int AGE_TEN = 10;

    public final String EVENT_SPRINT = "Sprint 30m";
    public final String EVENT_BALL = "Pallivise";
    public final String EVENT_LONG_JUMP = "Kaugushüpe";
    public final String EVENT_RUN = "400m jooks";
    public final String EVENT_FOOTBALL = "Jalgpall";
    public final String EVENT_BASKETBALL = "Korvpall";
    public final String EVENT_MEDICINE_BALL = "Topispallijänn";
    public final String EVENT_VOLLEYBALL = "Võrkpall";
    public final String EVENT_BIKE = "Jalgratas";
    public final String EVENT_BOXES = "Kastironimine";



    protected DatabaseHandler databaseHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHandler = ((LasteMVApplication) getActivity().getApplicationContext()).getDatabaseHandler();
        if( databaseHandler == null){
            ((LasteMVApplication) getActivity().getApplicationContext()).initApp();
            databaseHandler = ((LasteMVApplication) getActivity().getApplicationContext()).getDatabaseHandler();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }



}
