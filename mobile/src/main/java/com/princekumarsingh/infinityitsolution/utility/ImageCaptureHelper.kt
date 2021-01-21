package com.princekumarsingh.infinityitsolution.utility

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Parcelable
import android.provider.MediaStore
import androidx.core.content.FileProvider
import com.princekumarsingh.infinityitsolution.WebViewAppApplication
import java.io.File
import java.io.IOException

class ImageCaptureHelper {
    private var mCapturedImageUri: Uri? = null
    fun setupChooserIntent(chooserIntent: Intent) {
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf<Parcelable>(createImageCaptureIntent()))
    }

    //package im.delight.android.webview;
    val capturedImageUri: Uri?
        get() {
            var uri: Uri? = null
            if (mCapturedImageUri != null) {
                uri = mCapturedImageUri
                mCapturedImageUri = null
            }
            return uri
        }
    val capturedImageUris: Array<Uri>?
        get() {
            var uris: Array<Uri>? = null
            if (mCapturedImageUri != null) {
                uris = arrayOf(mCapturedImageUri!!)
                mCapturedImageUri = null
            }
            return uris
        }

    private fun createImageCaptureIntent(): Intent {
        mCapturedImageUri = imageUri
        val imageCaptureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageUri)
        return imageCaptureIntent
    }

    private val imageUri: Uri?
        private get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val values = ContentValues()
            val filename = System.currentTimeMillis().toString() + ".jpg"
            values.put(MediaStore.Images.Media.TITLE, filename)
            values.put(MediaStore.Images.Media.DISPLAY_NAME, filename)
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000)
            values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/.Infinity")
            WebViewAppApplication.getContext().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                val storageDir = WebViewAppApplication.getContext().getExternalFilesDir(Environment.DIRECTORY_DCIM)
                val photoFile = File.createTempFile(java.lang.Long.toString(System.currentTimeMillis()), ".jpg", storageDir)
                FileProvider.getUriForFile(
                        WebViewAppApplication.getContext(),
                        WebViewAppApplication.getContext().packageName + ".fileprovider",
                        photoFile)
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        } else {
            val externalDir = File(Environment.DIRECTORY_DCIM)
            val cameraDir = File(externalDir.absolutePath + File.separator + ".Infinity")
            cameraDir.mkdirs()
            val filePath = cameraDir.absolutePath + File.separator + System.currentTimeMillis() + ".jpg"
            Uri.fromFile(File(filePath))
        }
}