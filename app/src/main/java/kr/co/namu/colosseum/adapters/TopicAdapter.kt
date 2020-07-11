package kr.co.namu.colosseum.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import kr.co.namu.colosseum.R
import kr.co.namu.colosseum.data.Topic

class TopicAdapter(val mContext:Context, val resId : Int, val mList:ArrayList<Topic>)
    : ArrayAdapter<Topic>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

//        tempRow가 비어있는지? null인지? => 돌려막기 (재사용성 활용) 할 재료가 없다.
        if (tempRow == null) {
//            써먹을 재료가 없으면 새로 xml을 그려줘야함
            tempRow = inf.inflate(R.layout.topic_list_item, null)

        }

//        tempRow는 더이상 null일 가능성이 없다. => row에게 전달.
        val row = tempRow!!


//        최종 완성된 row를 리스트에 뿌리자.
        return row
    }

}