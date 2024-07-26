package com.techmania.tazzakhabar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FullNewsActivity extends AppCompatActivity {
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_news);

        webview =findViewById(R.id.webview);
        String url = getIntent().getStringExtra("url");

        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient());
        WebSettings webset = webview.getSettings();
        webset.setJavaScriptEnabled(true);

    }
}