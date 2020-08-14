package com.example.emotionalfacecustomeview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class EmotionalFaceView(context:Context,attrs:AttributeSet): View(context,attrs) {
    // Paint object for coloring and styling
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    // Some colors for the face background, eyes and mouth.
    private var faceColor = Color.YELLOW
    private var eyesColor = Color.BLACK
    private var mouthColor = Color.BLACK
    private var borderColor = Color.BLACK
    // Face border width in pixels
    private var borderWidth = 4.0f
    // View size in pixels
    private var size = 320

    private val mouthPath= Path()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawFaceBackground(canvas)
        drawEyes(canvas)
        drawMouth(canvas)
    }

    private fun drawFaceBackground(canvas: Canvas) {
        // 1
        paint.color = faceColor
        paint.style = Paint.Style.FILL

        // 2
        val radius = size / 2f

        // 3
        canvas.drawCircle(size / 2f, size / 2f, radius, paint)

        // 4
        paint.apply {
            color = borderColor
            style = Paint.Style.STROKE
            strokeWidth = borderWidth
        }


        // 5
        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint)
    }

    private fun drawEyes(canvas: Canvas) {
        // 1
        paint.color = eyesColor
        paint.style = Paint.Style.FILL

// 2
        val leftEyeRect = RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.50f)

        canvas.drawOval(leftEyeRect, paint)

// 3
        val rightEyeRect = RectF(size * 0.57f, size * 0.23f, size * 0.68f, size * 0.50f)

        canvas.drawOval(rightEyeRect, paint)
    }

    private fun drawMouth(canvas: Canvas) {
        // 1
        mouthPath.moveTo(size * 0.22f, size * 0.7f)
        // 2
        mouthPath.quadTo(size * 0.50f, size * 0.80f, size * 0.78f, size * 0.70f)
        // 3
        mouthPath.quadTo(size * 0.50f, size * 0.95f, size * 0.22f, size * 0.70f)
        // 4
        paint.color = mouthColor
        paint.style = Paint.Style.FILL
        // 5
        canvas.drawPath(mouthPath, paint)

//        Set the starting point of the path to (x0,y0) by using the moveTo() method where:
//        x0 is equal to 22% of the size.
//        y0 is equal to 70% of the size.
//        Draw a curved path from the starting point and through (x1,y1) that ends with (x2,y2) where:
//        x1 is equal to 50% of the size.
//        y1 is equal to 80% of the size.
//        x2 is equal to 78% of the size.
//        y2 is equal to 70% of the size.
//        Draw a curved path starting from the last end point (x2,y2) and through (x3,y3) and that ends with (x0,y0) where:
//        x3 is equal to 50% of the size.
//        y3 is equal to 90% of the size.
//        x0 is equal to 22% of the size.
//        y0 is equal to 70% of the size.
//        Set the paint color to the mouthColor and make it filling the drawing area.
//        Draw the path to the canvas.

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // 1
        size = Math.min(measuredWidth, measuredHeight)
        // 2
        setMeasuredDimension(size, size)
    }

}