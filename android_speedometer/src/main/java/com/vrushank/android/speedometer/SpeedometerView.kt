package com.vrushank.android.speedometer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import android.widget.TextView

class SpeedometerView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var speed = 0f
    private val maxSpeed = 100f
    private val handler = Handler(Looper.getMainLooper())
    private var isUpdating = false

    private lateinit var speedTextView: TextView

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 2f
        paint.color = Color.WHITE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()
        val radius = Math.min(width, height) / 2 * 0.8f

        val oval = RectF(
            width / 2 - radius, height / 2 - radius,
            width / 2 + radius, height / 2 + radius
        )
        paint.style = Paint.Style.STROKE
        paint.color = Color.WHITE
        canvas.drawArc(oval, 180f, 180f, false, paint)

        drawScale(canvas, radius, width, height)

        drawNeedle(canvas, radius, width, height)

        speedTextView.text = speed.toInt().toString()
    }

    private fun drawScale(canvas: Canvas, radius: Float, width: Float, height: Float) {
        paint.textSize = 40f
        for (i in 0..10) {
            val angle = 180f + i * 18f
            val x1 = (width / 2 + radius * Math.cos(Math.toRadians(angle.toDouble()))).toFloat()
            val y1 = (height / 2 + radius * Math.sin(Math.toRadians(angle.toDouble()))).toFloat()

            val x2 = (width / 2 + (radius - 30) * Math.cos(Math.toRadians(angle.toDouble()))).toFloat()
            val y2 = (height / 2 + (radius - 30) * Math.sin(Math.toRadians(angle.toDouble()))).toFloat()

            canvas.drawLine(x1, y1, x2, y2, paint)

            val label = (i * 10).toString()
            val labelX = (width / 2 + (radius - 60) * Math.cos(Math.toRadians(angle.toDouble()))).toFloat()
            val labelY = (height / 2 + (radius - 60) * Math.sin(Math.toRadians(angle.toDouble()))).toFloat()
            canvas.drawText(label, labelX - 20, labelY + 15, paint)
        }
    }

    private fun drawNeedle(canvas: Canvas, radius: Float, width: Float, height: Float) {
        val angle = 180f + speed / maxSpeed * 180f
        val x = (width / 2 + (radius - 50) * Math.cos(Math.toRadians(angle.toDouble()))).toFloat()
        val y = (height / 2 + (radius - 50) * Math.sin(Math.toRadians(angle.toDouble()))).toFloat()

        paint.color = Color.RED
        canvas.drawLine(width / 2, height / 2, x, y, paint)

        paint.style = Paint.Style.FILL
        paint.color = Color.WHITE
        canvas.drawCircle(width / 2, height / 2, 20f, paint)
    }

    fun setSpeedTextView(textView: TextView) {
        speedTextView = textView
    }

    fun startUpdatingSpeed() {
        isUpdating = true
        updateSpeed()
    }

    fun stopUpdatingSpeed() {
        isUpdating = false
        handler.removeCallbacksAndMessages(null)
    }

    private fun updateSpeed() {
        if (!isUpdating) return

        speed = (50..80).random().toFloat()
        invalidate()

        handler.postDelayed({ updateSpeed() }, 200) // Update every second
    }

}