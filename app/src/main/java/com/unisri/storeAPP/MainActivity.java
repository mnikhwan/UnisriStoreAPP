package com.unisri.storeAPP;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.webkit.URLUtil;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    WebView myWeb;
    BottomNavigationView bottomNavigationView;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myWeb = findViewById(R.id.myWeb);
        bottomNavigationView = findViewById(R.id.bottomNavView);
        myWeb.getSettings().setJavaScriptEnabled(true);

        myWeb.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // This method is deprecated in API 24, but still needed for lower API levels
                if (URLUtil.isNetworkUrl(url)) {
                    if(url.contains("unisristore.com")){
                        return false;
                    } else if (url.contains("instagram")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        MainActivity.this.startActivity(Intent.createChooser(intent, "Choose browser"));
                        Toast.makeText(MainActivity.this, "Beli via Instagram", Toast.LENGTH_SHORT).show();
                    } else if (url.contains("wa.me") && url.contains("whatsapp")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        MainActivity.this.startActivity(Intent.createChooser(intent, "Choose browser"));
                        Toast.makeText(MainActivity.this, "Beli via Whatsapp", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        MainActivity.this.startActivity(Intent.createChooser(intent, "Choose browser"));
                        Toast.makeText(MainActivity.this, "Opening Browser", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            int itemId = menuItem.getItemId();

            if (itemId == R.id.navHome){
                myWeb.loadUrl("https://unisristore.com/");
            } else if (itemId == R.id.navToko) {
                myWeb.loadUrl("https://unisristore.com/toko/");
            } else if (itemId == R.id.navHelp) {
                myWeb.loadUrl("https://unisristore.com/cs/");
            } else {
                myWeb.loadUrl("https://unisristore.com/wishlist/");
            }
            return true;
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
//                if (appInstalledOrNot(url)) {
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                    MainActivity.this.startActivity(Intent.createChooser(intent, "Choose browser")); // Corrected here
//                    //MainActivity.this.startActivity(intent); // Corrected here
//                } else {
//                  Toast.makeText(MainActivity.this, "Whatsapp or Instagram NOT Installed", Toast.LENGTH_SHORT).show();
//                }
