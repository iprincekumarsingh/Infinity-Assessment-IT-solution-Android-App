package com.infinity_it_solution_assement.utility;

import android.annotation.SuppressLint;
import android.app.Activity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;
import com.infinity_it_solution_assement.R;
import com.infinity_it_solution_assement.WebViewAppConfig;


import org.alfonz.utility.Logcat;

public final class RatingUtility {
	private RatingUtility() {}

	public static boolean checkRateAppPrompt(Activity activity) {
		// check if rate app prompt is disabled
		if (WebViewAppConfig.RATE_APP_PROMPT_FREQUENCY == 0) return false;

		// get counter
		final Preferences preferences = new Preferences();
		final int counter = preferences.getRateAppPromptCounter();
		Logcat.d("" + counter);

		// check counter
		boolean showPrompt = false;
		if (counter != -1) {
			if (counter >= WebViewAppConfig.RATE_APP_PROMPT_FREQUENCY && counter % WebViewAppConfig.RATE_APP_PROMPT_FREQUENCY == 0) {
				showPrompt = true;
			}
		} else {
			return false;
		}

		// show rate app prompt
		if (showPrompt) {
			showRateAppPrompt(activity);
		}

		// increment counter
		preferences.setRateAppPromptCounter(counter + 1);
		return showPrompt;
	}

	public static boolean checkInAppReviewDialog(Activity activity) {
		// check if in-app review dialog is disabled
		if (WebViewAppConfig.INAPP_REVIEW_DIALOG_FREQUENCY == 0) return false;

		// get counter
		final Preferences preferences = new Preferences();
		final int counter = preferences.getInAppReviewDialogCounter();
		Logcat.d("" + counter);

		// check counter
		boolean showDialog = false;
		if (counter != -1) {
			if (counter >= WebViewAppConfig.INAPP_REVIEW_DIALOG_FREQUENCY && counter % WebViewAppConfig.INAPP_REVIEW_DIALOG_FREQUENCY == 0) {
				showDialog = true;
			}
		} else {
			return false;
		}

		// show in-app review dialog
		if (showDialog) {
			showInAppReviewDialog(activity);
		}

		// increment counter
		preferences.setInAppReviewDialogCounter(counter + 1);
		return showDialog;
	}

	@SuppressLint("WrongConstant")
	private static void showRateAppPrompt(Activity activity) {
		Snackbar
				.make(activity.findViewById(R.id.main_coordinator_layout), R.string.main_rate_snackbar, WebViewAppConfig.RATE_APP_PROMPT_DURATION)
				.setAction(R.string.main_rate_confirm, view -> {
					IntentUtility.startStoreActivity(activity);
					Preferences preferences = new Preferences();
					preferences.setRateAppPromptCounter(-1);
				})
				.show();
	}

	private static void showInAppReviewDialog(Activity activity) {
		ReviewManager reviewManager = ReviewManagerFactory.create(activity);
		//ReviewManager reviewManager = new FakeReviewManager(activity);
		Task<ReviewInfo> request = reviewManager.requestReviewFlow();
		request.addOnCompleteListener(requestTask -> {
			if (requestTask.isSuccessful()) {
				Logcat.d("success");
				ReviewInfo reviewInfo = requestTask.getResult();
				Task<Void> launch = reviewManager.launchReviewFlow(activity, reviewInfo);
				launch.addOnCompleteListener(launchTask -> {
					Logcat.d("launch");
				});
			} else {
				Logcat.d("error " + requestTask.getException().getMessage());
			}
		});
	}
}
