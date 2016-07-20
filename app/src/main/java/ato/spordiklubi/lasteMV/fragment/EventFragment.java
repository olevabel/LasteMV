package ato.spordiklubi.lasteMV.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import ato.spordiklubi.lasteMV.R;
import ato.spordiklubi.lasteMV.Util.Util;
import ato.spordiklubi.lasteMV.activity.EventActivity;
import ato.spordiklubi.lasteMV.data.Participant;
import ato.spordiklubi.lasteMV.data.Result;
import ato.spordiklubi.lasteMV.view.TypefaceEditText;
import ato.spordiklubi.lasteMV.view.TypefaceTextView;


/**
 * Created by Olev on 05/08/2015.
 */
public class EventFragment extends BaseFragment {

    public static final String ARG_SELECTED_EVENT = EventFragment.class.getName() + ".ARG_SELECTED_EVENT";
    public static final String ARG_SELECTED_AGE = EventFragment.class.getName() + ".ARG_SELECTED_AGE";
    public static final String ARG_SPREADSHEET_ITERATOR = EventFragment.class.getName() + ".ARG_SPREADSHEET_ITERATOR";
    public static final String ARG_SPREADSHEET_SIZE = EventFragment.class.getName() + ".ARG_SPREADSHEET_SIZE";
    public static final String ARG_TABLE_NAME = EventFragment.class.getName() + ".ARG_TABLE_NAME";
    public static final String ARG_CYCLE_COUNT = EventFragment.class.getName() + ".ARG_CYCLE_COUNT";


    private Participant currentParticipant;
    private int databaseIterator;
    private int cycleCount;
    private long tableSize;

    private View viewRoot;
    private TypefaceTextView customToolbarTitle;
    private String selectedEvent;
    private int selectedAge;


    private ProgressDialog progressDialog;
    private TypefaceTextView participantNumber;
    private TypefaceTextView participantName;
    private TypefaceTextView participantAge;
    private ImageView btnQuickMessages;

    private TypefaceEditText resultInputLargeUnits;
    private TypefaceEditText resultInputSmallUnits;
    private TypefaceEditText resultInputSmallestUnits;
    private TypefaceTextView viewLargeUnits;
    private TypefaceTextView viewSmallUnits;
    private TypefaceTextView viewSmallestUnits;
    private TypefaceTextView btnEnter;
    private TypefaceTextView btnBack;
    private TypefaceTextView btnSend;
    private boolean isTimeMeasured;


    private String tableName;


    public static EventFragment newInstance(int selectedAge, String selectedEvent, int spreadsheetIterator, long spreadsheetSize, String tableName, int cycleCount) {
        final EventFragment eventFragment = new EventFragment();
        eventFragment.setArguments(createArguments(selectedAge, selectedEvent, spreadsheetIterator, spreadsheetSize, tableName, cycleCount));
        return eventFragment;
    }

