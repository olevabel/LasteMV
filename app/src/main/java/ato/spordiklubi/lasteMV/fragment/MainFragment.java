package ato.spordiklubi.lasteMV.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ato.spordiklubi.lasteMV.R;
import ato.spordiklubi.lasteMV.activity.RefereeActivity;


/**
 * Created by Olev on 26.07.2015.
 */
public class MainFragment extends BaseFragment {


    private View viewRoot;
    private TextView btnMini;
    private TextView btnThreeYears;
    private TextView btnFourYears;
    private TextView btnFiveYears;
    private TextView btnSixYears;
    private TextView btnSevenYears;
    private TextView btnEightYears;
    private TextView btnNineYears;
    private TextView btnTenYears;
    private Intent intent;
    private int selectedAge;




    public static MainFragment newInstance() {
        return new MainFragment();
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewRoot = inflater.inflate(R.layout.fragment_main, null);
        btnMini = (TextView) viewRoot.findViewById(R.id.btn_mini);
        btnMini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), RefereeActivity.class);
                selectedAge = AGE_MINI;
                intent.putExtra(RefereeActivity.EXTRA_SELECTED_AGE,selectedAge);
                startActivity(intent);

            }
        });

        btnThreeYears = (TextView) viewRoot.findViewById(R.id.btn_three);
        btnThreeYears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), RefereeActivity.class);
                selectedAge = AGE_THREE;
                intent.putExtra(RefereeActivity.EXTRA_SELECTED_AGE,selectedAge);
                startActivity(intent);


            }
        });

        btnFourYears = (TextView) viewRoot.findViewById(R.id.btn_four);
        btnFourYears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), RefereeActivity.class);
                selectedAge = AGE_FOUR;
                intent.putExtra(RefereeActivity.EXTRA_SELECTED_AGE,selectedAge);
                startActivity(intent);

            }
        });

        btnFiveYears = (TextView) viewRoot.findViewById(R.id.btn_five);
        btnFiveYears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), RefereeActivity.class);
                selectedAge = AGE_FIVE;
                intent.putExtra(RefereeActivity.EXTRA_SELECTED_AGE,selectedAge);
                startActivity(intent);


            }
        });

        btnSixYears = (TextView) viewRoot.findViewById(R.id.btn_six);
        btnSixYears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), RefereeActivity.class);
                selectedAge = AGE_SIX;
                intent.putExtra(RefereeActivity.EXTRA_SELECTED_AGE,selectedAge);
                startActivity(intent);


            }
        });

        btnSevenYears = (TextView) viewRoot.findViewById(R.id.btn_seven);
        btnSevenYears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), RefereeActivity.class);
                selectedAge = AGE_SEVEN;
                intent.putExtra(RefereeActivity.EXTRA_SELECTED_AGE,selectedAge);
                startActivity(intent);

            }
        });

        btnEightYears = (TextView) viewRoot.findViewById(R.id.btn_eight);
        btnEightYears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), RefereeActivity.class);
                selectedAge = AGE_EIGHT;
                intent.putExtra(RefereeActivity.EXTRA_SELECTED_AGE,selectedAge);
                startActivity(intent);

            }
        });

        btnNineYears = (TextView) viewRoot.findViewById(R.id.btn_nine);
        btnNineYears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), RefereeActivity.class);
                selectedAge = AGE_NINE;
                intent.putExtra(RefereeActivity.EXTRA_SELECTED_AGE,selectedAge);
                startActivity(intent);

            }
        });

        btnTenYears = (TextView) viewRoot.findViewById(R.id.btn_ten);
        btnTenYears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), RefereeActivity.class);
                selectedAge = AGE_TEN;
                intent.putExtra(RefereeActivity.EXTRA_SELECTED_AGE,selectedAge);
                startActivity(intent);

            }
        });


        return viewRoot;

    }





}
