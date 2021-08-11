package dev.nda.bookingdemo.extension

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.widget.ImageView

fun ImageView.makeGreyScale() {
    val colorMatrix = ColorMatrix()
    colorMatrix.setSaturation(0.0F)
    colorFilter = ColorMatrixColorFilter(colorMatrix)
    imageAlpha = 128
}

fun ImageView.makeNormalScale() {
    colorFilter = null
    imageAlpha = 255
}