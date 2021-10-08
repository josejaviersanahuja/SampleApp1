package com.zitrojjdev.sampleapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActvity extends AppCompatActivity {
    private static final String TAG = "onWebViewActivity";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_actvity);

        // receiving the url from previous intent
        Intent intent = getIntent();
        String url ="";
        try {
            url= intent.getStringExtra("url");
        } catch (NullPointerException e){
            Log.d(TAG, "onCreate: No url from intent");
        }

        // initWidget
        webView = findViewById(R.id.webView);

        // para evitar que se abra con el navegador
        webView.setWebViewClient(new WebViewClient());

        // open javascripts for websites to work
        webView.getSettings().setJavaScriptEnabled(true);

        // load url
        webView.loadUrl(url);
    }

    // Como navegamos dentro de nuestra app, el boton de go back de android puede navegar atrás dentro del webView
    // o puede navegar atrás en nuestra APP y sacarnos del webView. Por eso sobreescribimos el método
    @Override
    public void onBackPressed() {
        // si podemos navegar atrás dentro del webView,
        if (webView.canGoBack()){
            webView.goBack();
        } else {
            // Si no podemos ir atras en el webView, vamos atras en la APP
            super.onBackPressed();
        }
    }
}