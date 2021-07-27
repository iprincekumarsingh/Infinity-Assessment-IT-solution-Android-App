package com.infinity_it_solution_assement.utility

import android.Manifest
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.infinity_it_solution_assement.R
import org.alfonz.utility.PermissionManager.ConfirmAction
import org.alfonz.utility.PermissionManager.RationaleHandler

class PermissionRationaleHandler : RationaleHandler {
    override fun getRationaleMessage(permission: String): String {
        val resId: Int = when (permission) {
            Manifest.permission.READ_EXTERNAL_STORAGE -> R.string.permission_read_external_storage
            Manifest.permission.WRITE_EXTERNAL_STORAGE -> R.string.permission_write_external_storage
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION -> R.string.permission_access_location
            Manifest.permission.CAMERA -> R.string.permission_camera
            else -> R.string.permission_unknown
        }
        return com.infinity_it_solution_assement.WebViewAppApplication.getContext().getString(resId)
    }

    override fun showRationale(rootView: View, rationaleMessage: String, confirmAction: ConfirmAction) {
        Snackbar
                .make(rootView, rationaleMessage, Snackbar.LENGTH_INDEFINITE)
                .setAction(android.R.string.ok) { confirmAction.run() }
                .show()

    }
}