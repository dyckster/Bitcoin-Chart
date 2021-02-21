package com.dyckster.base.ui.chart.animator

import android.animation.Animator
import android.animation.ValueAnimator
import android.view.View
import javax.inject.Inject

private const val TARGET_TRANSLATION_X = 24f
private const val REPEAT_COUNT = 3
private const val DURATION = 700L

class FingerEmojiAnimator
@Inject constructor() {

    fun prepareAnimator(view: View): Animator {
        val animator = ValueAnimator.ofFloat(0f, TARGET_TRANSLATION_X)
        animator.repeatCount = REPEAT_COUNT
        animator.repeatMode = ValueAnimator.REVERSE
        animator.duration = DURATION
        animator.addUpdateListener { value ->
            view.translationX = value.animatedValue as Float
        }
        return animator
    }
}