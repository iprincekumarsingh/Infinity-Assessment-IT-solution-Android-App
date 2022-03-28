package com.infinity_it_solution_assement;
import android.content.Context;
import androidx.multidex.MultiDex;
import com.robotemplates.kozuza.BaseApplication;
import com.robotemplates.kozuza.Kozuza;


import org.alfonz.utility.Logcat;

public class WebViewAppApplication extends BaseApplication {
	@Override
	public void onCreate() {
		super.onCreate();

		// init logcat
		Logcat.init(WebViewAppConfig.LOGS, "WEBVIEWAPP");

	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}


	@Override
	public String getPurchaseCode() {
		return null;
	}

	@Override
	public String getProduct() {
		return Kozuza.PRODUCT_WEBVIEWAPP;
	}


}
