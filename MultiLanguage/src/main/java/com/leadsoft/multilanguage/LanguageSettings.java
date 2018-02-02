package com.leadsoft.multilanguage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.util.Locale;

/**
 * Created by bmdha on 2/2/18.
 */

public class LanguageSettings {


    private final Context mContext;
    private Locale myLocale;

    public LanguageSettings(Context context) {
        this.mContext = context;
    }


    public void loadLocale(OnChangeLanguageListener listener) {
        String langPref = "Language";
        SharedPreferences prefs = mContext.getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "");
        changeLang(listener, language);
    }

    public void changeLang(OnChangeLanguageListener listener, String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        mContext.getResources().updateConfiguration(config, mContext.getResources().getDisplayMetrics());
        listener.updateTexts();
    }

    public void saveLocale(String lang) {
        String langPref = "Language";
        SharedPreferences prefs = mContext.getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        if (myLocale != null) {
            newConfig.locale = myLocale;
            Locale.setDefault(myLocale);
            mContext.getResources().updateConfiguration(newConfig, mContext.getResources().getDisplayMetrics());
        }
    }

    public interface OnChangeLanguageListener {
        void updateTexts();
    }
}
