package ato.spordiklubi.lasteMV.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import ato.spordiklubi.lasteMV.R;
import ato.spordiklubi.lasteMV.fragment.EventFragment;


/**
 * Created by Olev on 05/08/2015.
 */
public class EventActivity extends BaseActivity {

    public static String EXTRA_SELECTED_AGE = EventActivity.class.getName() + ".EXTRA_SELECTED_AGE";
    public static String EXTRA_SELECTED_EVENT = EventActivity.class.getName() + ".EXTRA_SELECTED_EVENT";
    public static String EXTRA_SPREADSHEET_ITERATOR = EventActivity.class.getName() + ".EXTRA_SPREADSHEET_ITERATOR";
    public static String EXTRA_SPREADSHEET_SIZE = EventActivity.class.getName() + ".EXTRA_SPREADSHEET_SIZE";
    public static String EXTRA_TABLE_NAME = EventActivity.class.getName() + ".EXTRA_TABLE_NAME";
    public static String EXTRA_CYCLE_COUNT = EventActivity.class.getName() + ".EXTRA_CYCLE_COUNT";


    private static String TAG_FRAGMENT_EVENT = EventActivity.class.getName() + ".TAG_FRAGMENT_EVENT";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        setupMainFragment();
    }

    protected void setupMainFragment() {
        setupFragment(TAG_FRAGMENT_EVENT, R.id.layout_event_fragment_container);
    }

    @Override
    protected Fragment createFragment(final String tag) {
        if (tag.equals(TAG_FRAGMENT_EVENT)) {

            return EventFragment.newInstance(getIntent().getIntExtra(EXTRA_SELECTED_AGE, -1),getIntent().getStringExtra(EXTRA_SELECTED_EVENT),getIntent().getIntExtra(EXTRA_SPREADSHEET_ITERATOR, 1),getIntent().getLongExtra(EXTRA_SPREADSHEET_SIZE, 0),getIntent().getStringExtra(EXTRA_TABLE_NAME),getIntent().getIntExtra(EXTRA_CYCLE_COUNT,1));
        }
        return null;
    }
}
