package com.leadsoft.multilanguage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.leadsoft.multilanguage.LanguageSettings.Lang.bn;
import static com.leadsoft.multilanguage.LanguageSettings.Lang.de;
import static com.leadsoft.multilanguage.LanguageSettings.Lang.en;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener, LanguageSettings.OnChangeLanguageListener {
    private Button btn_en, btn_bn, btn_fr, btn_de;
    private LanguageSettings languageSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.btn_en = findViewById(R.id.btn_en);
        this.btn_bn = findViewById(R.id.btn_bn);
        this.btn_fr = findViewById(R.id.btn_fr);
        this.btn_de = findViewById(R.id.btn_de);
        this.btn_en.setOnClickListener(this);
        this.btn_bn.setOnClickListener(this);
        this.btn_fr.setOnClickListener(this);
        this.btn_de.setOnClickListener(this);

        languageSettings = new LanguageSettings(this);
        languageSettings.loadLocale(this);
    }

    @Override
    public void updateTexts(LanguageSettings.Lang lang) {
        btn_en.setText(R.string.btn_en);
        btn_bn.setText(R.string.btn_bn);
        btn_fr.setText(R.string.btn_fr);
        btn_de.setText(R.string.btn_de);
    }

    @Override
    public void onClick(View v) {
        LanguageSettings.Lang lang = en;
        switch (v.getId()) {
            case R.id.btn_en:
                lang = en;
                break;
            case R.id.btn_bn:
                lang = bn;
                break;
            case R.id.btn_de:
                lang = de;
                break;
            case R.id.btn_fr:
                lang = LanguageSettings.Lang.fr;
                break;
            default:
                break;
        }
        languageSettings.changeLang(this, lang);
    }

    @Override
    public void onConfigurationChanged(android.content.res.Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        languageSettings.onConfigurationChanged(newConfig);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
