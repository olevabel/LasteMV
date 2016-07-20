package ato.spordiklubi.lasteMV.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ato.spordiklubi.lasteMV.R;
import ato.spordiklubi.lasteMV.activity.EventActivity;
import ato.spordiklubi.lasteMV.data.Participant;
import ato.spordiklubi.lasteMV.view.TypefaceTextView;


/**
 * Created by Olev on 29.07.2015.
 * <p/>
 * Should be done with Adapter and recyclerview,
 * but since the dicipline count stays the same form year to year,
 * its done with buttons to save time.
 */
public class DiciplineFragment extends BaseFragment {

    private static final String ARG_SELECTED_AGE = DiciplineFragment.class.getName() + ".ARG_SELECTED_AGE";
    private static final String ARG_REFEREE_NAME = DiciplineFragment.class.getName() + ".ARG_REFEREE_NAME";
    private View viewRoot;
    private TypefaceTextView btnSprint;
    private TypefaceTextView btnLongJump;
    private TypefaceTextView btnBall;
    private TypefaceTextView btnMedicineBall;
    private TypefaceTextView btnRun;
    private TypefaceTextView btnFootball;
    private TypefaceTextView btnBasketball;
    private TypefaceTextView btnVolleyball;
    private TypefaceTextView btnBoxes;
    private TypefaceTextView btnBike;

    private String tableUri;
    private String tableName;
    private int selectedAge;
    private String refereeName;


    public static DiciplineFragment newInstance(int selectedAge, String refereeName) {
        final DiciplineFragment diciplineFragment = new DiciplineFragment();
        diciplineFragment.setArguments(createArguments(selectedAge, refereeName));
        return diciplineFragment;
    }

