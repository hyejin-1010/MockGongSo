package com.emirim.hyejin.mokgongso.smallMandalart.page

class Mandalart {
    companion object {
        var title: String? = null
        var achievement: Int = 0

        var subMandalartTitle = Array<String>(3, {""})
        var thirdContent = Array(3, { Array<String>(3, {""}) })

        // var thirdCout = IntArray(3)
        // var count: Int = 1
        var position: Int = 1

        var secondSelect = -1
        var thirdSelect = -1

        var viewer = 0
    }
}