package com.emirim.hyejin.mokgongso.smallMandalart.page

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.smallMandalart.CreateMandalart
import kotlinx.android.synthetic.main.activity_small_page_2.view.*

class Page2 : Fragment() {
    companion object {
        lateinit var constraintLayout: View
        lateinit var mandalartSub: Array<EditText>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        constraintLayout = inflater.inflate(R.layout.activity_small_page_2, container, false)

        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        constraintLayout.leftArrow.setOnClickListener {
            CreateMandalart.mViewPager.currentItem = 0
        }

        mandalartSub = arrayOf<EditText>(constraintLayout.mandalartSub1,constraintLayout.mandalartSub2,constraintLayout.mandalartSub3)

        constraintLayout.rightArrow.setOnClickListener {
            for(i in 1..(Mandalart.count - 1)) {
                Mandalart.subMandalartTitle[i - 1] = mandalartSub[i-1].text.toString()
            }

            CreateMandalart.mViewPager.currentItem = 2
        }

        // EditText 추가
        constraintLayout.mandalartAddBtn.setOnClickListener {
            mandalartSub[Mandalart.count - 1].visibility = View.VISIBLE
            if(Mandalart.count == 3)
                constraintLayout.mandalartAddBtn.visibility = View.GONE

            constraintLayout.rightArrow.visibility = View.GONE

            Mandalart.count++
        }

        for(x in 1..3) {
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

            for(i in 1..(Mandalart.count - 1)) {
                mandalartSub[i-1].visibility = View.VISIBLE
                mandalartSub[i-1].setText(Mandalart.subMandalartTitle[i - 1])
            }

            if((Mandalart.count - 1) == 3) {
                constraintLayout.mandalartAddBtn.visibility = View.GONE
            } else {
                constraintLayout.mandalartAddBtn.visibility = View.VISIBLE
            }
        } else {
            // preload 될 때 (전 페이지에 있을 때)
        }
    }
}