package com.example.aida.shoppinglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Instructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        //LUO WEBNÄKYMÄ OLIO
        WebView webViewInstructions;
        //XML-LAYOTISTA WEBNÄKYMÄN VIITTAUKSEN HAKEMINEN JA TYYPPIMUUNNOS
        webViewInstructions = (WebView)findViewById(R.id.webViewInstructions);
        //HTML-TIEDOSTON LATAAMINEN WEBNÄKYMÄÄN
        webViewInstructions.loadUrl("file:///android_asset/Instructions.html");
    }
}
