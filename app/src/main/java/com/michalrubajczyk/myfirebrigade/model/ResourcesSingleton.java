package com.michalrubajczyk.myfirebrigade.model;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;

/**
 * Created by Michal on 19/03/2018.
 */

public class ResourcesSingleton {

    private static ResourcesSingleton mInstance;

    private Context mContext;

    private ResourcesSingleton(Context context) {
        mContext = context;
    }

    public static void init(Context context) {
        mInstance = new ResourcesSingleton(context.getApplicationContext());
    }

    public static ResourcesSingleton getInstance() {
        return mInstance;
    }

    public String getString(@StringRes int id) {
        return mContext.getString(id);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Drawable getDrawable(@DrawableRes int id) {
        return mContext.getDrawable(id);
    }
}
