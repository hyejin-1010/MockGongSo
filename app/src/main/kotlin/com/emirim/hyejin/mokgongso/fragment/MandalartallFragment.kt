package com.emirim.hyejin.mokgongso.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.mandalart.Mandalart
import kotlinx.android.synthetic.main.fragment_mandalartall.view.*

class MandalartallFragment : Fragment() {
    companion object {
        fun newInstance(): MandalartallFragment {
            return MandalartallFragment()
        }

        lateinit var secondSelector: Array<TextView>
        lateinit var constraintLayout: View
        lateinit var viewBox: Array<Array<TextView>>
        lateinit var centerBox: Array<TextView>
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("Mandalartall", "dfsfsd")

        constraintLayout = inflater?.inflate(R.layout.fragment_mandalartall, container, false)

        // MandalartViewFragment.secondSelector = arrayOf(MandalartViewFragment.constraintLayout.secondTitle1, MandalartViewFragment.constraintLayout.secondTitle2, MandalartViewFragment.constraintLayout.secondTitle3, MandalartViewFragment.constraintLayout.secondTitle4, MandalartViewFragment.constraintLayout.secondTitle5, MandalartViewFragment.constraintLayout.secondTitle6, MandalartViewFragment.constraintLayout.secondTitle7, MandalartViewFragment.constraintLayout.secondTitle8)

        viewBox = arrayOf(arrayOf(constraintLayout.boxfirst1, constraintLayout.boxfirst2, constraintLayout.boxfirst3,
                constraintLayout.boxfirst4, constraintLayout.boxfirstcenter, constraintLayout.boxfirst5,
                constraintLayout.boxfirst6, constraintLayout.boxfirst7, constraintLayout.boxfirst8),
                arrayOf(constraintLayout.boxsecond1, constraintLayout.boxsecond2, constraintLayout.boxsecond3,
                        constraintLayout.boxsecond4, constraintLayout.boxsecondcenter, constraintLayout.boxsecond5,
                        constraintLayout.boxsecond6, constraintLayout.boxsecond7, constraintLayout.boxsecond8),
                arrayOf(constraintLayout.boxthird1, constraintLayout.boxthird2, constraintLayout.boxthird3,
                        constraintLayout.boxthird4, constraintLayout.boxthirdcenter, constraintLayout.boxthird5,
                        constraintLayout.boxthird6, constraintLayout.boxthird7, constraintLayout.boxthird8),
                arrayOf(constraintLayout.boxfourth1, constraintLayout.boxfourth2, constraintLayout.boxfourth3,
                        constraintLayout.boxfourth4, constraintLayout.boxfourthcenter, constraintLayout.boxfourth5,
                        constraintLayout.boxfourth6, constraintLayout.boxfourth7, constraintLayout.boxfourth8),
                arrayOf(constraintLayout.boxfifth1, constraintLayout.boxfifth2, constraintLayout.boxfifth3,
                        constraintLayout.boxfifth4, constraintLayout.boxfifthcenter, constraintLayout.boxfifth5,
                        constraintLayout.boxfifth6, constraintLayout.boxfifth7, constraintLayout.boxfifth8),
                arrayOf(constraintLayout.boxsixth1, constraintLayout.boxsixth2, constraintLayout.boxsixth3,
                        constraintLayout.boxsixth4, constraintLayout.boxsixthcenter, constraintLayout.boxsixth5,
                        constraintLayout.boxsixth6, constraintLayout.boxsixth7, constraintLayout.boxsixth8),
                arrayOf(constraintLayout.boxseventh1, constraintLayout.boxseventh2, constraintLayout.boxseventh3,
                        constraintLayout.boxseventh4, constraintLayout.boxseventhcenter, constraintLayout.boxseventh5,
                        constraintLayout.boxseventh6, constraintLayout.boxseventh7, constraintLayout.boxseventh8),
                arrayOf(constraintLayout.boxeighth1, constraintLayout.boxeighth2, constraintLayout.boxeighth3,
                        constraintLayout.boxeighth4, constraintLayout.boxeighthcenter, constraintLayout.boxeighth5,
                        constraintLayout.boxeighth6, constraintLayout.boxeighth7, constraintLayout.boxeighth8))

        centerBox = arrayOf(constraintLayout.boxcenter1, constraintLayout.boxcenter2, constraintLayout.boxcenter3,
                constraintLayout.boxcenter4, constraintLayout.centerbox, constraintLayout.boxcenter5,
                constraintLayout.boxcenter6, constraintLayout.boxcenter7, constraintLayout.boxcenter8)

        for(i in 0..7) {
            for(j in 0..8) {
                if(j == 4) {
                    viewBox[i][j].text = Mandalart.subMandalartTitle[i]

                    if(Mandalart.subMandalartTitle[i] != null && !(Mandalart.subMandalartTitle[i].equals(""))) {
                        viewBox[i][j].background = resources.getDrawable(R.drawable.mandalart_box_4)
                    }
                } else {
                    if(j > 4) {
                        viewBox[i][j].text = Mandalart.thirdContent[i][j-1]
                    } else {
                        viewBox[i][j].text = Mandalart.thirdContent[i][j]
                    }
                }
            }
        }

        for(i in 0..8) {
            if(i == 4) {
                centerBox[i].text = Mandalart.title
            } else if(i > 4) {
                centerBox[i].text = Mandalart.subMandalartTitle[i - 1]

                if(Mandalart.subMandalartTitle[i - 1] != null && !(Mandalart.subMandalartTitle[i - 1].equals(""))) {
                    centerBox[i].background = resources.getDrawable(R.drawable.mandalart_box_4)
                }
            } else {
                centerBox[i].text = Mandalart.subMandalartTitle[i]

                if(Mandalart.subMandalartTitle[i] != null && !(Mandalart.subMandalartTitle[i].equals(""))) {
                    centerBox[i].background = resources.getDrawable(R.drawable.mandalart_box_4)
                }
            }
        }

        return constraintLayout
    }

}
/*
 이 값을 순서대로  넣어주세요
    "re": {
        "title": "큰 목표",
        "achievement": 0,
        "mandal": [
            {
                "smallMandalArt1": {
                    "title": "세부",
                    "achievement": 0
                },
                "smallMandalArt2": {
                    "title": "목표",
                    "achievement": 0
                },
                "smallMandalArt3": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt4": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt5": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt6": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt7": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt8": {
                    "title": "",
                    "achievement": 0
                },
                "middleTitle": "작은",
                "achievement": 0,
                "_id": "5b9e1101b0cc9d38dff13def",
                "order": 0
            },
            {
                "smallMandalArt1": {
                    "title": "1",
                    "achievement": 0
                },
                "smallMandalArt2": {
                    "title": "2",
                    "achievement": 0
                },
                "smallMandalArt3": {
                    "title": "3",
                    "achievement": 0
                },
                "smallMandalArt4": {
                    "title": "4",
                    "achievement": 0
                },
                "smallMandalArt5": {
                    "title": "5",
                    "achievement": 0
                },
                "smallMandalArt6": {
                    "title": "6",
                    "achievement": 0
                },
                "smallMandalArt7": {
                    "title": "7",
                    "achievement": 0
                },
                "smallMandalArt8": {
                    "title": "8",
                    "achievement": 0
                },
                "middleTitle": "목표",
                "achievement": 0,
                "_id": "5b9e1101b0cc9d38dff13df0",
                "order": 1
            },
            {
                "smallMandalArt1": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt2": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt3": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt4": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt5": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt6": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt7": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt8": {
                    "title": "",
                    "achievement": 0
                },
                "middleTitle": "",
                "achievement": 0,
                "_id": "5b9e1101b0cc9d38dff13df1",
                "order": 2
            },
            {
                "smallMandalArt1": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt2": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt3": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt4": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt5": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt6": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt7": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt8": {
                    "title": "",
                    "achievement": 0
                },
                "middleTitle": "",
                "achievement": 0,
                "_id": "5b9e1101b0cc9d38dff13df2",
                "order": 3
            },
            {
                "smallMandalArt1": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt2": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt3": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt4": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt5": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt6": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt7": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt8": {
                    "title": "",
                    "achievement": 0
                },
                "middleTitle": "",
                "achievement": 0,
                "_id": "5b9e1101b0cc9d38dff13df3",
                "order": 4
            },
            {
                "smallMandalArt1": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt2": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt3": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt4": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt5": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt6": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt7": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt8": {
                    "title": "",
                    "achievement": 0
                },
                "middleTitle": "",
                "achievement": 0,
                "_id": "5b9e1101b0cc9d38dff13df4",
                "order": 5
            },
            {
                "smallMandalArt1": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt2": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt3": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt4": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt5": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt6": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt7": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt8": {
                    "title": "",
                    "achievement": 0
                },
                "middleTitle": "",
                "achievement": 0,
                "_id": "5b9e1101b0cc9d38dff13df5",
                "order": 6
            },
            {
                "smallMandalArt1": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt2": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt3": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt4": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt5": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt6": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt7": {
                    "title": "",
                    "achievement": 0
                },
                "smallMandalArt8": {
                    "title": "",
                    "achievement": 0
                },
                "middleTitle": "",
                "achievement": 0,
                "_id": "5b9e1101b0cc9d38dff13df6",
                "order": 7
            }
        ]
    }
}
 */