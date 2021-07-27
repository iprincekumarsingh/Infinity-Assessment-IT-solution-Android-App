package com.infinity_it_solution_assement.utility

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.MailTo
import android.net.Uri
import com.infinity_it_solution_assement.R

object IntentUtility {
    @JvmStatic
	fun startIntentActivity(context: Context, url: String?): Boolean {
        return if (url != null && url.startsWith("mailto:")) {
            val mailTo = MailTo.parse(url)
            startEmailActivity(context, mailTo.to, mailTo.subject, mailTo.body)
            true
        } else if (url != null && url.startsWith("tel:")) {
            startCallActivity(context, url)
            true
        } else if (url != null && url.startsWith("sms:")) {
            startSmsActivity(context, url)
            true
        } else if (url != null && url.startsWith("geo:")) {
            startMapSearchActivity(context, url)
            true
        } else if (url != null && url.startsWith("fb://")) {
            startWebActivity(context, url)
            true
        } else if (url != null && url.startsWith("twitter://")) {
            startWebActivity(context, url)
            true
        } else if (url != null && url.startsWith("whatsapp://")) {
            startWebActivity(context, url)
            true
        } else {
            false
        }
    }

    @JvmStatic
	fun startWebActivity(context: Context, url: String?) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // can't start activity
            e.printStackTrace()
        }
    }

    fun startEmailActivity(context: Context, email: String, subject: String?, text: String?) {
        try {
            val uri = "mailto:$email"
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse(uri))
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(Intent.EXTRA_TEXT, text)
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // can't start activity
            e.printStackTrace()
        }
    }

    fun startCallActivity(context: Context, url: String?) {
        try {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse(url))
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // can't start activity
            e.printStackTrace()
        }
    }

    fun startSmsActivity(context: Context, url: String?) {
        try {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse(url))
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // can't start activity
            e.printStackTrace()
        }
    }

    fun startMapSearchActivity(context: Context, url: String?) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // can't start activity
            e.printStackTrace()
        }
    }

    @JvmStatic
	fun startShareActivity(context: Context, subject: String?, text: String?) {
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(Intent.EXTRA_TEXT, text)
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // can't start activity
            e.printStackTrace()
        }
    }

    @JvmStatic
	fun startStoreActivity(context: Context) {
        try {
            val uri = context.getString(R.string.app_store_uri, com.infinity_it_solution_assement.WebViewAppApplication.getContext().packageName)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // can't start activity
            e.printStackTrace()
        }
    }
}