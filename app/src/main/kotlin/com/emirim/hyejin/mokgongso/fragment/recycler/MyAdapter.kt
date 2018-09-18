/*
package com.emirim.hyejin.mokgongso.fragment.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emirim.hyejin.mokgongso.R
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class MyAdapter: ExpandableRecyclerViewAdapter<TitleParentViewHolder, TitleChildViewHolder> {

    constructor(groups: List<ExpandableGroup<*>>): super(groups)

    override fun onCreateGroupViewHolder(parent: ViewGroup?, viewType: Int): TitleParentViewHolder {
        var view: View = LayoutInflater.from(parent?.context).inflate(R.layout.list_header, parent, false)

        return TitleParentViewHolder(view)
    }

    override fun onCreateChildViewHolder(parent: ViewGroup?, viewType: Int): TitleChildViewHolder {
        var view: View = LayoutInflater.from(parent?.context)
                .inflate(R.layout.list_item, parent, false)

        return TitleChildViewHolder(view)
    }

    override fun onBindChildViewHolder(holder: TitleChildViewHolder?, flatPosition: Int, group: ExpandableGroup<*>?, childIndex: Int) {
        val child: TitleChild = (group as Genre).items.get(childIndex)

    }

    override fun onBindGroupViewHolder(holder: TitleParentViewHolder?, flatPosition: Int, group: ExpandableGroup<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    */
/*   lateinit var inflater: LayoutInflater
       var context: Context*//*


   */
/* constructor(context: Context, parentItemList: List<ParentObject>) : super(context, parentItemList) {
        inflater = LayoutInflater.from(context)
        this.context = context
    }

    override fun onBindParentViewHolder(p0: TitleParentViewHolder?, p1: Int, p2: Any?) {
        var title: TitleParent = p2 as TitleParent
        p0?.textView1?.text = title.title

        p0?.isExpanded = false

        // notifyDataSetChanged()
    }

    override fun onCreateChildViewHolder(p0: ViewGroup?): TitleChildViewHolder {
        var view: View = inflater.inflate(R.layout.list_item, p0, false)

        return TitleChildViewHolder(view)
    }

    override fun onCreateParentViewHolder(p0: ViewGroup?): TitleParentViewHolder {
        var view: View = inflater.inflate(R.layout.list_header, p0, false)

        return TitleParentViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)

        Log.d("bindview", "position: ${position}")
    }

    override fun onBindChildViewHolder(p0: TitleChildViewHolder?, p1: Int, p2: Any?) {
        *//*
*/
/*this@MyAdapter.run {
            notifyDataSetChanged()
        }*//*
*/
/*

        // DiaryFragment.containtLayout.cardView.setBackgroundColor(Color.WHITE)


        var title: TitleChild = p2 as TitleChild

        p0?.itemRecyclerView?.layoutManager = LinearLayoutManager(context)
        var innerAdapter: InnerAdapter = InnerAdapter(title.innerList, context)
        p0?.itemRecyclerView?.adapter = innerAdapter

        // 클릭할 때마다 호출
        p0?.diaryWriteBtn?.setOnClickListener {
            if(p0?.inputDiary?.visibility == View.VISIBLE) {
                //Toast.makeText(context, "${p1}", Toast.LENGTH_SHORT).show()

                // DB에 저장
                var calendar = Calendar.getInstance()
                calendar.add(Calendar.DAY_OF_MONTH, (p1 - 1) * -1)
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

                Diary.addDiary = AddDiary(LoginActivity.appData!!.getString("ID", ""), sdf.format(date) + " " + day, p0?.inputDiary?.text.toString())

                val call: Call<Message> = APIRequestManager.getInstance().requestServer().addDiary(Diary.addDiary)

                call.enqueue(object: Callback<Message> {
                    override fun onResponse(call: Call<Message>, response: Response<Message>) {
                        when(response.code()) {
                            200 -> {
                                Log.d("addDiary", "Success")

                                var innerList: ArrayList<InnterItem> = title.innerList as ArrayList<InnterItem>
                                innerList.add(InnterItem(p0?.inputDiary?.text.toString(), false))

                                title.innerList = innerList

                                p0?.itemRecyclerView?.layoutManager = LinearLayoutManager(context)
                                var innerAdapter: InnerAdapter = InnerAdapter(title.innerList, context)
                                p0?.itemRecyclerView?.adapter = innerAdapter

                                p0?.inputDiary?.setText("")


                                p0?.inputDiary?.visibility = View.GONE
                                p0?.diaryWriteBtn?.text = "일기쓰기"

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
                p0?.inputDiary?.visibility = View.VISIBLE
                p0?.diaryWriteBtn?.text = "확인"
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
    }

    override fun onParentItemClickListener(position: Int) {
        super.onParentItemClickListener(position)
    }*//*

}
*/
