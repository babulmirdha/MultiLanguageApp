package com.leadsoft.multilanguage;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView txt_hello;
    private Button btn_en, btn_bn, btn_fr, btn_de;
    private Locale myLocale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.txt_hello = (TextView)findViewById(R.id.txt_hello);
        this.btn_en = (Button) findViewById(R.id.btn_en);
        this.btn_bn = (Button) findViewById(R.id.btn_bn);
        this.btn_fr = (Button)findViewById(R.id.btn_fr);
        this.btn_de = (Button)findViewById(R.id.btn_de);
        this.btn_en.setOnClickListener(this);
        this.btn_bn.setOnClickListener(this);
        this.btn_fr.setOnClickListener(this);
        this.btn_de.setOnClickListener(this);
       // Localization.loadLocale(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        LanguageSettings.setLocale(this);
    }

    private void updateTexts()
    {
        txt_hello.setText(R.string.hello_world);
        btn_en.setText(R.string.btn_en);
        btn_bn.setText(R.string.btn_bn);
        btn_fr.setText(R.string.btn_fr);
        btn_de.setText(R.string.btn_de);
    }
    @Override
    public void onClick(View v) {
        String lang = "en";
        switch (v.getId()) {
            case R.id.btn_en:
                lang = "en";
                break;
            case R.id.btn_bn:
                lang = "bn";
                break;
            case R.id.btn_de:
                lang = "de";
                break;
            case R.id.btn_fr:
                lang = "fr";
                break;
            default:
                break;
        }
//        Localization.saveLocale(this,lang);
//        Localization.loadLocale(this);

        LanguageSettings.setNewLocale(this,lang);

    }

}
