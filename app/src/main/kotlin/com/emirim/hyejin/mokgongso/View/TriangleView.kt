package com.emirim.hyejin.mokgongso.View

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

import com.emirim.hyejin.mokgongso.R

class TriangleView : View {

    lateinit var mPaint: Paint
    lateinit var strokePaint: Paint

    lateinit var mPath: Path
    //internal var direction = Direction.SOUTH
    internal var direction = Direction.UP
    private var mColor: ColorStateList? = null

    //
    var boolean = 0

    enum class Direction {
        UP, DOWN, LEFT, RIGHT
    }

    constructor(context: Context) : super(context) {
        create()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        hanldeAttributes(context, attrs)
        create()
    }

    private fun hanldeAttributes(context: Context, attrs: AttributeSet) {
        val a = context.obtainStyledAttributes(attrs,
                R.styleable.TriangleView)
        val mDirection = a.getInt(R.styleable.TriangleView_direction, 0)
        when (mDirection) {
            0 -> direction = Direction.UP
            1 -> direction = Direction.DOWN
            2 -> direction = Direction.RIGHT
            3 -> direction = Direction.LEFT
        }
        mColor = a.getColorStateList(R.styleable.TriangleView_color)

    }

    fun setColor(color: Int) {
        mPaint.color = color

        if(color == 2131034285) {
            boolean = 1
        } else if(color == 2131034160) {
            boolean = 2
        } else if(color == 2131034162) {
            boolean = 3
        }
        Log.d("TriangleView change2 ", color.toString())

        invalidate()
    }

    fun setDirection(direction: Direction) {
        this.direction = direction
        invalidate()
    }

    private fun create() {
        mPaint = Paint()
        mPaint.flags = Paint.ANTI_ALIAS_FLAG
        mPaint.style = Paint.Style.FILL
        mPaint.color = mColor!!.defaultColor

        strokePaint = Paint()
        strokePaint.flags = Paint.ANTI_ALIAS_FLAG
        strokePaint.style = Paint.Style.STROKE
        strokePaint.color = resources.getColor(R.color.colorPrimaryDark)
        strokePaint.strokeWidth = 5f

        /*if(mColor!!.defaultColor == resources.getColor(R.color.colorPrimaryDark)) {
            mPaint.strokeWidth = 5f
            mPaint.style = Paint.Style.STROKE
        } else */if(mColor!!.defaultColor == resources.getColor(R.color.white)) {
            boolean = 1
        }
    }

    override fun onDraw(canvas: Canvas?) {
        if(boolean == 1) {
            mPath = calculate(direction)
            mPaint.color = resources.getColor(R.color.white)
            canvas!!.drawPath(mPath, mPaint)

            boolean = 0
            canvas!!.drawPath(mPath, strokePaint)
        } else if(boolean == 2) {
            mPath = calculate(direction)
            mPaint.color = resources.getColor(R.color.colorPrimaryDark)
            canvas!!.drawPath(mPath, mPaint)

            boolean = 0
            canvas!!.drawPath(mPath, strokePaint)
        } else if(mColor!!.defaultColor == resources.getColor(R.color.colorPrimaryDark)) {
            mPath = calculate(direction)
            mPaint.color = resources.getColor(R.color.colorPrimaryDark)
            canvas!!.drawPath(mPath, strokePaint)
            //canvas!!.drawPath(mPath, mPaint)
        } else if(boolean == 3) {
            mPath = calculate(direction)
            mPaint.color = resources.getColor(R.color.colorPrimaryDark)
            // canvas!!.drawPath(mPath, mPaint)
            canvas!!.drawPath(mPath, strokePaint)
        } else {
            mPath = calculate(direction)
            canvas!!.drawPath(mPath, mPaint)
        }
    }

    private fun calculate(direction: Direction): Path {
        val p1 = Point()
        val p2 = Point()
        val p3 = Point()

        val width = getWidth()
        val height = getHeight()

        if (direction == Direction.UP) {
            p1.x = width / 2
            p2.y = 0
            p2.x = 0
            p2.y = height
            p3.x = width
            p3.y = height
        } else if (direction == Direction.DOWN) {
            p1.x = 0
            p2.y = 0
            p2.x = width
            p2.y = 0
            p3.x = width / 2
            p3.y = height
        } else if (direction == Direction.RIGHT) {
            p1.x = 0
            p2.y = 0
            p2.x = 0
            p2.y = height
            p3.x = width
            p3.y = height / 2
        } else if (direction == Direction.LEFT) {
            p1.x = 0
            p1.y = height / 2
            p2.x = width
            p2.y = 0
            p3.x = width
            p3.y = height
        }

        val path = Path()
        path.moveTo(p1.x.toFloat(), p1.y.toFloat())
        path.lineTo(p2.x.toFloat(), p2.y.toFloat())
        path.lineTo(p3.x.toFloat(), p3.y.toFloat())
        path.close()

        return path
    }
}