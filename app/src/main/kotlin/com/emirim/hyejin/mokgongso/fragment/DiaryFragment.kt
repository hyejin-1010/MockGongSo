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

        /*var adapter = MyAdapter(activity as Context, initData())
        adapter.setParentClickableViewAnimationDefaultDuration()
        adapter.setParentAndIconExpandOnClick(true)*/

        return containtLayout
    }
}

   /* private fun initData(): List<ParentObject> {
        var parentObject: ArrayList<ParentObject> = ArrayList()

        var titles: ArrayList<TitleParent> = ArrayList()
        var getDiary = com.emirim.hyejin.mokgongso.Diary.getDiary

        Mandalart.mandalChk = MandalChk(LoginActivity.appData!!.getString("ID", ""))
        var call: Call<GetDiary> = APIRequestManager.getInstance().requestServer().getDiary(Mandalart.mandalChk)

        call.enqueue(object: Callback<GetDiary> {
            override fun onResponse(call: Call<GetDiary>, response: Response<GetDiary>) {
                when(response.code()) {
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

        /*Mandalart.mandalChk = MandalChk()

        var call: Call<GetDiary> = APIRequestManager.getInstance().requestServer().getDiary(Mandalart.mandalChk)

        call.enqueue(object: Callback<GetDiary> {
            override fun onResponse(call: Call<GetDiary>, response: Response<GetDiary>) {
                when(response.code()) {
                    200 -> {
                        var titles: ArrayList<TitleParent> = ArrayList()

                        val getDiary: GetDiary = response.body() as GetDiary

                        val startDay = LoginActivity.appData!!.getString("startday", "")
                        val startDate = SimpleDateFormat("yyyy-MM-dd").parse(startDay)

                        val today = Date()

                        val startCalendar = Calendar.getInstance()
                        val todayCalendar = Calendar.getInstance()

                        startCalendar.time = startDate
                        todayCalendar.time = today

                        todayCalendar.add(Calendar.DATE, 1)

                        while(startCalendar.compareTo(todayCalendar) != 1) {
                            val sdf = SimpleDateFormat("yyyy-MM-dd")
                            var day: String
                            var date = startCalendar.time

                            when(startCalendar.get(Calendar.DAY_OF_WEEK)){
                                1 -> day = "일요일"
                                2 -> day = "월요일"
                                3 -> day = "화요일"
                                4 -> day = "수요일"
                                5 -> day = "목요일"
                                6 -> day = "금요일"
                                7 -> day = "토요일"
                                else -> day = "else"
                            }

                            Log.d("시발", "${getDiary.re.size - 1}")

                            for(i in 0..(getDiary.re.size - 1)) {
                                if((sdf.format(date) + " " + day).equals(getDiary.re[i].date)) {

                                    titles.add(TitleParent(getDiary.re[i].date, getDiary.re[i].diary[0].index))
                                } else if(i == getDiary.re.size - 1) {
                                    titles.add(TitleParent(getDiary.re[i].date, ""))
                                }
                            }

                            for(title in titles) {
                                var childList: ArrayList<TitleChild> = ArrayList()

                                if(title.subTitle != null && !(title.subTitle.equals(""))) {
                                    for(i in 0..(getDiary.re.size - 1)) {
                                        if(title.title.equals(getDiary.re[i].date)) {
                                            if(title.title.equals(getDiary.re[i].diary.size <= 1)) {
                                                break
                                            }
                                            else {
                                                var diaryText = ""
                                                for(j in 1..(getDiary.re[i].diary.size -1)) {
                                                    diaryText += " - " + getDiary.re[i].diary[j].index + "\n"
                                                }

                                                childList.add(TitleChild(diaryText))
                                            }
                                        }
                                    }
                                }

                                title.mChildrenList = childList

                                parentObject.add(title)
                            }

                            startCalendar.add(Calendar.DATE, 1)
                        }


                        Log.d("init Data ", getDiary.toString())
                    }
                    404 -> {
                        Log.d("init Data ", "404")
                    }
                    500 -> {
                        Log.d("init Data ", "500")
                    }
                }
            }
            override fun onFailure(call: Call<GetDiary>, t: Throwable) {
                Log.e("ServerMandal", "에러: " + t.message)
                t.printStackTrace()
            }
        })*/

       /* var innerItems: ArrayList<InnterItem> = ArrayList()
        innerItems.add(InnterItem("안녕", false))
        innerItems.add(InnterItem("반가워", false))
        innerItems.add(InnterItem("인생아", false))
        innerItems.add(InnterItem("꺄륵", false))

        var innerItems2: ArrayList<InnterItem> = ArrayList()
        innerItems2.add(InnterItem("인생", false))
        innerItems2.add(InnterItem("제발", false))
        innerItems2.add(InnterItem("되죠", false))
        innerItems2.add(InnterItem("꺄륵", false))

        var inner: ArrayList<ArrayList<InnterItem>> = ArrayList()
        inner.add(innerItems)
        inner.add(innerItems2)
        inner.add(innerItems)
        inner.add(innerItems2)

        var titles: ArrayList<TitleParent> = ArrayList()

        titles.add(TitleParent("2018년 9월 1일 토요일"))
        titles.add(TitleParent("2018년 9월 2일 일요일"))
        titles.add(TitleParent("2018년 9월 3일 월요일"))
        titles.add(TitleParent("2018년 9월 4일 화요일"))

        var varparentObject : ArrayList<ParentObject> = ArrayList()*/

        // var titleCreator = TitleCreator.get(activity as Context)
        /*var titles: ArrayList<TitleParent> = ArrayList()
        titles.add(TitleParent("2018년 9월 1일 토요일", "- 장염에 걸렸다. 하루종일 뭘 할 수가 없었다"))
        titles.add(TitleParent("2018년 9월 2일 일요일", ""))

        varparentObject : ArrayList<ParentObject> = ArrayList()

        for(title in titles) {
            var childList: ArrayList<TitleChild> = ArrayList()
            childList.add(TitleChild("- 생각보다 만다라트는 쉬운 것 같다. 첨엔 어려웠는데 쓸 수록 괜찮은 것 같다는 생각을 했다\n\n" +
                    "- 작은 목표를 통해서 오늘 해야할 일이 많았는데 어느정도 정리가 된 것 같다\n\n- 자고\n\n- 싶다"))

            title.mChildrenList = childList

            parentObject.add(title)
        }*/

        /*var i = 0
        for(title in titles) {
            var childList: ArrayList<TitleChild> = ArrayList()
            childList.add(TitleChild(inner[i]))

            title.mChildrenList = childList

            parentObject.add(title)

            i++
        }*/

        val startDay = LoginActivity.appData!!.getString("startday", "")
        val startDate = SimpleDateFormat("yyyy-MM-dd").parse(startDay)

        val today = Date()

        val startCalendar = Calendar.getInstance()
        val todayCalendar = Calendar.getInstance()

        startCalendar.time = startDate
        todayCalendar.time = today

        Toast.makeText(context, "${startCalendar.time} ${todayCalendar.time}", Toast.LENGTH_SHORT).show()

        startCalendar.add(Calendar.DATE, -5) //

        while(startCalendar.compareTo(todayCalendar) != 1) {
            Log.d("starttoday", "${startCalendar.time} ${todayCalendar.time}")

            val sdf = SimpleDateFormat("yyyy-MM-dd")
            var day: String
            var date = startCalendar.time

            when (startCalendar.get(Calendar.DAY_OF_WEEK)) {
                1 -> day = "일요일"
                2 -> day = "월요일"
                3 -> day = "화요일"
                4 -> day = "수요일"
                5 -> day = "목요일"
                6 -> day = "금요일"
                7 -> day = "토요일"
                else -> day = "else"
            }

            titles.add(TitleParent(sdf.format(date) + " " + day))

            startCalendar.add(Calendar.DATE, 1)
        }

        for(title in titles) {
            Log.d("starttoday", "${title.title}")

            var innerItems: ArrayList<InnterItem> = ArrayList()

            for (i in 0..(getDiary.re.size - 1)) {
                Log.d("starttoday", "인생아 ${getDiary.re[i].date} ${title.title}")
                if ((title.title).equals(getDiary.re[i].date)) {

                    for (j in 0..(getDiary.re[i].diary.size - 1)) {
                        innerItems.add(InnterItem(getDiary.re[i].diary[j].index, false))
                    }
                    break
                }
            }

            var childList: ArrayList<TitleChild> = ArrayList()
            childList.add(TitleChild(innerItems))

            title.mChildrenList = childList

            parentObject.add(title)
        }

        return parentObject
    } */
