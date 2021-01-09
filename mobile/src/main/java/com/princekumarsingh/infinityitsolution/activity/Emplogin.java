package com.princekumarsingh.infinityitsolution.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.princekumarsingh.infinityitsolution.R;
import com.princekumarsingh.infinityitsolution.activity.ui.main.EmploginFragment;

public class Emplogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emplogin_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, EmploginFragment.newInstance())
                    .commitNow();
        }
    }
}