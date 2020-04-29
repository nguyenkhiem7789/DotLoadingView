package com.nguyen.dotloading

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import androidx.core.content.res.ResourcesCompat

class DotLoadingView: View {

    companion object {
        const val DEFAULT_MIN_RADIUS = 4

        const val DEFAULT_MAX_RADIUS = 16

        const val DEFAULT_DISTANCE = 28

        const val DEFAULT_COUNT = 3
    }

    lateinit var extraBitmap: Bitmap

    lateinit var extraCanvas: Canvas

    public var minRadius: Int = DEFAULT_MIN_RADIUS

    public var maxRadius: Int = DEFAULT_MAX_RADIUS

    public var distance: Int = DEFAULT_DISTANCE

    public var count: Int = DEFAULT_COUNT

    public var dotColor: Int = 0

    val dots = ArrayList<Dot>()

    val bgColor: Int by lazy {
        ResourcesCompat.getColor(resources, R.color.white, null)
    }

    constructor(context: Context) : this(context, null) {
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.DotLoadingView)
        minRadius = typeArray.getDimensionPixelSize(R.styleable.DotLoadingView_dot_min_radius, DEFAULT_MIN_RADIUS)
        maxRadius = typeArray.getDimensionPixelSize(R.styleable.DotLoadingView_dot_max_radius, DEFAULT_MAX_RADIUS)
        distance = typeArray.getDimensionPixelSize(R.styleable.DotLoadingView_dot_distance, DEFAULT_DISTANCE)
        count = typeArray.getInt(R.styleable.DotLoadingView_dot_count, DEFAULT_COUNT)
        dotColor = typeArray.getColor(R.styleable.DotLoadingView_dot_color, Color.parseColor("#aaaaaa"))
        typeArray.recycle()
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    fun init() {
        dotColor = ResourcesCompat.getColor(resources, R.color.blue, null)

        for(i in 0 .. (count - 1)) {
            dots.add(Dot())
        }
        for(i in 0 .. (count - 1)) {
            changeNewRadius(i, maxRadius.toFloat())
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        //init background
        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(bgColor)

        //init array dot
        val xCenter = width/2
        val yCenter = height/2
        val d = 2 * minRadius + distance
        val firstXCenter = xCenter - ((dots.size - 1) * d / 2)
        for(i in 0 .. dots.size - 1) {
            dots[i].setCenter(firstXCenter + d.toFloat() * i, yCenter.toFloat())
            dots[i].setColor(dotColor)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(extraBitmap, 0f, 0f, null)
        for(dot in dots) {
            dot.draw(canvas)
        }
    }

    @SuppressLint("WrongConstant")
    private fun changeNewRadius(positionPerform: Int, newRadius: Float) {
        if(dots[positionPerform].radius != newRadius) {
            val animation: ValueAnimator = ValueAnimator.ofFloat(dots[positionPerform].radius, newRadius)
            animation.addUpdateListener {
                val newRadius = it.animatedValue
                dots[positionPerform].radius = newRadius as Float
                invalidate()
            }
            animation.repeatCount = Animation.INFINITE
            animation.repeatMode = Animation.REVERSE
            animation.duration = 1000
            animation.startDelay = (positionPerform * 500).toLong()
            animation.start()
        }
    }
}