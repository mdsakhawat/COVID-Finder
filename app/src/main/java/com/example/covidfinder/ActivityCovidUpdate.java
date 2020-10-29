package com.example.covidfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.covidfinder.R;

public class ActivityCovidUpdate extends AppCompatActivity {
    private WebView w;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        setContentView(R.layout.activity_avtivity_covid_update);

        w=(WebView) findViewById(R.id.webview);
        progressBar = findViewById(R.id.progressBar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_USER);

        WebSettings websetting=w.getSettings();
        websetting.setBuiltInZoomControls(true);
        websetting.setJavaScriptEnabled(true);
        w.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        w.setWebViewClient(new WebViewClient());
        w.loadUrl("https://corona.gov.bd/");

    }

    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(w.canGoBack())
        {
            w.goBack();
        }
        else

        {

          Intent intent=new Intent(ActivityCovidUpdate.this, ActivityLocationMap.class);
          startActivity(intent);
          finish();
        }

    }


    }

