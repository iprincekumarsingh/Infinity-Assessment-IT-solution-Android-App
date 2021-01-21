package com.princekumarsingh.infinityitsolution.utility

import android.content.Context
import android.content.SharedPreferences
import com.princekumarsingh.infinityitsolution.R
import com.princekumarsingh.infinityitsolution.WebViewAppApplication
import android.preference.PreferenceManager

class Preferences {
    private val mContext: Context
    private val mSharedPreferences: SharedPreferences
    fun clearPreferences() {
        mSharedPreferences.edit().clear().apply()
    }

    var rateCounter: Int
        get() {
            val key = mContext.getString(R.string.prefs_key_rate_counter)
            return mSharedPreferences.getInt(key, 0)
        }
        set(rateCounter) {
            val key = mContext.getString(R.string.prefs_key_rate_counter)
            mSharedPreferences.edit().putInt(key, rateCounter).apply()
        }
    var fcmRegistrationToken: String?
        get() {
            val key = mContext.getString(R.string.prefs_key_fcm_registration_token)
            return mSharedPreferences.getString(key, "")
        }
        set(fcmRegistrationToken) {
            val key = mContext.getString(R.string.prefs_key_fcm_registration_token)
            mSharedPreferences.edit().putString(key, fcmRegistrationToken).apply()
        }
    var oneSignalUserId: String?
        get() {
            val key = mContext.getString(R.string.prefs_key_one_signal_user_id)
            return mSharedPreferences.getString(key, "")
        }
        set(oneSignalUserId) {
            val key = mContext.getString(R.string.prefs_key_one_signal_user_id)
            mSharedPreferences.edit().putString(key, oneSignalUserId).apply()
        }

    init {
        mContext = WebViewAppApplication.getContext()
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)
    }
}