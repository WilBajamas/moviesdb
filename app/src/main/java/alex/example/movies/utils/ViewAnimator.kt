package alex.example.movies.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

class ViewAnimator {

    private var shortAnimationDuration: Int = 0

     fun crossFadeView(fadeInView: View, fadeOutView: View, sideEffect: () -> Unit) {
        sideEffect()
        fadeInView.apply {
            alpha = 0f
            visibility = View.VISIBLE

            animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration.toLong())
                .setListener(null)
        }
        fadeOutView.animate()
            .alpha(0f)
            .setDuration(shortAnimationDuration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    fadeOutView.visibility = View.GONE
                }
            })
    }

}