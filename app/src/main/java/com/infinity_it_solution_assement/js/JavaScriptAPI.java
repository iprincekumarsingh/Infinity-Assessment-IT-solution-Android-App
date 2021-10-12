package com.infinity_it_solution_assement.js;

import android.app.Activity;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.snackbar.Snackbar;
import com.infinity_it_solution_assement.activity.MainActivity;
import com.infinity_it_solution_assement.utility.IntentUtility;
import com.infinity_it_solution_assement.utility.Preferences;
import com.onesignal.OneSignal;


import org.alfonz.utility.DeviceUuidFactory;
import org.alfonz.utility.VersionUtility;

import java.util.UUID;

public class JavaScriptAPI {
	private Activity mActivity;

	public JavaScriptAPI(Activity activity) {
		mActivity = activity;
	}

	@JavascriptInterface
	public String getApplicationId() {
		return mActivity.getApplicationContext().getPackageName();
	}

	@JavascriptInterface
	public int getVersionCode() {
		return VersionUtility.getVersionCode(mActivity);
	}

	@JavascriptInterface
	public String getVersionName() {
		return VersionUtility.getVersionName(mActivity);
	}

	@JavascriptInterface
	public int getDeviceAPILevel() {
		return android.os.Build.VERSION.SDK_INT;
	}

	@JavascriptInterface
	public String getDeviceUniqueId() {
		DeviceUuidFactory deviceUuidFactory = new DeviceUuidFactory(mActivity);
		UUID uuid = deviceUuidFactory.getDeviceUUID();
		return uuid.toString();
	}

	@JavascriptInterface
	public String getFcmRegistrationToken() {
		Preferences preferences = new Preferences();
		return preferences.getFcmRegistrationToken();
	}

	@JavascriptInterface
	public String getOneSignalUserId() {
		Preferences preferences = new Preferences();
		return preferences.getOneSignalUserId();
	}

	@JavascriptInterface
	public void showToast(String message) {
		Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show();
	}

	@JavascriptInterface
	public void showSnackbar(String message) {
		final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) mActivity.findViewById(android.R.id.content)).getChildAt(0);
		Snackbar
				.make(viewGroup, message, Snackbar.LENGTH_LONG)
				.show();
	}

	@JavascriptInterface
	public void showSnackbarWithButton(String message, String button) {
		final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) mActivity.findViewById(android.R.id.content)).getChildAt(0);
		Snackbar
				.make(viewGroup, message, Snackbar.LENGTH_INDEFINITE)
				.setAction(button, view -> {})
				.show();
	}

	@JavascriptInterface
	public void showDialog(String title, String message) {
		new AlertDialog.Builder(mActivity)
				.setTitle(title)
				.setMessage(message)
				.setPositiveButton(android.R.string.ok, (dialog, which) -> {})
				.show();
	}

	@JavascriptInterface
	public void openBrowser(String url) {
		IntentUtility.startWebActivity(mActivity, url);
	}

	@JavascriptInterface
	public void openStore() {
		IntentUtility.startStoreActivity(mActivity);
	}

	@JavascriptInterface
	public void share(String subject, String text) {
		IntentUtility.startShareActivity(mActivity, subject, text);
	}

	@JavascriptInterface
	public void closeApp() {
		mActivity.finish();
	}

	@JavascriptInterface
	public void sendOneSignalTag(String key, String value) {
		OneSignal.sendTag(key, value);
	}

	@JavascriptInterface
	public void showInterstitialAd() {
		((MainActivity) mActivity).showInterstitialAd();
	}
}
