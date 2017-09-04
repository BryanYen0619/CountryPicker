package com.bingerz.android.countrycodepicker;

import android.content.Context;

import java.util.Locale;

/**
 * Created by hanbing on 16/6/29.
 */
public class Utils {

    public static final int COUNTRY_EN = 0x00;
    public static final int COUNTRY_TW = 0x10;
    public static final int COUNTRY_CN = 0x11;

    public static String getCountry(Context context) {
        try {
            Locale locale = context.getResources().getConfiguration().locale;
            return locale.getCountry();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Locale.getDefault().getCountry();
    }
}