    protected static Bundle createArguments(int selectedAge, String selectedEvent, int spreadsheetIterator, long spreadsheetSize, String tableName, int cycleCount) {
        final Bundle args = new Bundle();
        args.putInt(ARG_SELECTED_AGE, selectedAge);
        args.putString(ARG_SELECTED_EVENT, selectedEvent);
        args.putInt(ARG_SPREADSHEET_ITERATOR, spreadsheetIterator);
        args.putLong(ARG_SPREADSHEET_SIZE, spreadsheetSize);
        args.putString(ARG_TABLE_NAME, tableName);
        args.putInt(ARG_CYCLE_COUNT, cycleCount);


        return args;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewRoot = inflater.inflate(R.layout.fragment_event, null);
        progressDialog = new ProgressDialog(getActivity());
        databaseIterator = getArguments().getInt(ARG_SPREADSHEET_ITERATOR);
        selectedEvent = getArguments().getString(ARG_SELECTED_EVENT);
        selectedAge = getArguments().getInt(ARG_SELECTED_AGE);
        tableName = getArguments().getString(ARG_TABLE_NAME);
        cycleCount = getArguments().getInt(ARG_CYCLE_COUNT);

        currentParticipant = databaseHandler.getParticipantFromDb(databaseIterator, tableName);
        tableSize = databaseHandler.getTableRowCount(tableName);
        customToolbarTitle = (TypefaceTextView) viewRoot.findViewById(R.id.custom_toolbar_title);
        customToolbarTitle.setText(selectedEvent);

        participantNumber = (TypefaceTextView) viewRoot.findViewById(R.id.view_participant_number);
        participantNumber.setText(Integer.toString(currentParticipant.getNumber()));

        participantName = (TypefaceTextView) viewRoot.findViewById(R.id.view_participant_name);
        participantName.setText(currentParticipant.getName());

        participantAge = (TypefaceTextView) viewRoot.findViewById(R.id.view_participant_age);
        participantAge.setText(selectedAge == 2 ? getString(R.string.mini_age) : getString(R.string.participant_age, selectedAge));


        resultInputLargeUnits = (TypefaceEditText) viewRoot.findViewById(R.id.input_large_units);
        resultInputSmallUnits = (TypefaceEditText) viewRoot.findViewById(R.id.input_small_units);
        resultInputSmallestUnits = (TypefaceEditText) viewRoot.findViewById(R.id.input_smallest_units);
        if (databaseHandler.getResultFromDb(databaseIterator, tableName, selectedEvent) != null) {
            if (databaseHandler.getResultFromDb(databaseIterator, tableName, selectedEvent).getResult() != 0) {
                if (selectedEvent.equals(getString(R.string.btn_bike)) || selectedEvent.equals(getString(R.string.btn_sprint)) || selectedEvent.equals(getString(R.string.run))) {
                    if (Double.toString(databaseHandler.getResultFromDb(databaseIterator, tableName, selectedEvent).getResult() / 60).contains(".")) {
                        String oldString = Double.toString(databaseHandler.getResultFromDb(databaseIterator, tableName, selectedEvent).getResult() / 60);
                        String withOutDecimal = oldString.substring(0, oldString.indexOf("."));
                        resultInputLargeUnits.setText(withOutDecimal);
                    } else {
                        resultInputLargeUnits.setText(Double.toString(databaseHandler.getResultFromDb(databaseIterator, tableName, selectedEvent).getResult() / 60));
                    }
                    if (Double.toString(databaseHandler.getResultFromDb(databaseIterator, tableName, selectedEvent).getResult() % 60).contains(".")) {
                        String oldStringLittle = Double.toString(databaseHandler.getResultFromDb(databaseIterator, tableName, selectedEvent).getResult() % 60);
                        String withoutOutDecimalLittle = oldStringLittle.substring(0, oldStringLittle.indexOf("."));
                        resultInputSmallUnits.setText(withoutOutDecimalLittle);
                    } else {
                        resultInputSmallUnits.setText(Double.toString(databaseHandler.getResultFromDb(databaseIterator, tableName, selectedEvent).getResult() % 60));
                    }
                    if (Double.toString(databaseHandler.getResultFromDb(databaseIterator, tableName, selectedEvent).getResult()).contains(".")) {
                        double rounded = databaseHandler.getResultFromDb(databaseIterator, tableName, selectedEvent).getResult() - Math.floor(databaseHandler.getResultFromDb(databaseIterator, tableName, selectedEvent).getResult());
                        if (rounded == 0) {
                            resultInputSmallestUnits.setText("");
                        }
                        resultInputSmallestUnits.setText(Integer.toString((int) Util.round((rounded * 1000), 2)));
                    } else {
                        resultInputSmallestUnits.setText("");
                    }
                } else if (selectedEvent.equals(getString(R.string.btn_long_jump)) || selectedEvent.equals(getString(R.string.btn_ball)) || selectedEvent.equals(getString(R.string.btn_medicine_ball))) {
                    if (Double.toString(databaseHandler.getResultFromDb(databaseIterator, tableName, selectedEvent).getResult()).contains(".")) {
                        String oldString = Double.toString(databaseHandler.getResultFromDb(databaseIterator, tableName, selectedEvent).getResult());
                        String withoutDecimal = oldString.substring(0, oldString.indexOf("."));
                        resultInputLargeUnits.setText(withoutDecimal);
                        double rounded = databaseHandler.getResultFromDb(databaseIterator, tableName, selectedEvent).getResult() - Math.floor(databaseHandler.getResultFromDb(databaseIterator, tableName, selectedEvent).getResult());
                        if (rounded == 0) {
                            resultInputSmallUnits.setText("");
                        }
                        resultInputSmallUnits.setText(Integer.toString((int) Util.round((rounded * 100), 2)));
                    }
                } else {
                    resultInputLargeUnits.setText(Integer.toString((int) databaseHandler.getResultFromDb(databaseIterator, tableName, selectedEvent).getResult()));
                }
            }
        }
        resultInputLargeUnits.setSelection(resultInputLargeUnits.length());
        resultInputSmallUnits.setSelection(resultInputSmallUnits.length());
        resultInputSmallestUnits.setSelection(resultInputSmallestUnits.length());
        viewLargeUnits = (TypefaceTextView) viewRoot.findViewById(R.id.view_large_units);
        viewSmallUnits = (TypefaceTextView) viewRoot.findViewById(R.id.view_small_units);
        viewSmallestUnits = (TypefaceTextView) viewRoot.findViewById(R.id.view_smallest_units);
        switch (selectedEvent) {
            case EVENT_BALL:
                viewLargeUnits.setText(getString(R.string.units_meters));
                viewSmallUnits.setText(getString(R.string.units_centimeters));
                break;
            case EVENT_LONG_JUMP:
                viewLargeUnits.setText(getString(R.string.units_meters));
                viewSmallUnits.setText(getString(R.string.units_centimeters));
                break;
            case EVENT_MEDICINE_BALL:
                viewLargeUnits.setText(getString(R.string.units_meters));
                viewSmallUnits.setText(getString(R.string.units_centimeters));
                break;
            case EVENT_BASKETBALL:
                viewLargeUnits.setText(getString(R.string.units_baskets));
                break;
            case EVENT_FOOTBALL:
                viewLargeUnits.setText(getString(R.string.units_goals));
                break;
            case EVENT_VOLLEYBALL:
                viewLargeUnits.setText(getString(R.string.units_serves));
                break;
            case EVENT_BOXES:
                viewLargeUnits.setText(getString(R.string.units_boxes));
                break;
            default:
                viewLargeUnits.setText(getString(R.string.units_minutes));
                viewSmallUnits.setText(getString(R.string.units_seconds));
                viewSmallestUnits.setText(getString(R.string.units_hundreds));
                isTimeMeasured = true;
                break;
        }
        viewSmallestUnits.setVisibility(isTimeMeasured ? View.VISIBLE : View.GONE);
        resultInputSmallestUnits.setVisibility(isTimeMeasured ? View.VISIBLE : View.GONE);
        resultInputLargeUnits.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 2) {
                    resultInputSmallUnits.requestFocus();
                    resultInputSmallUnits.setSelection(resultInputSmallUnits.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        viewSmallUnits.setVisibility((selectedEvent.equals(EVENT_BASKETBALL) || selectedEvent.equals(EVENT_BOXES) || selectedEvent.equals(EVENT_FOOTBALL) || selectedEvent.equals(EVENT_VOLLEYBALL)) ? View.GONE : View.VISIBLE);
        resultInputSmallUnits.setVisibility((selectedEvent.equals(EVENT_BASKETBALL) || selectedEvent.equals(EVENT_BOXES) || selectedEvent.equals(EVENT_FOOTBALL) || selectedEvent.equals(EVENT_VOLLEYBALL)) ? View.GONE : View.VISIBLE);
        btnEnter = (TypefaceTextView) viewRoot.findViewById(R.id.btn_enter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
                                        Result result;

                                        @Override
                                        public void onClick(View v) {

                                            if (selectedEvent.equals(getString(R.string.btn_bike)) || selectedEvent.equals(getString(R.string.btn_sprint)) || selectedEvent.equals(getString(R.string.run))) {
                                                if (resultInputLargeUnits.getText().toString().equals("") || resultInputSmallUnits.getText().toString().equals("") || resultInputSmallestUnits.getText().toString().equals("")) {
                                                    Toast.makeText(getActivity(), getString(R.string.result_field_empty), Toast.LENGTH_LONG).show();
                                                    return;
                                                }
                                                result = new Result(selectedEvent, Integer.valueOf(resultInputLargeUnits.getText().toString()) * 60 + Integer.valueOf(resultInputSmallUnits.getText().toString()) + Double.valueOf(resultInputSmallestUnits.getText().toString()) / 1000);

                                            } else if (selectedEvent.equals(getString(R.string.btn_long_jump)) || selectedEvent.equals(getString(R.string.btn_ball)) || selectedEvent.equals(getString(R.string.btn_medicine_ball))) {
                                                if (resultInputLargeUnits.getText().toString().equals("") || resultInputSmallUnits.getText().toString().equals("")) {
                                                    Toast.makeText(getActivity(), getString(R.string.result_field_empty), Toast.LENGTH_LONG).show();
                                                    return;
                                                }
                                                result = new Result(selectedEvent, Integer.valueOf(resultInputLargeUnits.getText().toString()) + Double.valueOf(resultInputSmallUnits.getText().toString()) / 100);
                                            } else {
                                                if (resultInputLargeUnits.getText().toString().equals("")) {
                                                    Toast.makeText(getActivity(), getString(R.string.result_field_empty), Toast.LENGTH_LONG).show();
                                                    return;
                                                }
                                                result = new Result(selectedEvent, Integer.valueOf(resultInputLargeUnits.getText().toString()));
                                            }
                                            databaseHandler.addResult(databaseIterator, result, tableName, selectedEvent);
                                            System.out.println(databaseHandler.getResultFromDb(databaseIterator, tableName, selectedEvent).getResult());
                                            showProgressDialog(false);
                                            Intent intent = new Intent(getActivity(), EventActivity.class);
                                            databaseIterator += 1;

                                            if (databaseIterator == tableSize + 1) {
                                                if (selectedEvent.equals(getString(R.string.btn_long_jump)) && cycleCount < 3) {
                                                    cycleCount += 1;
                                                    intent.putExtra(EventActivity.EXTRA_SPREADSHEET_ITERATOR, 1);
                                                    intent.putExtra(EventActivity.EXTRA_SELECTED_AGE, selectedAge);
                                                    intent.putExtra(EventActivity.EXTRA_SELECTED_EVENT, selectedEvent);
                                                    intent.putExtra(EventActivity.EXTRA_SPREADSHEET_SIZE, tableSize);
                                                    intent.putExtra(EventActivity.EXTRA_TABLE_NAME, tableName);
                                                    intent.putExtra(EventActivity.EXTRA_CYCLE_COUNT,cycleCount);
                                                    startActivity(intent);
                                                    getActivity().finish();
                                                } else

                                                Toast.makeText(getActivity(), getString(R.string.toast_no_more_participants), Toast.LENGTH_LONG).show();
                                                btnEnter.setVisibility(View.GONE);
                                                return;
                                            }
                                            intent.putExtra(EventActivity.EXTRA_SPREADSHEET_ITERATOR, databaseIterator);
                                            intent.putExtra(EventActivity.EXTRA_SELECTED_AGE, selectedAge);
                                            intent.putExtra(EventActivity.EXTRA_SELECTED_EVENT, selectedEvent);
                                            intent.putExtra(EventActivity.EXTRA_SPREADSHEET_SIZE, tableSize);
                                            intent.putExtra(EventActivity.EXTRA_TABLE_NAME, tableName);
                                            intent.putExtra(EventActivity.EXTRA_CYCLE_COUNT,cycleCount);
                                            startActivity(intent);
                                            getActivity().finish();
                                        }
                                    }
        );

        btnEnter.setVisibility(databaseIterator == tableSize + 1 ? View.GONE : View.VISIBLE);
        btnBack = (TypefaceTextView) viewRoot.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener()

                                   {
                                       @Override
                                       public void onClick(View v) {
                                           Intent intent = new Intent(getActivity(), EventActivity.class);
                                           databaseIterator -= 1;
                                           intent.putExtra(EventActivity.EXTRA_SPREADSHEET_ITERATOR, databaseIterator);
                                           intent.putExtra(EventActivity.EXTRA_SELECTED_AGE, selectedAge);
                                           intent.putExtra(EventActivity.EXTRA_SELECTED_EVENT, selectedEvent);
                                           intent.putExtra(EventActivity.EXTRA_SPREADSHEET_SIZE, tableSize);
                                           intent.putExtra(EventActivity.EXTRA_TABLE_NAME, tableName);
                                           intent.putExtra(EventActivity.EXTRA_CYCLE_COUNT,cycleCount);
                                           startActivity(intent);
                                           getActivity().finish();
                                       }
                                   }

        );
        btnBack.setVisibility(databaseIterator == 1 ? View.GONE : View.VISIBLE);
        btnSend = (TypefaceTextView) viewRoot.findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {

                                       }
                                   }

        );
        return viewRoot;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void showProgressDialog(boolean showDialog) {
        if (showDialog) {
            progressDialog.show();
        } else {
            progressDialog.cancel();
        }
    }


}
