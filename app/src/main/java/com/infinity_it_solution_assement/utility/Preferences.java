package com.infinity_it_solution_assement.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.infinity_it_solution_assement.R;
import com.infinity_it_solution_assement.WebViewAppApplication;

public class Preferences {
	private Context mContext;
	private SharedPreferences mSharedPreferences;

	public Preferences() {
		mContext = WebViewAppApplication.getContext();
		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
	}

	public void clearPreferences() {
		mSharedPreferences.edit().clear().apply();
	}

	public int getInAppReviewDialogCounter() {
		String key = mContext.getString(R.string.prefs_key_inapp_review_dialog_counter);
		return mSharedPreferences.getInt(key, 0);
	}

	public void setInAppReviewDialogCounter(int inAppReviewDialogCounter) {
		String key = mContext.getString(R.string.prefs_key_inapp_review_dialog_counter);
		mSharedPreferences.edit().putInt(key, inAppReviewDialogCounter).apply();
	}

	public int getRateAppPromptCounter() {
		String key = mContext.getString(R.string.prefs_key_rate_app_prompt_counter);
		return mSharedPreferences.getInt(key, 0);
	}

	public void setRateAppPromptCounter(int rateAppPromptCounter) {
		String key = mContext.getString(R.string.prefs_key_rate_app_prompt_counter);
		mSharedPreferences.edit().putInt(key, rateAppPromptCounter).apply();
	}

	public String getFcmRegistrationToken() {
		String key = mContext.getString(R.string.prefs_key_fcm_registration_token);
		return mSharedPreferences.getString(key, "");
	}

	public void setFcmRegistrationToken(String fcmRegistrationToken) {
		String key = mContext.getString(R.string.prefs_key_fcm_registration_token);
		mSharedPreferences.edit().putString(key, fcmRegistrationToken).apply();
	}

	public String getOneSignalUserId() {
		String key = mContext.getString(R.string.prefs_key_one_signal_user_id);
		return mSharedPreferences.getString(key, "");
	}

	public void setOneSignalUserId(String oneSignalUserId) {
		String key = mContext.getString(R.string.prefs_key_one_signal_user_id);
		mSharedPreferences.edit().putString(key, oneSignalUserId).apply();
	}
}
