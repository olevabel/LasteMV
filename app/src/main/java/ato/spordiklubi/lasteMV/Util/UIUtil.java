package ato.spordiklubi.lasteMV.Util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.SoftReference;
import java.util.Hashtable;



/**
 * Created by Olev on 26.07.2015.
 */
public class UIUtil {

    public static void setCustomFont(View textViewOrButton, Context ctx, AttributeSet attrs, int[] attributeSet, int fontId) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, attributeSet);
        String customFont = a.getString(fontId);
        setCustomFont(textViewOrButton, ctx, customFont);
        a.recycle();
    }

    public static boolean setCustomFont(View textViewOrButton, Context ctx, String asset) {
        if (TextUtils.isEmpty(asset))
            return false;
        Typeface tf = null;
        try {
            tf = getFont(ctx, asset, false);
            if (textViewOrButton instanceof TextView) {
                ((TextView) textViewOrButton).setTypeface(tf);
            } else {
                ((Button) textViewOrButton).setTypeface(tf);
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static Typeface getFont(final Context c, final String name, final boolean isNameWithDir) {
        synchronized (fontCache) {
            SoftReference<Typeface> ref = fontCache.get(name);
            if (ref != null) {
                if (ref.get() != null) {
                    return ref.get();
                }
            }

            Typeface typeface = Typeface.createFromAsset(c.getAssets(), (isNameWithDir ? "" : "fonts/") + name);
            fontCache.put(name, new SoftReference<>(typeface));

            return typeface;
        }
    }






    private static final Hashtable<String, SoftReference<Typeface>> fontCache = new Hashtable<>();

}