    protected static Bundle createArguments(final int selectedAge, final String refereeName) {
        final Bundle args = new Bundle();
        args.putInt(ARG_SELECTED_AGE, selectedAge);
        args.putString(ARG_REFEREE_NAME, refereeName);
        return args;
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_dicipline, null);
        selectedAge = getArguments().getInt(ARG_SELECTED_AGE);
        refereeName = getArguments().getString(ARG_REFEREE_NAME);
        switch (selectedAge) {
            case 2:
                tableName = getString(R.string.mini_table);
                tableUri = "https://spreadsheets.google.com/tq?key=1yhMcH3_KL0X_7mk3-zI6xLk7hCEWEDkn-4TXxF0G-lw";
                break;
            case 3:
                tableName = getString(R.string.database_table_name, getString(R.string.three_years));
                tableUri = "https://spreadsheets.google.com/tq?key=12K-UcIe7IQ2ynO6ZHIMrCU9Str6JXul15E7F7iPAmKU";
                break;
            case 4:
                tableName = getString(R.string.database_table_name, getString(R.string.four_years));
                tableUri = "https://spreadsheets.google.com/tq?key=1FfYxESgvJ7a8iQxQ1rIM1_nPwfY9S0j1E7ZHTvAq4ag";
                break;
            case 5:
                tableName = getString(R.string.database_table_name, getString(R.string.five_years));
                tableUri = "https://spreadsheets.google.com/tq?key=1JPXGBIaljDeQAoOzsEDdmud5hOhqwyXGgkpGD_zEYc0";
                break;
            case 6:
                tableName = getString(R.string.database_table_name, getString(R.string.six_years));
                tableUri = "https://spreadsheets.google.com/tq?key=12hefTxYRWi5REOTGJA59aorTeW5qjwiLo2ZWsKpw8zc";
                break;
            case 7:
                tableName = getString(R.string.database_table_name, getString(R.string.seven_years));
                tableUri = "https://spreadsheets.google.com/tq?key=1dAImrD6zQMiqsw5Kp9gDQ5ccy8d2RmJbI7meVFF1cWI";
                break;
            case 8:
                tableName = getString(R.string.database_table_name, getString(R.string.eight_years));
                tableUri = "https://spreadsheets.google.com/tq?key=1wcChvDZCCCyJH9acqJDVAYSxAL2Cc8WHaRHOrjb-xGI";
                break;
            case 9:
                tableName = getString(R.string.database_table_name, getString(R.string.nine_years));
                tableUri = "https://spreadsheets.google.com/tq?key=1wZ2HYYAGFkvgqJFYDGjlotrewEWGR_TLRKX1CVkvFi0";
                break;
            case 10:
                tableName = getString(R.string.database_table_name, getString(R.string.ten_years));
                tableUri = "https://spreadsheets.google.com/tq?key=1X3lo4hZHP9m9LC7_qUTDEUkv41q923z9ycR_-H6JkHk";
                break;
        }
        databaseHandler.dropTable(tableName);
        databaseHandler.createTable(tableName);
        databaseHandler.addRefereeNameToSelectedEvent(refereeName,tableName);
        btnSprint = (TypefaceTextView) viewRoot.findViewById(R.id.btn_sprint);
        btnSprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(getActivity(), EventActivity.class);
                intent.putExtra(EventActivity.EXTRA_SELECTED_EVENT, getString(R.string.btn_sprint));
                intent.putExtra(EventActivity.EXTRA_SELECTED_AGE, selectedAge);
                intent.putExtra(EventActivity.EXTRA_TABLE_NAME,tableName);
                startActivity(intent);
            }
        });
        btnLongJump = (TypefaceTextView) viewRoot.findViewById(R.id.btn_long_jump);
        btnLongJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(getActivity(), EventActivity.class);
                intent.putExtra(EventActivity.EXTRA_SELECTED_EVENT, getString(R.string.btn_long_jump));
                intent.putExtra(EventActivity.EXTRA_SELECTED_AGE, selectedAge);
                intent.putExtra(EventActivity.EXTRA_TABLE_NAME,tableName);
                startActivity(intent);
            }
        });
        btnBall = (TypefaceTextView) viewRoot.findViewById(R.id.btn_ball);
        btnBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(getActivity(), EventActivity.class);
                intent.putExtra(EventActivity.EXTRA_SELECTED_EVENT, getString(R.string.btn_ball));
                intent.putExtra(EventActivity.EXTRA_SELECTED_AGE, selectedAge);
                intent.putExtra(EventActivity.EXTRA_TABLE_NAME,tableName);
                startActivity(intent);
            }
        });
        btnRun = (TypefaceTextView) viewRoot.findViewById(R.id.btn_run);
        btnRun.setVisibility(selectedAge >= AGE_FOUR ? View.VISIBLE : View.GONE);
        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(getActivity(), EventActivity.class);
                intent.putExtra(EventActivity.EXTRA_SELECTED_EVENT, getString(R.string.run));
                intent.putExtra(EventActivity.EXTRA_SELECTED_AGE, selectedAge);
                intent.putExtra(EventActivity.EXTRA_TABLE_NAME,tableName);
                startActivity(intent);
            }
        });
        btnMedicineBall = (TypefaceTextView) viewRoot.findViewById(R.id.btn_medicine_ball);
        btnMedicineBall.setVisibility(selectedAge >= AGE_FIVE ? View.VISIBLE : View.GONE);
        btnMedicineBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(getActivity(), EventActivity.class);
                intent.putExtra(EventActivity.EXTRA_SELECTED_EVENT, getString(R.string.btn_medicine_ball));
                intent.putExtra(EventActivity.EXTRA_SELECTED_AGE, selectedAge);
                intent.putExtra(EventActivity.EXTRA_TABLE_NAME,tableName);
                startActivity(intent);
            }
        });
        btnFootball = (TypefaceTextView) viewRoot.findViewById(R.id.btn_football);
        btnFootball.setVisibility(selectedAge >= AGE_SIX ? View.VISIBLE : View.GONE);
        btnFootball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(getActivity(), EventActivity.class);
                intent.putExtra(EventActivity.EXTRA_SELECTED_EVENT, getString(R.string.btn_football));
                intent.putExtra(EventActivity.EXTRA_SELECTED_AGE, selectedAge);
                intent.putExtra(EventActivity.EXTRA_TABLE_NAME,tableName);
                startActivity(intent);
            }
        });

        btnBasketball = (TypefaceTextView) viewRoot.findViewById(R.id.btn_basketball);
        btnBasketball.setVisibility(selectedAge >= AGE_SEVEN ? View.VISIBLE : View.GONE);
        btnBasketball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(getActivity(), EventActivity.class);
                intent.putExtra(EventActivity.EXTRA_SELECTED_EVENT, getString(R.string.btn_basketball));
                intent.putExtra(EventActivity.EXTRA_SELECTED_AGE, selectedAge);
                intent.putExtra(EventActivity.EXTRA_TABLE_NAME,tableName);
                startActivity(intent);
            }
        });

        btnVolleyball = (TypefaceTextView) viewRoot.findViewById(R.id.btn_volleyball);
        btnVolleyball.setVisibility(selectedAge >= AGE_EIGHT ? View.VISIBLE : View.GONE);
        btnVolleyball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(getActivity(), EventActivity.class);
                intent.putExtra(EventActivity.EXTRA_SELECTED_EVENT, getString(R.string.btn_volleyball));
                intent.putExtra(EventActivity.EXTRA_SELECTED_AGE, selectedAge);
                intent.putExtra(EventActivity.EXTRA_TABLE_NAME,tableName);
                startActivity(intent);
            }
        });

        btnBoxes = (TypefaceTextView) viewRoot.findViewById(R.id.btn_boxes);
        btnBoxes.setVisibility(selectedAge >= AGE_NINE ? View.VISIBLE : View.GONE);
        btnBoxes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(getActivity(), EventActivity.class);
                intent.putExtra(EventActivity.EXTRA_SELECTED_EVENT, getString(R.string.btn_boxes));
                intent.putExtra(EventActivity.EXTRA_SELECTED_AGE, selectedAge);
                intent.putExtra(EventActivity.EXTRA_TABLE_NAME,tableName);
                startActivity(intent);
            }
        });

        btnBike = (TypefaceTextView) viewRoot.findViewById(R.id.btn_bike);
        btnBike.setVisibility(selectedAge == AGE_TEN ? View.VISIBLE : View.GONE);
        btnBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(getActivity(), EventActivity.class);
                intent.putExtra(EventActivity.EXTRA_SELECTED_EVENT, getString(R.string.btn_bike));
                intent.putExtra(EventActivity.EXTRA_SELECTED_AGE, selectedAge);
                intent.putExtra(EventActivity.EXTRA_TABLE_NAME,tableName);
                startActivity(intent);
            }
        });
        return viewRoot;
    }

    private void processJson(JSONObject object) {
        try {
            JSONArray rows = object.getJSONArray("rows");

            for (int r = 0; r < rows.length(); ++r) {
                JSONObject row = rows.getJSONObject(r);
                JSONArray columns = row.getJSONArray("c");

                int number = columns.getJSONObject(0).getInt("v");
                String name = columns.getJSONObject(1).getString("v");

                Participant participant = new Participant(number, name);
                databaseHandler.addParticipant(participant, tableName);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
