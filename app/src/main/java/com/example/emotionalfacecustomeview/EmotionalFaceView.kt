package com.example.emotionalfacecustomeview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class EmotionalFaceView(context:Context,attrs:AttributeSet): View(context,attrs) {
    // Paint object for coloring and styling
    private val paint = Paint()
    private val mouthPath = Path()
    private var size = 0
    // Some colors for the face background, eyes and mouth.
    // 2
    private var faceColor = DEFAULT_FACE_COLOR
    private var eyesColor = DEFAULT_EYES_COLOR
    private var mouthColor = DEFAULT_MOUTH_COLOR
    private var borderColor = DEFAULT_BORDER_COLOR
    private var borderWidth = DEFAULT_BORDER_WIDTH

    // 1
    companion object {
        private const val DEFAULT_FACE_COLOR = Color.YELLOW
        private const val DEFAULT_EYES_COLOR = Color.BLACK
        private const val DEFAULT_MOUTH_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_WIDTH = 4.0f

        const val HAPPY = 0L
        const val SAD = 1L
    }

    // 3
    var happinessState = HAPPY
        set(state) {
            field = state
            // 4
            invalidate()
        }

    // 5
    init {
        paint.isAntiAlias = true
        setupAttributes(attrs)
    }
    private fun setupAttributes(attrs: AttributeSet?) {
        // 6
        // Obtain a typed array of attributes
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.EmotionalFaceView,
            0, 0)

        // 7
        // Extract custom attributes into member variables
        happinessState = typedArray.getInt(R.styleable.EmotionalFaceView_state, HAPPY.toInt()).toLong()
        faceColor = typedArray.getColor(R.styleable.EmotionalFaceView_faceColor, DEFAULT_FACE_COLOR)
        eyesColor = typedArray.getColor(R.styleable.EmotionalFaceView_eyesColor, DEFAULT_EYES_COLOR)
        mouthColor = typedArray.getColor(R.styleable.EmotionalFaceView_mouthColor, DEFAULT_MOUTH_COLOR)
        borderColor = typedArray.getColor(R.styleable.EmotionalFaceView_borderColor,
            DEFAULT_BORDER_COLOR)
        borderWidth = typedArray.getDimension(R.styleable.EmotionalFaceView_borderWidth,
            DEFAULT_BORDER_WIDTH)

        // 8
        // TypedArray objects are shared and must be recycled.
        typedArray.recycle()


//        1.Add two constants, one for the HAPPY state and one for the SAD state.
//        2.Setup default values of the XML attribute properties, in case a user of the custom view does not set one of them
//        3.Add a new property called happinessState for the face happiness state.
//        4.Call the invalidate() method in the set happinessState method. The invalidate() method makes Android redraw the view by calling onDraw().
//        5.Call a new private setupAttributes() method from the init block.
//        6.Obtain a typed array of the XML attributes
//        7.Extract custom attributes into member variables
//        8.Recycle the typedArray to make the data associated with it ready for garbage collection.
    }

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
        mouthPath.reset()

        mouthPath.moveTo(size * 0.22f, size * 0.7f)

        if (happinessState == HAPPY) {
            // 1
            mouthPath.quadTo(size * 0.5f, size * 0.80f, size * 0.78f, size * 0.7f)
            mouthPath.quadTo(size * 0.5f, size * 0.90f, size * 0.22f, size * 0.7f)
        } else {
            // 2
            mouthPath.quadTo(size * 0.5f, size * 0.50f, size * 0.78f, size * 0.7f)
            mouthPath.quadTo(size * 0.5f, size * 0.60f, size * 0.22f, size * 0.7f)
        }

        paint.color = mouthColor
        paint.style = Paint.Style.FILL

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