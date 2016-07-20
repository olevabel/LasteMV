package ato.spordiklubi.lasteMV.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import ato.spordiklubi.lasteMV.R;
import ato.spordiklubi.lasteMV.Util.UIUtil;


/**
 * Created by Olev on 03/08/2015.
 */
public class TypefaceTextView extends TextView {
    public TypefaceTextView(Context context) {
        super(context);
    }

    public TypefaceTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        UIUtil.setCustomFont(this, context, attrs, R.styleable.TypeFaceTextView, R.styleable.TypeFaceTextView_font);
    }

    public TypefaceTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        UIUtil.setCustomFont(this, context, attrs, R.styleable.TypeFaceTextView, R.styleable.TypeFaceTextView_font);
    }
}
