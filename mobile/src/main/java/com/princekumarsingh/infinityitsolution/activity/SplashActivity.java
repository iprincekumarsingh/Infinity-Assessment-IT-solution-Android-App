package com.princekumarsingh.infinityitsolution.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;



public class SplashActivity extends AppCompatActivity {
	private  final  int splashscenond = 1000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new Handler().postDelayed(() -> {
			Intent splash = new Intent(getApplicationContext(),MainActivity.class);
			startActivity(splash);
			finish();

		},splashscenond);

	}


	}

