package com.princekumarsingh.infinityitsolution.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.crashlytics.internal.common.CrashlyticsCore;
import com.princekumarsingh.infinityitsolution.R;
import com.princekumarsingh.infinityitsolution.listener.DrawerStateListener;
import com.princekumarsingh.infinityitsolution.listener.LoadUrlListener;
import com.robotemplates.kozuza.Kozuza;

import com.princekumarsingh.infinityitsolution.WebViewAppConfig;
import com.princekumarsingh.infinityitsolution.ads.AdMobGdprHelper;
import com.princekumarsingh.infinityitsolution.ads.AdMobInterstitialHelper;
import com.princekumarsingh.infinityitsolution.fragment.MainFragment;
import com.princekumarsingh.infinityitsolution.utility.IntentUtility;
import com.princekumarsingh.infinityitsolution.utility.Preferences;

import org.alfonz.utility.Logcat;

public class MainActivity extends AppCompatActivity implements LoadUrlListener, DrawerStateListener {
	public static final String EXTRA_URL = "url";

	private DrawerLayout mDrawerLayout;
	private NavigationView mNavigationView;
	private ActionBarDrawerToggle mDrawerToggle;
	private String mUrl;
	private final AdMobInterstitialHelper mAdMobInterstitialHelper = new AdMobInterstitialHelper();

	public static Intent newIntent(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return intent;
	}

	public static Intent newIntent(Context context, String url) {
		Intent intent = new Intent(context, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra(EXTRA_URL, url);
		return intent;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);



		// handle intent extras
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			handleExtras(extras);
		}

		// handle intent data
		String data = getIntent().getDataString();
		if (data != null) {
			handleData(data);
		}

		// setup action bar and drawer
		setupActionBar();
		setupDrawer(savedInstanceState);

//		getActionBar().setDisplayShowTitleEnabled(false);
getSupportActionBar().setDisplayShowTitleEnabled(false);
		// bind data
		setupView();

		// gdpr
		AdMobGdprHelper adMobGdprHelper = new AdMobGdprHelper(this, getString(R.string.admob_publisher_id), WebViewAppConfig.GDPR_PRIVACY_POLICY_URL);
		adMobGdprHelper.requestConsent();

		// admob
		mAdMobInterstitialHelper.setupAd(this);


		// rate prompt
		checkRateCounter();

