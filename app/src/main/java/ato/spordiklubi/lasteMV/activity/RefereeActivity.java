package ato.spordiklubi.lasteMV.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import ato.spordiklubi.lasteMV.R;
import ato.spordiklubi.lasteMV.fragment.RefereeFragment;


/**
 * Created by Olev on 18/08/2015.
 */
public class RefereeActivity extends BaseActivity {

    public static String EXTRA_SELECTED_AGE = RefereeActivity.class.getName() + ".EXTRA_SELECTED_AGE";
    private static final String TAG_FRAGMENT_REFEREE = RefereeActivity.class.getName() + ".TAG_FRAGMENT_REFEREE";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referee);
        setupMainFragment();

    }

    protected void setupMainFragment() {
        setupFragment(TAG_FRAGMENT_REFEREE, R.id.layout_referee_fragment_container);
    }

    @Override
    protected Fragment createFragment(final String tag) {
        if (tag.equals(TAG_FRAGMENT_REFEREE)) {

            return RefereeFragment.newInstance(getIntent().getIntExtra(EXTRA_SELECTED_AGE, -1));
        }
        return null;
    }
}
