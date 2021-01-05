package com.princekumarsingh.infinityitsolution.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.LinearLayout;

import com.princekumarsingh.infinityitsolution.R;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar =  findViewById(R.id.hometoolbar);
        setSupportActionBar(toolbar);
        setTitle("Setting");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                finish();
            }
        });




//        privacy();
//        contact();
        clearcache();




    }


    private void clearcache() {
        LinearLayout cache = findViewById(R.id.clearcache);
        cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);

                ii.setData(Uri.parse("package:" + getPackageName()));
                startActivity(ii);

            }
        });
    }


//    private void contact() {
//        LinearLayout contactl =  findViewById(R.id.contactus);
//        contactl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent cont = new Intent(Setting.this,Contact_us_.class);
//                startActivity(cont);
//
//            }
//        });
//
//    }
//    public  void privacy(){
//        LinearLayout privacyweb = (LinearLayout) findViewById(R.id.privacypolicy);
//        privacyweb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent pri = new Intent(SettingActivity.this,PrivacyPolicy.class);
//                startActivity(pri);
//
//            }
//        });
//    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.smenu,menu);
//        return true;
//
//    }




//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId())
//        {
////            case  R.id.settingtool:
////                Intent st = new Intent(SettingActivity.this,Dashboard.class);
////                startActivity(st);
//
//        }
//        return true;
//    }
    }
