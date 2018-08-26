package com.emirim.hyejin.mokgongso.fragment

import android.os.Bundle
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emirim.hyejin.mokgongso.R

class MandalartFragment : Fragment() {
    companion object {
        fun newInstance(): MandalartFragment {
            return MandalartFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_mandalart, container, false)
    }
}