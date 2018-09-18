package com.emirim.hyejin.mokgongso.fragment.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.emirim.hyejin.mokgongso.R
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder

class TitleChildViewHolder: ChildViewHolder {
    var itemRecyclerView: RecyclerView
    var diaryWriteBtn: Button
    var inputDiary: EditText

    constructor(view: View): super(view) {
        itemRecyclerView = view.findViewById(R.id.itemRecyclerView) as RecyclerView
        diaryWriteBtn = view.findViewById(R.id.diaryWriteBtn) as Button
        inputDiary = view.findViewById(R.id.inputDiary) as EditText

       /* diaryWriteBtn.setOnClickListener {
            if(inputDiary.visibility == View.VISIBLE) {
                inputDiary.visibility = View.GONE
                diaryWriteBtn.text = "일기쓰기"

                // DB에 저장
                var calendar = Calendar.getInstance()
                calendar.add(Calendar.DAY_OF_MONTH, (position) * -1)
                var date = calendar.time
                val sdf = SimpleDateFormat("yyyy-MM-dd")
                var day: String
                when(calendar.get(Calendar.DAY_OF_WEEK)){
                    1 -> day = "일요일"
                    2 -> day = "월요일"
                    3 -> day = "화요일"
                    4 -> day = "수요일"
                    5 -> day = "목요일"
                    6 -> day = "금요일"
                    7 -> day = "토요일"
                    else -> day = "else"
                }

                Diary.addDiary = AddDiary(LoginActivity.appData!!.getString("ID", ""), sdf.format(date) + " " + day, inputDiary?.text.toString())

                val call: Call<Message> = APIRequestManager.getInstance().requestServer().addDiary(Diary.addDiary)

                call.enqueue(object: Callback<Message> {
                    override fun onResponse(call: Call<Message>, response: Response<Message>) {
                        when(response.code()) {
                            200 -> {
                                Log.d("addDiary", "Success")
                            }
                            500 -> {
                                Log.d("addDiary", "Success")
                            }
                        }
                    }
                    override fun onFailure(call: Call<Message>, t: Throwable) {
                        Log.e("ThirdMandalart", "에러: " + t.message)
                        t.printStackTrace()
                    }
                })
            } else {
                inputDiary.visibility = View.VISIBLE
                diaryWriteBtn.text = "확인"
            }
        }*/


        /*childListview = view.findViewById(R.id.childListview) as TextView
        cardLayout = view.findViewById(R.id.listcardView) as LinearLayout
        diaryWriteBtn = view.findViewById(R.id.diaryWriteBtn) as Button
        diaryEdt = view.findViewById(R.id.diaryEdt) as EditText

        diaryWriteBtn.setOnClickListener {
            if(diaryEdt.visibility == View.GONE) {
                Log.d("버튼 ", "\"${position}\"")
                diaryEdt.visibility = View.VISIBLE
                diaryWriteBtn.text = "확인"
            } else {
                // 일기 서버에 보내기
                Log.d("onParentItem", "${position}")

                var calendar = Calendar.getInstance()
                calendar.add(Calendar.DAY_OF_MONTH, (position - 1) * -1)
                var date = calendar.time
                val sdf = SimpleDateFormat("yyyy-MM-dd")
                var day: String
                when(calendar.get(Calendar.DAY_OF_WEEK)){
                    1 -> day = "일요일"
                    2 -> day = "월요일"
                    3 -> day = "화요일"
                    4 -> day = "수요일"
                    5 -> day = "목요일"
                    6 -> day = "금요일"
                    7 -> day = "토요일"
                    else -> day = "else"
                }

                Diary.addDiary = AddDiary(LoginActivity.appData!!.getString("ID", ""), sdf.format(date) + " " + day, diaryEdt.text.toString())

                val call: Call<Message> = APIRequestManager.getInstance().requestServer().addDiary(Diary.addDiary)

                call.enqueue(object: Callback<Message> {
                    override fun onResponse(call: Call<Message>, response: Response<Message>) {
                        when(response.code()) {
                            200 -> {
                                Log.d("addDiary", "Success")

                                diaryEdt.visibility = View.GONE
                                diaryWriteBtn.text = "일기쓰기"
                            }
                            500 -> {
                                Log.d("addDiary", "Success")
                            }
                        }
                    }
                    override fun onFailure(call: Call<Message>, t: Throwable) {
                        Log.e("ThirdMandalart", "에러: " + t.message)
                        t.printStackTrace()
                    }
                })
            }
        }*/
    }

    fun onBind(titleChild: TitleChild) {

    }
}