package ato.spordiklubi.lasteMV.Util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Olev on 11/08/2015.
 */
public class SharedPrefHelper {


    private static final String PREF_ACCESS_TOKEN = "PREF_ACCESS_TOKEN";

    private static SharedPreferences getSharedPrefs(final Context context) {
        return context.getSharedPreferences(context.getPackageName() + "_preferences", Context.MODE_PRIVATE);
    }

    private static void setAccessToken (final Context context, final String accessToken){
        final SharedPreferences.Editor editor = getSharedPrefs(context).edit();
        editor.putString(PREF_ACCESS_TOKEN,accessToken);
        editor.apply();
    }

    private static String getAccessToken(final Context context){
        return getSharedPrefs(context).getString(PREF_ACCESS_TOKEN,null);
    }
}