		// kozuza
		Kozuza.process(this);
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);

		// forward activity result to fragment
		Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container_drawer_content);
		if (fragment != null) {
			fragment.onActivityResult(requestCode, resultCode, intent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// action bar menu
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		// open or close the drawer if home button is pressed
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		// action bar menu behavior
		if (item.getItemId() == android.R.id.home) {
			Intent intent = MainActivity.newIntent(this);
			startActivity(intent);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(@NonNull Configuration newConfiguration) {
		super.onConfigurationChanged(newConfiguration);
		mDrawerToggle.onConfigurationChanged(newConfiguration);
	}

	@Override
	public void onBackPressed() {
		if (isDrawerOpen()) {
			mDrawerLayout.closeDrawer(GravityCompat.START);
		} else {
			if (WebViewAppConfig.EXIT_CONFIRMATION) {
				Snackbar
						.make(findViewById(R.id.main_coordinator_layout), R.string.main_exit_snackbar, Snackbar.LENGTH_LONG)
						.setAction(R.string.main_exit_confirm, view -> MainActivity.super.onBackPressed())
						.show();
			} else {
				super.onBackPressed();
			}
		}
	}

	@Override
	public void onLoadUrl(String url) {
		mAdMobInterstitialHelper.checkAd();
	}

	@Override
	public boolean isDrawerOpen() {
		return mDrawerLayout.isDrawerOpen(GravityCompat.START);
	}

	@Override
	public void onBackButtonPressed() {
		onBackPressed();
	}

	private void setupActionBar() {
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		ActionBar bar = getSupportActionBar();
		bar.setDisplayUseLogoEnabled(false);
		bar.setDisplayShowTitleEnabled(true);
		bar.setDisplayShowHomeEnabled(true);
		bar.setDisplayHomeAsUpEnabled(WebViewAppConfig.NAVIGATION_DRAWER);
		bar.setHomeButtonEnabled(WebViewAppConfig.NAVIGATION_DRAWER);
		if (!WebViewAppConfig.ACTION_BAR) bar.hide();
	}

	private void setupDrawer(Bundle savedInstanceState) {
		// reference
		mDrawerLayout = findViewById(R.id.main_drawer_layout);
		mNavigationView = findViewById(R.id.main_drawer_navigation);

		// add menu items
		MenuItem firstItem = setupMenu(mNavigationView.getMenu());

		// menu icon tint
		if (!WebViewAppConfig.NAVIGATION_DRAWER_ICON_TINT) {
			mNavigationView.setItemIconTintList(null);
		}

		// navigation listener
		mNavigationView.setNavigationItemSelectedListener(item -> {
			// check interstitial ad
			mAdMobInterstitialHelper.checkAd();

			// select drawer item
			selectDrawerItem(item);
			return true;
		});

		// drawer toggle
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
			@Override
			public void onDrawerClosed(View view) {
				supportInvalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				supportInvalidateOptionsMenu();
			}
		};
		mDrawerLayout.addDrawerListener(mDrawerToggle);

		// disable navigation drawer
		if (!WebViewAppConfig.NAVIGATION_DRAWER) {
			mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.START);
		}

		// show initial fragment
		if (savedInstanceState == null) {
			if (mUrl == null) {
				selectDrawerItem(firstItem);
			} else {
				selectDrawerItem(mUrl);
			}
		}
	}

	private MenuItem setupMenu(Menu menu) {
		// title list
		String[] titles = getResources().getStringArray(R.array.navigation_title_list);

		// url list
		String[] urls = getResources().getStringArray(R.array.navigation_url_list);

		// icon list
		TypedArray iconTypedArray = getResources().obtainTypedArray(R.array.navigation_icon_list);
		Integer[] icons = new Integer[iconTypedArray.length()];
		for (int i = 0; i < iconTypedArray.length(); i++) {
			icons[i] = iconTypedArray.getResourceId(i, -1);
		}
		iconTypedArray.recycle();

		// clear menu
		menu.clear();

		// add menu items
		Menu parent = menu;
		MenuItem firstItem = null;
		for (int i = 0; i < titles.length; i++) {
			if (urls[i].equals("")) {
				// category
				parent = menu.addSubMenu(Menu.NONE, i, i, titles[i]);
			} else {
				// item
				MenuItem item = parent.add(Menu.NONE, i, i, titles[i]);
				if (icons[i] != -1) item.setIcon(icons[i]);
				if (firstItem == null) firstItem = item;
			}
		}

		return firstItem;
	}

	private void selectDrawerItem(MenuItem item) {
		int position = item.getItemId();

		String[] urlList = getResources().getStringArray(R.array.navigation_url_list);
		String[] shareList = getResources().getStringArray(R.array.navigation_share_list);

		String url = urlList[position];
		if (IntentUtility.startIntentActivity(this, url)) return; // check for intent url

		Fragment fragment = MainFragment.newInstance(url, shareList[position]);
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.container_drawer_content, fragment).commitAllowingStateLoss();

		item.setCheckable(true);
		mNavigationView.setCheckedItem(position);
		getSupportActionBar().setTitle(item.getTitle());
		mDrawerLayout.closeDrawers();
	}

	private void selectDrawerItem(String url) {
		if (IntentUtility.startIntentActivity(this, url)) return; // check for intent url

		Fragment fragment = MainFragment.newInstance(url, "");
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.container_drawer_content, fragment).commitAllowingStateLoss();

		mNavigationView.setCheckedItem(-1);
		getSupportActionBar().setTitle(getString(R.string.app_name));
		mDrawerLayout.closeDrawers();
	}

	private void handleExtras(Bundle extras) {
		if (extras.containsKey(EXTRA_URL)) {
			mUrl = extras.getString(EXTRA_URL);
		}
	}

	private void handleData(String data) {
		mUrl = data;
	}

	private void setupView() {
		// reference
		NavigationView navigationView = findViewById(R.id.main_drawer_navigation);

		// inflate navigation header
		if (navigationView.getHeaderView(0) == null) {
			View headerView = getLayoutInflater().inflate(R.layout.navigation_header, navigationView, false);
			navigationView.addHeaderView(headerView);
		}

		// navigation header content
		if (navigationView.getHeaderView(0) != null) {
			// reference
			View headerView = navigationView.getHeaderView(0);

			// header background
			if (WebViewAppConfig.NAVIGATION_DRAWER_HEADER_IMAGE) {
				headerView.setBackgroundResource(R.drawable.navigation_header_bg);
			}
		}
	}

	@SuppressLint("WrongConstant")
	private void checkRateCounter() {
		// check if rate prompt is disabled
		if (WebViewAppConfig.RATE_APP_PROMPT_FREQUENCY == 0) return;

		// get current rate counter
		final Preferences preferences = new Preferences();
		final int rateCounter = preferences.getRateCounter();
		Logcat.d("" + rateCounter);

		// check rate counter
		boolean showMessage = false;
		if (rateCounter != -1) {
			if (rateCounter >= WebViewAppConfig.RATE_APP_PROMPT_FREQUENCY && rateCounter % WebViewAppConfig.RATE_APP_PROMPT_FREQUENCY == 0) {
				showMessage = true;
			}
		} else {
			return;
		}

		// show rate message
		if (showMessage) {
			Snackbar
					.make(findViewById(R.id.main_coordinator_layout), R.string.main_rate_snackbar, WebViewAppConfig.RATE_APP_PROMPT_DURATION)
					.setAction(R.string.main_rate_confirm, view -> {
						IntentUtility.startStoreActivity(MainActivity.this);
						preferences.setRateCounter(-1);
					})
					.show();
		}

		// increment rate counter
		preferences.setRateCounter(rateCounter + 1);
	}
}
