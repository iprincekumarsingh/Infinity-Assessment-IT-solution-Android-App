package com.infinity_it_solution_assement.ads;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.infinity_it_solution_assement.R;
import com.infinity_it_solution_assement.WebViewAppApplication;
import com.infinity_it_solution_assement.WebViewAppConfig;

public class AdMobInterstitialHelper {
	private static int sInterstitialCounter = 1;

	private InterstitialAd mInterstitialAd;
	private InterstitialAdLoadCallback mInterstitialAdLoadCallback;

	public void setupAd(Context context) {
		String unitId = getUnitId();
		if (!unitId.equals("")) {
			mInterstitialAdLoadCallback = new InterstitialAdLoadCallback() {
				@Override
				public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
					mInterstitialAd = interstitialAd;
					mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
						@Override
						public void onAdShowedFullScreenContent() {
							mInterstitialAd = null;
						}

						@Override
						public void onAdDismissedFullScreenContent() {
							loadAd(context, unitId);
						}
					});
				}

				@Override
				public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
					mInterstitialAd = null;
				}
			};

			loadAd(context, unitId);
		}
	}

	public void checkAd(Activity activity) {
		if (WebViewAppConfig.ADMOB_INTERSTITIAL_FREQUENCY > 0 && sInterstitialCounter % WebViewAppConfig.ADMOB_INTERSTITIAL_FREQUENCY == 0) {
			showAd(activity);
		}
		sInterstitialCounter++;
	}

	public void forceAd(Activity activity) {
		showAd(activity);
		sInterstitialCounter++;
	}

	private void loadAd(Context context, String unitId) {
		InterstitialAd.load(context, unitId, AdMobUtility.createAdRequest(), mInterstitialAdLoadCallback);
	}

	private void showAd(Activity activity) {
		String unitId = getUnitId();
		if (!unitId.equals("")) {
			if (mInterstitialAd != null) {
				mInterstitialAd.show(activity);
			}
		}
	}

	private String getUnitId() {
		Context context = WebViewAppApplication.getContext();
		return WebViewAppConfig.TEST_ADS ? context.getString(R.string.admob_test_unit_id_interstitial) : context.getString(R.string.admob_unit_id_interstitial);
	}
}
