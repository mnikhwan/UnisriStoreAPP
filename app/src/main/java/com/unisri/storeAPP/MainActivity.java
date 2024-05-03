package com.unisri.storeAPP;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.webkit.URLUtil;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    WebView myWeb;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myWeb = findViewById(R.id.myWeb);
        myWeb.getSettings().setJavaScriptEnabled(true);

        myWeb.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // This method is deprecated in API 24, but still needed for lower API levels
                if (URLUtil.isNetworkUrl(url)) {
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                    MainActivity.this.startActivity(Intent.createChooser(intent, "Choose browser"));
                    return false;
                }
                if (appInstalledOrNot(url)) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    MainActivity.this.startActivity(Intent.createChooser(intent, "Choose browser")); // Corrected here
                    //MainActivity.this.startActivity(intent); // Corrected here
                } else {
                    Toast.makeText(MainActivity.this, "Whatsapp or Instagram NOT Installed", Toast.LENGTH_SHORT).show();
                }
                return true;
            }


        });

        myWeb.loadUrl("https://unisristore.com/");
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return false;
    }
}
