package com.princekumarsingh.infinityitsolution.utility

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout

object AnimationUtility {
    @JvmStatic
    fun animateLayoutHeight(view: View, height: Int) {
        val animator = ValueAnimator.ofInt(0, height)
        animator.duration = 500
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animator: Animator) {
                view.visibility = View.VISIBLE
            }
        })
        animator.addUpdateListener { animation: ValueAnimator ->
            val value = animation.animatedValue as Int
            val params = view.layoutParams as LinearLayout.LayoutParams
            params.height = value
            view.layoutParams = params
        }
        animator.start()
    }
}