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


        Lang language = getDefaultLang();
        changeLang(listener, language);
    }

    private Lang getDefaultLang() {

        String langPref = "Language";
        SharedPreferences prefs = mContext.getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        return Lang.valueOf(prefs.getString(langPref, Lang.en.name()));
    }

    public void changeLang(OnChangeLanguageListener listener, Lang lang) {
        if (lang.name().equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang.name());
        saveLocale(lang.name());
        Locale.setDefault(myLocale);
        Configuration config = new Configuration();
        config.locale = myLocale;
        mContext.getResources().updateConfiguration(config, mContext.getResources().getDisplayMetrics());
        listener.updateTexts(lang);
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

    public enum Lang {
        en, bn, de, fr
    }

    public interface OnChangeLanguageListener {
        void updateTexts(Lang lang);
    }
}
