package com.emirim.hyejin.mokgongso.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emirim.hyejin.mokgongso.*
import com.emirim.hyejin.mokgongso.api.APIRequestManager
import com.emirim.hyejin.mokgongso.fragment.innerrecyclerview.InnterItem
import com.emirim.hyejin.mokgongso.fragment.outerrecycler.BasicAdapter
import com.emirim.hyejin.mokgongso.fragment.outerrecycler.OutterItem
import com.emirim.hyejin.mokgongso.model.GetDiary
import com.emirim.hyejin.mokgongso.model.MandalChk
import kotlinx.android.synthetic.main.fragment_diary.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DiaryFragment : Fragment() {
    companion object {
        fun newInstance(): DiaryFragment {
            return DiaryFragment()
        }

        lateinit var containtLayout: View

        fun initData(): List<OutterItem> {
            var outterItems: ArrayList<OutterItem> = ArrayList()
            var titles: ArrayList<String> = ArrayList()

            var getDiary = Diary.getDiary

            Mandalart.mandalChk = MandalChk(LoginActivity.appData!!.getString("ID", ""))
            var call: Call<GetDiary> = APIRequestManager.getInstance().requestServer().getDiary(Mandalart.mandalChk)

            call.enqueue(object : Callback<GetDiary> {
                override fun onResponse(call: Call<GetDiary>, response: Response<GetDiary>) {
                    when (response.code()) {
                        200 -> {
                            com.emirim.hyejin.mokgongso.Diary.getDiary = response.body() as GetDiary

                            Log.d("init Data ", getDiary.toString())
                        }
                    }
                }

                override fun onFailure(call: Call<GetDiary>, t: Throwable) {
                    Log.e("ServerMandal", "에러: " + t.message)
                    t.printStackTrace()
                }
            })

            val startDay = LoginActivity.appData!!.getString("startday", "")
            val startDate = SimpleDateFormat("yyyy-MM-dd").parse(startDay)

            val today = Date()

            val startCalendar = Calendar.getInstance()
            val todayCalendar = Calendar.getInstance()

            startCalendar.time = startDate
            todayCalendar.time = today

            while (startCalendar.compareTo(todayCalendar) != 1) {
                Log.d("starttoday dada", "${startCalendar.time} ${todayCalendar.time}")

                val sdf = SimpleDateFormat("yyyy-MM-dd")
                var day: String
                var date = todayCalendar.time

                when (todayCalendar.get(Calendar.DAY_OF_WEEK)) {
                    1 -> day = "일요일"
                    2 -> day = "월요일"
                    3 -> day = "화요일"
                    4 -> day = "수요일"
                    5 -> day = "목요일"
                    6 -> day = "금요일"
                    7 -> day = "토요일"
                    else -> day = "else"
                }

                titles.add(sdf.format(date) + " " + day)

                //startCalendar.add(Calendar.DATE, 1)
                todayCalendar.add(Calendar.DATE, -1)
            }

            for (title in titles) {
                var innerItems: ArrayList<InnterItem> = ArrayList()

                for (i in 0..(getDiary.re.size - 1)) {
                    if (title.equals(getDiary.re[i].date)) {
                        for (j in 0..(getDiary.re[i].diary.size - 1)) {
                            innerItems.add(InnterItem(getDiary.re[i].diary[j].index, false, getDiary.re[i].diary[j].token))
                        }
                        break
                    }
                }

                outterItems.add(OutterItem(title, false, innerItems))
            }

            return outterItems
        }
    }

    var mDataset: ArrayList<OutterItem> = ArrayList()
    lateinit var mAdapter: BasicAdapter

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        containtLayout = inflater?.inflate(R.layout.fragment_diary, container, false)

        val layoutManager: RecyclerView.LayoutManager? = LinearLayoutManager(activity)
        containtLayout.diaryRecyclerView.layoutManager = layoutManager

        mDataset = ArrayList()
        mAdapter = BasicAdapter(activity as Context, initData())

        containtLayout.diaryRecyclerView.invalidate()

        containtLayout.diaryRecyclerView.adapter = mAdapter
        containtLayout.diaryRecyclerView.adapter?.notifyDataSetChanged()

        Log.d("diarytest", "test")

        return containtLayout
    }
}