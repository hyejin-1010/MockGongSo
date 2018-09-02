package com.emirim.hyejin.mokgongso.mandalart

class Mandalart {
    companion object {
        var title: String? = null
        var subMandalartTitle = Array<String>(8, {""})
        var thirdContent = Array(8, { Array<String>(8, {""}) })

        var thirdCout = IntArray(8)
        var count: Int = 1
        var position: Int = 1
    }
}