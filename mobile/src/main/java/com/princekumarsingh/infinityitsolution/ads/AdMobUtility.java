package com.princekumarsingh.infinityitsolution.ads;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.princekumarsingh.infinityitsolution.R;
import com.princekumarsingh.infinityitsolution.utility.AnimationUtility;

import com.princekumarsingh.infinityitsolution.WebViewAppApplication;
import com.princekumarsingh.infinityitsolution.WebViewAppConfig;

public final class AdMobUtility {
	private AdMobUtility() {}

	public static AdRequest createAdRequest() {
		String testDeviceId = WebViewAppApplication.getContext().getString(R.string.admob_test_device_id);
		AdRequest.Builder builder = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice(testDeviceId);

		if (WebViewAppConfig.GDPR_PRIVACY_POLICY_URL != null && !WebViewAppConfig.GDPR_PRIVACY_POLICY_URL.equals("")) {
			builder.addNetworkExtrasBundle(AdMobAdapter.class, AdMobGdprHelper.getConsentStatusBundle());
		}

		return builder.build();
	}

	public static AdListener createAdListener(final AdView adView) {
		return new AdListener() {
			@Override
			public void onAdLoaded() {
				super.onAdLoaded();
				AnimationUtility.animateLayoutHeight(adView, adView.getAdSize().getHeightInPixels(adView.getContext()));
			}

			@Override
			public void onAdFailedToLoad(int errorCode) {
				super.onAdFailedToLoad(errorCode);
				adView.setVisibility(View.GONE);
			}
		};
	}

	public static AdView createAdView(Context context, String adUnitId, AdSize adSize, ViewGroup viewGroup) {
		// layout params
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.CENTER;

		// create ad view
		AdView adView = new AdView(context);
		adView.setId(R.id.adview);
		adView.setLayoutParams(params);
		adView.setVisibility(View.GONE);
		adView.setAdUnitId(adUnitId);
		adView.setAdSize(adSize);
		adView.setAdListener(createAdListener(adView));

		// add to layout
		viewGroup.removeView(viewGroup.findViewById(R.id.adview));
		viewGroup.addView(adView);

		// call ad request
		adView.loadAd(createAdRequest());

		return adView;
	}

	public static AdSize getAdaptiveBannerAdSize(Context context) {
		Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		DisplayMetrics displayMetrics = new DisplayMetrics();
		display.getMetrics(displayMetrics);

		float widthPixels = displayMetrics.widthPixels;
		float density = displayMetrics.density;
		int adWidth = (int) (widthPixels / density);

		return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth);
	}
}
