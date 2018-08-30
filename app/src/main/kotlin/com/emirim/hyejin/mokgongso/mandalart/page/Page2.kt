package com.emirim.hyejin.mokgongso.mandalart.page

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.mandalart.CreateMandalart
import com.emirim.hyejin.mokgongso.mandalart.Mandalart
import kotlinx.android.synthetic.main.activity_page_1.*
import kotlinx.android.synthetic.main.activity_page_2.view.*

class Page2 : Fragment() {
    companion object {
        lateinit var constraintLayout: View
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        constraintLayout = inflater.inflate(R.layout.activity_page_2, container, false)

        constraintLayout.leftArrow.setOnClickListener {
            CreateMandalart.mViewPager.currentItem = 0
        }

        var mandalartSub = arrayOf<EditText>(constraintLayout.mandalartSub1,constraintLayout.mandalartSub2,constraintLayout.mandalartSub3, constraintLayout.mandalartSub4, constraintLayout.mandalartSub5, constraintLayout.mandalartSub6, constraintLayout.mandalartSub7, constraintLayout.mandalartSub8)

        constraintLayout.rightArrow.setOnClickListener {
            for(i in 1..(Mandalart.count - 1)) {
                Mandalart.subMandalartTitle.add(mandalartSub[i].text.toString())
            }

            CreateMandalart.mViewPager.currentItem = 2
        }

        // EditText 추가
        constraintLayout.mandalartAddBtn.setOnClickListener {
            mandalartSub[Mandalart.count - 1].visibility = View.VISIBLE
            if(Mandalart.count == 8)
                constraintLayout.mandalartAddBtn.visibility = View.GONE

            constraintLayout.rightArrow.visibility = View.GONE

            Mandalart.count ++
        }

        for(x in 1..8) {
            mandalartSub[x-1].addTextChangedListener(object: TextWatcher {
                override fun afterTextChanged(s: Editable?) { }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    var boolean = false

                    for(i in 1..(Mandalart.count - 1)) {
                            if(!(mandalartSub[i-1].text.trim().isNotEmpty())) {
                                boolean = true
                                break
                            }
                        }

                    if(boolean)
                        constraintLayout.rightArrow.visibility = View.GONE
                    else
                        constraintLayout.rightArrow.visibility = View.VISIBLE
                }
            })
        }

        return constraintLayout
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if(isVisibleToUser) {
            // 화면에 실제로 보일 때
            val mandalartTitle: TextView = constraintLayout.findViewById(R.id.mandalartTitle)

            mandalartTitle.text = Mandalart.title
        } else {
            // preload 될 때 (전 페이지에 있을 때)
        }
    }
}