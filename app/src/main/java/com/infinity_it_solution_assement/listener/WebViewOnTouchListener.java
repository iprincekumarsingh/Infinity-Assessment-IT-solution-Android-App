package com.infinity_it_solution_assement.listener;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

public class WebViewOnTouchListener implements View.OnTouchListener {
	@Override
	@SuppressLint("ClickableViewAccessibility")
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_UP:
				if (!v.hasFocus()) {
					v.requestFocus();
				}
				break;
		}

		return false;
	}
}
