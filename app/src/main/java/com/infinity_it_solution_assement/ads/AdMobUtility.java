package com.infinity_it_solution_assement.ads;

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
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.RequestConfiguration;
import com.infinity_it_solution_assement.R;
import com.infinity_it_solution_assement.WebViewAppApplication;
import com.infinity_it_solution_assement.WebViewAppConfig;
import com.infinity_it_solution_assement.utility.AnimationUtility;

import java.util.ArrayList;
import java.util.List;

public final class AdMobUtility {
	private AdMobUtility() {}

	public static RequestConfiguration createRequestConfiguration() {
		List<String> testDeviceIds = new ArrayList<>();
		testDeviceIds.add(AdRequest.DEVICE_ID_EMULATOR);
		testDeviceIds.add(WebViewAppApplication.getContext().getString(R.string.admob_test_device_id));

		return new RequestConfiguration.Builder()
				.setTestDeviceIds(testDeviceIds)
				.build();
	}

	public static AdRequest createAdRequest() {
		AdRequest.Builder builder = new AdRequest.Builder();

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
			public void onAdFailedToLoad(LoadAdError loadAdError) {
				super.onAdFailedToLoad(loadAdError);
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
