package com.emirim.hyejin.mokgongso.mandalart

class Mandalart {
    companion object {
        var title: String? = null
        var achievement: Int = 0

        var subMandalartTitle = Array<String>(8, {""})
        var thirdContent = Array(8, { Array<String>(8, {""}) })
        var thirdAchievement = Array(8, { IntArray(8) })

        var thirdCout = IntArray(8)
        var count: Int = 1
        var position: Int = 1

        var secondSelect = -1
        var thirdSelect = -1

        var viewer = 0
        var tmpAchievement = 0
    }
}