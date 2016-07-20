package ato.spordiklubi.lasteMV.Util;

import android.content.Context;
import android.content.Intent;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ato.spordiklubi.lasteMV.activity.MainActivity;
import ato.spordiklubi.lasteMV.activity.SplashScreenActivity;


/**
 * Created by Olev on 13/08/2015.
 */
public class Util {

    public static void restartApp(final Context activity, final boolean showSplashScreen) {
        if (activity == null) {
            return;
        }
        final Intent intent = new Intent(activity, showSplashScreen ? SplashScreenActivity.class : MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
