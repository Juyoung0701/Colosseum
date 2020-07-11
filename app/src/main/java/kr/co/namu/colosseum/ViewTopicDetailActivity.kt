package kr.co.namu.colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_topic_detail.*
import kr.co.namu.colosseum.data.Topic
import kr.co.namu.colosseum.utils.ServerUtil
import org.json.JSONObject

class ViewTopicDetailActivity : BaseActivity() {

//
    var mTopicId = 0

//    서버에서 받아온 토픽 상세 정보 변수
    lateinit var mTopicData : Topic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_topic_detail)
        setupEvents()
        setValues()

}

    override fun setupEvents() {


    }

    override fun setValues() {

//        메인에서 보내준 토픽 id값을 저장
        mTopicId = intent.getIntExtra("topicId", 0)

//        서버에서 해당 토픽의 진행상황 / 상세 정보 확인
        getTopicDetailFromServer()
    }

    fun getTopicDetailFromServer(){

        ServerUtil.getRequestTopicDetail(mContext, mTopicId, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

                val data = json.getJSONObject("data")

                val topicJson = data.getJSONObject("topic")

//                멤버 변수 mTopicData에 서버에서 내려준 애용을 저장.
                mTopicData = Topic.getTopicFromJson(topicJson)

                runOnUiThread {
                    titleTxt.text = mTopicData.title
                }
            }

        })
    }

}


