package com.emirim.hyejin.mokgongso.fragment.outerrecycler

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emirim.hyejin.mokgongso.Diary
import com.emirim.hyejin.mokgongso.LoginActivity
import com.emirim.hyejin.mokgongso.MandalartActivity
import com.emirim.hyejin.mokgongso.R
import com.emirim.hyejin.mokgongso.api.APIRequestManager
import com.emirim.hyejin.mokgongso.fragment.innerrecyclerview.InnerAdapter
import com.emirim.hyejin.mokgongso.fragment.innerrecyclerview.InnterItem
import com.emirim.hyejin.mokgongso.model.*
import kotlinx.android.synthetic.main.list_item_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BasicAdapter: RecyclerView.Adapter<ViewHolder> {
    var mDataset: List<OutterItem>
    var context: Context
    lateinit var mHolder: ViewHolder

    companion object {
        var previousExpandedPosition = -1
        var mExpandedPosition = -1
    }

    constructor(context: Context, dataset: List<OutterItem>) {
        mDataset = dataset
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemRecyclerView.layoutManager = LinearLayoutManager(context)
        var innerAdapter = InnerAdapter(mDataset[position].innerItem, context)
        holder.itemRecyclerView.adapter = innerAdapter
        holder.date.text = mDataset[position].diaryText

        // Animation
        val isExpanded = position == mExpandedPosition

        var innerItem2: ArrayList<InnterItem> = ArrayList()
        for(i in 0..(Diary.getDiary.re.size - 1)) {
            if (holder.date.text.equals(Diary.getDiary.re[i].date)) {
                for (j in 0..(Diary.getDiary.re[i].diary.size - 1)) {
                    innerItem2.add(InnterItem(Diary.getDiary.re[i].diary[j].index, false, Diary.getDiary.re[i].diary[j].token))
                }

                var innerAdapter2 = InnerAdapter(innerItem2, context)
                holder.itemRecyclerView.adapter = innerAdapter2
                holder.date.text = mDataset[position].diaryText

                break
            }
        }

        if(isExpanded) {
            holder.itemRecyclerView.visibility = View.VISIBLE
            holder.diaryWriteBtn.visibility = View.VISIBLE
            holder.listcardView.setBackgroundResource(R.color.white)

            var innerAdapter = InnerAdapter(mDataset[position].innerItem, context)
            holder.itemRecyclerView.adapter = innerAdapter
            holder.date.text = mDataset[position].diaryText

            mHolder = holder
        } else {
            holder.itemRecyclerView.visibility = View.GONE
            holder.diaryWriteBtn.visibility = View.GONE
            holder.listcardView.setBackgroundResource(R.color.colorPrimary)
            MandalartActivity.rightButtonImageView.setImageResource(R.drawable.trash)
            MandalartActivity.rightButtonImageView.tag = R.drawable.trash

            for(i in 0..(holder.itemRecyclerView.childCount - 1)) {
                holder.itemRecyclerView.delCheckBox.visibility = View.GONE
            }
        }

        holder.itemView.isActivated = isExpanded

        if(isExpanded)
            previousExpandedPosition = position

        holder.itemView.setOnClickListener {
            mExpandedPosition = if(isExpanded) -1 else position

            if(previousExpandedPosition >= 0) {
                notifyItemChanged(previousExpandedPosition)
            }
            if(position >= 0) {
                notifyItemChanged(position)
            }
        }

        holder.diaryWriteBtn.setOnClickListener {
            // position 0부터 시작
            if(holder.inputDiary.visibility == View.VISIBLE) {
                var calendar = Calendar.getInstance()
                calendar.add(Calendar.DAY_OF_MONTH, (position * -1))
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

                Log.d("asdf", "position: ${position}, date: ${sdf.format(date)}")
                Diary.addDiary = AddDiary(LoginActivity.appData!!.getString("ID", ""), sdf.format(date) + " " + day, holder.inputDiary.text.toString())

                val call: Call<Message> = APIRequestManager.getInstance().requestServer().addDiary(Diary.addDiary)

                call.enqueue(object: Callback<Message> {
                    override fun onResponse(call: Call<Message>, response: Response<Message>) {
                        when(response.code()) {
                            200 -> {
                                Log.d("addDiary", "Success")
                                var innerList: ArrayList<InnterItem> = mDataset[position].innerItem as ArrayList<InnterItem>
                                innerList.add(InnterItem(holder.inputDiary.text.toString(), false, ""))

                                mDataset[position].innerItem = innerList

                                innerAdapter = InnerAdapter(mDataset[position].innerItem, context)
                                holder.itemRecyclerView.adapter = innerAdapter


                                holder.inputDiary.setText("")


                                holder.inputDiary.visibility = View.GONE
                                holder.diaryWriteBtn.text = "일기쓰기"

                                com.emirim.hyejin.mokgongso.Mandalart.mandalChk = MandalChk(LoginActivity.appData!!.getString("ID", ""))
                                var call5: Call<GetDiary> = APIRequestManager.getInstance().requestServer().getDiary(com.emirim.hyejin.mokgongso.Mandalart.mandalChk)

                                call5.enqueue(object: Callback<GetDiary> {
                                    override fun onResponse(call: Call<GetDiary>, response: Response<GetDiary>) {
                                        when(response.code()) {
                                            200 -> {
                                                com.emirim.hyejin.mokgongso.Diary.getDiary = response.body() as GetDiary
                                            }
                                        }
                                    }
                                    override fun onFailure(call: Call<GetDiary>, t: Throwable) {
                                        Log.e("ServerMandal", "에러: " + t.message)
                                        t.printStackTrace()
                                    }
                                })
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
                holder.inputDiary.visibility = View.VISIBLE
                holder.diaryWriteBtn.text = "확인"
            }
        }
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    inner class ThreadClass: Thread() {
        override fun run() {
        }
    }

    fun viewCheckBox() {
        for(i in 0..(mHolder.itemRecyclerView.childCount - 1)) {
            mHolder.itemRecyclerView.getChildAt(i).delCheckBox.visibility = View.VISIBLE
        }
    }

    fun goneCheckBox() {
        for(i in 0..(mHolder.itemRecyclerView.childCount - 1)) {
            mHolder.itemRecyclerView.getChildAt(i).delCheckBox.visibility = View.GONE
        }

        var checkedList:List<InnterItem> = ArrayList()
        checkedList = (mHolder.itemRecyclerView.adapter as InnerAdapter).getInnerList()

        var delTokens: ArrayList<String> = ArrayList()
        var innerItems: ArrayList<InnterItem> = ArrayList()

        // 삭제 List
        for(i in 0..(checkedList.size - 1)) {
            if (checkedList[i].delCheck) {
                delTokens.add(checkedList[i].token)
            } else {
                innerItems.add(InnterItem(checkedList[i].diaryText, false, checkedList[i].token))
            }
        }

        // 삭제
        Diary.delDiary = DelDiary(LoginActivity.appData!!.getString("ID", ""), delTokens)
        var callDel: Call<Message> = APIRequestManager.getInstance().requestServer().delDiary(Diary.delDiary)

        callDel.enqueue(object: Callback<Message> {
            override fun onResponse(call: Call<Message>, response: Response<Message>) {
                when(response.code()) {
                    200 -> {
                        Log.d("DelDiary", "200")

                        com.emirim.hyejin.mokgongso.Mandalart.mandalChk = MandalChk(LoginActivity.appData!!.getString("ID", ""))
                        var callGet: Call<GetDiary> = APIRequestManager.getInstance().requestServer().getDiary(com.emirim.hyejin.mokgongso.Mandalart.mandalChk)

                        callGet.enqueue(object: Callback<GetDiary> {
                            override fun onResponse(call: Call<GetDiary>, response: Response<GetDiary>) {
                                when(response.code()) {
                                    200 -> {
                                        com.emirim.hyejin.mokgongso.Diary.getDiary = response.body() as GetDiary
                                    }
                                }
                            }
                            override fun onFailure(call: Call<GetDiary>, t: Throwable) {
                                Log.e("ServerMandal", "에러: " + t.message)
                                t.printStackTrace()
                            }
                        })


                        mDataset[mExpandedPosition].innerItem = innerItems

                        var innerAdapter = InnerAdapter(mDataset[mExpandedPosition].innerItem, context)
                        mHolder.itemRecyclerView.adapter = innerAdapter
                        mHolder.date.text = mDataset[mExpandedPosition].diaryText

                        Log.d("checkedList", "${checkedList.toString()}")
                    }
                    404 -> {
                        Log.d("DelDiary", "실패")
                    }
                }
            }
            override fun onFailure(call: Call<Message>, t: Throwable) {
                Log.e("DelDiary", "에러: " + t.message)
                t.printStackTrace()
            }
        })
    }
}