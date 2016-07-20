package ato.spordiklubi.lasteMV.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import ato.spordiklubi.lasteMV.R;
import ato.spordiklubi.lasteMV.fragment.DiciplineFragment;


/**
 * Created by Olev on 29.07.2015.
 */
public class DiciplineActivity extends BaseActivity {

    public static String EXTRA_SELECTED_AGE = DiciplineActivity.class.getName() + ".EXTRA_SELECTED_AGE";
    public static  String EXTRA_REFEREE_NAME = DiciplineActivity.class.getName() + ".EXTRA_REFEREE_NAME";
    private static String TAG_FRAGMENT_DICIPLINE = DiciplineActivity.class.getName() + ".TAG_FRAGMENT_DICIPLINE";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dicipline);
        setupMainFragment();
    }

    protected void setupMainFragment() {
        setupFragment(TAG_FRAGMENT_DICIPLINE, R.id.layout_dicipline_fragment_container);
    }

    @Override
    protected Fragment createFragment(final String tag) {
        if (tag.equals(TAG_FRAGMENT_DICIPLINE)) {

            return DiciplineFragment.newInstance(getIntent().getIntExtra(EXTRA_SELECTED_AGE,-1),getIntent().getStringExtra(EXTRA_REFEREE_NAME));
        }
        return null;
    }

}
