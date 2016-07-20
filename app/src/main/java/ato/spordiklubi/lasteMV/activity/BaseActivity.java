package ato.spordiklubi.lasteMV.activity;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import ato.spordiklubi.lasteMV.LasteMVApplication;
import ato.spordiklubi.lasteMV.database.DatabaseHandler;


/**
 * Created by Olev on 26.07.2015.
 */
public class BaseActivity extends AppCompatActivity {



    protected DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        databaseHandler = ((LasteMVApplication) getApplicationContext()).getDatabaseHandler();
        if (databaseHandler == null) {
            ((LasteMVApplication) getApplicationContext()).initApp();
            databaseHandler = ((LasteMVApplication) getApplicationContext()).getDatabaseHandler();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    protected void setupFragment(final String tag, final int containerId) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            fragment = createFragment(tag);
            final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(containerId == -1 ? android.R.id.content : containerId, fragment, tag);
            // Do not care if this fails so loss is ok
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    protected ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    protected Fragment createFragment(final String tag) {
        return null;
    }




}
