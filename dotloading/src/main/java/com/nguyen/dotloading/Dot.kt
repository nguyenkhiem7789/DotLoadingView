package com.nguyen.dotloading

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF

class Dot {

    private val paint: Paint by lazy {
        Paint()
    }

    private val center: PointF by lazy {
        PointF()
    }

    var radius: Float = 4.0f

    init {
        paint.isAntiAlias = true
    }

    fun setColor(color: Int) {
        paint.color = color
    }

    fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    fun setCenter(x: Float, y: Float) {
        center.set(x, y)
    }

    fun draw(canvas: Canvas) {
        canvas.drawCircle(center.x, center.y, radius, paint)
    }

}