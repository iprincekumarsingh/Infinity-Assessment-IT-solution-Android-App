package com.infinity_it_solution_assement;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.google.android.gms.ads.MobileAds;

import com.onesignal.OneSignal;
import com.infinity_it_solution_assement.utility.Preferences;
import com.robotemplates.kozuza.BaseApplication;
import com.robotemplates.kozuza.Kozuza;


import org.alfonz.utility.Logcat;

import static com.google.firebase.analytics.FirebaseAnalytics.*;

public class WebViewAppApplication extends BaseApplication {
	@Override
	public void onCreate() {
		super.onCreate();

		// init logcat
		Logcat.init(WebViewAppConfig.LOGS, "WEBVIEWAPP");

		// init analytics
		getInstance(this).setAnalyticsCollectionEnabled(!WebViewAppConfig.DEV_ENVIRONMENT);

		// init AdMob
		MobileAds.initialize(this);

		// init OneSignal
//		initOneSignal(getString(R.string.onesignal_app_id));
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}

//	@Override
//	public String getPurchaseCode() {
//		return WebViewAppConfig.PURCHASE_CODE;
//	}

	@Override
	public String getPurchaseCode() {
		return null;
	}

	@Override
	public String getProduct() {
		return Kozuza.PRODUCT_WEBVIEWAPP;
	}

//	private void initOneSignal(String oneSignalAppId) {
//		if (!oneSignalAppId.equals("")) {
//			OneSignal.startInit(this)
//					.setNotificationOpenedHandler(new OneSignalNotificationOpenedHandler())
//					.inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//					.unsubscribeWhenNotificationsAreDisabled(true)
//					.init();
//			saveOneSignalUserId();
//		}
//	}

	private void saveOneSignalUserId() {
		String userId = OneSignal.getPermissionSubscriptionState().getSubscriptionStatus().getUserId();
		if (userId != null) {
			Logcat.d("userId = " + userId);
			Preferences preferences = new Preferences();
			preferences.setOneSignalUserId(userId);
		}
	}
}
