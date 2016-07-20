package ato.spordiklubi.lasteMV;

import android.app.Application;
import android.content.Context;

import ato.spordiklubi.lasteMV.database.DatabaseHandler;


/**
 * Created by Olev on 13/08/2015.
 */
public class LasteMVApplication extends Application {

    private Context mContext;
    protected DatabaseHandler databaseHandler;
    @Override
    public void onCreate() {
        mContext = getApplicationContext();
        super.onCreate();
        initApp();
    }

    public void initApp() {

        databaseHandler = new DatabaseHandler(mContext,null,null,3);
    }

    public DatabaseHandler getDatabaseHandler(){
        return databaseHandler;
    }

}
