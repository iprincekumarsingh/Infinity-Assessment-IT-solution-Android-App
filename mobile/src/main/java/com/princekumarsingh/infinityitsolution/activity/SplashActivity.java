package com.princekumarsingh.infinityitsolution.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread timerThread = new Thread(){
			public void run(){
				try{
					sleep(2000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent intent = new Intent(SplashActivity.this, MainActivity.class);
					startActivity(intent);
				}
			}
		};
		timerThread.start();
	}


	@Override
	protected void onPause() {
		/* TODO Auto-generated method stub */
		super.onPause();
		finish();
	}
	}

