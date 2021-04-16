package com.drmarks.constructionfriend;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class TendorActivity extends AppCompatActivity {
    WebView webtendor;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tendor);

        webtendor =findViewById(R.id.webtendor);
        webtendor.setWebViewClient(new WebViewClient());
        webtendor.loadUrl("https://www.gujarattenders.in/");
        webtendor.getSettings().setJavaScriptEnabled(true);

    }

    @Override
    public void onBackPressed() {
        if(webtendor.canGoBack()){
            webtendor.goBack();
        }else {
        super.onBackPressed();
    }
}
}