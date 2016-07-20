package ato.spordiklubi.lasteMV.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import ato.spordiklubi.lasteMV.R;
import ato.spordiklubi.lasteMV.fragment.MainFragment;


/**
 * Created by Olev on 26.07.2015.
 */
public class MainActivity extends BaseActivity{

    private static final String TAG_FRAGMENT_MAIN= MainActivity.class.getName() +".TAG_FRAGMENT_MAIN";

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupMainFragment();

    }

    protected void setupMainFragment() {
        setupFragment(TAG_FRAGMENT_MAIN, R.id.layout_main_fragment_container);
    }

    @Override
    protected Fragment createFragment(final String tag) {
        if (tag.equals(TAG_FRAGMENT_MAIN)) {

            return MainFragment.newInstance();
        }
        return null;
    }
}
