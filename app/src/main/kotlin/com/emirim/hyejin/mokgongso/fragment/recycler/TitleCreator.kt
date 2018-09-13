package com.emirim.hyejin.mokgongso.fragment.recycler

import android.content.Context

class TitleCreator {
    lateinit var _titleParents: ArrayList<TitleParent>


    companion object {
        lateinit var _titleCreator: TitleCreator

        fun get(context: Context): TitleCreator {
            if(_titleCreator == null)
                _titleCreator = TitleCreator(context)

            return _titleCreator
        }
    }

    constructor(context: Context) {
        _titleParents = ArrayList()

        for(i in 1..100){
            var title: TitleParent = TitleParent(String.format("Caller #&d", i), String.format("Caller #&d", i))
            _titleParents.add(title)
        }
    }
}