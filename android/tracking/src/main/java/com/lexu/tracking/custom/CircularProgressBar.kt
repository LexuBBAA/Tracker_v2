package com.lexu.tracking.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat


class CircularProgressBar(context: Context, attributeSet: AttributeSet?): View(context, attributeSet) {
    /**
     * ProgressBar's line thickness
     */
    private var strokeWidth = 4f
    private var progress = 0f
    private var min = 0
    private var max = 100
    /**
     * Start the progress at 12 o'clock
     */
    private var startAngle = -90F
    private var color = Color.RED
    private var rectF: RectF? = null
    private var backgroundPaint: Paint? = null
    private var foregroundPaint: Paint? = null
    private var imageDrawable: Drawable

    private var imageRes: Int = -1

    constructor(context: Context): this(context, null)

    init {
        rectF = RectF()
        val typedArray = context.theme.obtainStyledAttributes(
            attributeSet,
            com.lexu.tracking.R.styleable.CircularProgressBar,
            0, 0
        )
        //Reading values from the XML layout
        try {
            strokeWidth = typedArray.getDimension(
                com.lexu.tracking.R.styleable.CircularProgressBar_progressBarThickness,
                strokeWidth
            )
            progress = typedArray.getFloat(com.lexu.tracking.R.styleable.CircularProgressBar_progress, progress)
            color = typedArray.getInt(com.lexu.tracking.R.styleable.CircularProgressBar_progressbarColor, color)
            min = typedArray.getInt(com.lexu.tracking.R.styleable.CircularProgressBar_min, min)
            max = typedArray.getInt(com.lexu.tracking.R.styleable.CircularProgressBar_max, max)

            imageRes = typedArray.getResourceId(com.lexu.tracking.R.styleable.CircularProgressBar_image, -1)
        } finally {
            typedArray.recycle()
        }

        backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        backgroundPaint!!.color = adjustAlpha(color, 0.3f)
        backgroundPaint!!.style = Paint.Style.STROKE
        backgroundPaint!!.strokeWidth = strokeWidth

        foregroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        foregroundPaint!!.color = color
        foregroundPaint!!.style = Paint.Style.STROKE
        foregroundPaint!!.strokeWidth = strokeWidth

        imageDrawable =
            ResourcesCompat.getDrawable(resources, imageRes, null) ?: ResourcesCompat.getDrawable(
                resources,
                com.lexu.tracking.R.drawable.ic_account_circle_black_24dp,
                null
            ) as Drawable
    }

    private fun adjustAlpha(color: Int, factor: Float): Int {
        val alpha = Math.round(Color.alpha(color) * factor)
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        return Color.argb(alpha, red, green, blue)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val min = Math.min(width, height)
        setMeasuredDimension(min, min)
        rectF!!.set(0 + strokeWidth / 2, 0 + strokeWidth / 2, min - strokeWidth / 2, min - strokeWidth / 2)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawOval(rectF!!, backgroundPaint!!)
        val angle = 360 * progress / max
        canvas?.drawArc(rectF!!, startAngle, angle, false, foregroundPaint!!)

        imageDrawable.setBounds(rectF!!.left.toInt(),  rectF!!.top.toInt(), rectF!!.right.toInt(), rectF!!.bottom.toInt())
        canvas?.let { imageDrawable.draw(it) }
    }

    fun setImage(resId: Int) {
        this.imageRes = resId
        invalidate()
    }

    fun setProgress(progress: Float = 0F) {
        this.progress = progress
        this.color = when(progress) {
            in 0F .. 25F -> Color.RED
            in 26F .. 50F -> Color.CYAN
            in 51F .. 80F -> Color.GREEN
            in 81F .. 99F -> Color.YELLOW
            else -> Color.RED
        }
        foregroundPaint!!.color = color

        invalidate()
    }
}