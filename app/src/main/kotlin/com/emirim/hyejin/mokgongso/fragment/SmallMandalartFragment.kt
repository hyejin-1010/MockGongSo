package com.emirim.hyejin.mokgongso.fragment

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.support.v4.app.Fragment;
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.emirim.hyejin.mokgongso.LoginActivity
import com.emirim.hyejin.mokgongso.MandalartActivity
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.mandalart.CreateMandalart
import com.emirim.hyejin.mokgongso.mandalart.Mandalart
import kotlinx.android.synthetic.main.fragment_mandalart.view.*
import kotlinx.android.synthetic.main.fragment_smallmandalart.view.*

class SmallMandalartFragment : Fragment() {
    companion object {
        fun newInstance(): SmallMandalartFragment {
            return SmallMandalartFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val constraintLayout: View = inflater?.inflate(R.layout.fragment_smallmandalart, container, false)

        val drawingView = DrawingView(context)
        // constraintLayout.canvasLayout.addView(drawingView)

        /*constraintLayout.t1.setOnClickListener {
            Toast.makeText(activity, "상아", Toast.LENGTH_SHORT).show()
        }*/

        constraintLayout.t1.setOnTouchListener(View.OnTouchListener { v, event ->
            Toast.makeText(activity, "${event.x}, ${event.y} = ${v.width}" , Toast.LENGTH_SHORT).show()

            return@OnTouchListener true
        })

        return constraintLayout
    }

    class DrawingView: View {
        constructor(context: Context?): super(context)
        constructor(context: Context, attrs: AttributeSet): super(context, attrs)
        constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle)

        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)

            val paint = Paint()
            paint.color = Color.BLACK
            paint.isAntiAlias = true

            val width = getWidth()

            val point1 = Point(100, 100)
            val point2 = Point(100 - 50, 100 + 50)
            val point3 = Point(100 + 50, 100 + 50)

           var path = Path()

            path.reset()

            path.moveTo(100f, 100f)
            path.lineTo(50f, 150f)
            path.lineTo(150f, 150f)
            path.lineTo(100f, 100f)

            path.close()

            canvas?.drawPath(path, paint)
            //canvas?.drawText("Line", 0f, 200f, paint)
            //canvas?.drawRect (10f, 110f, (width - 10) .toFloat (), 140f, paint)
        }
    }
}