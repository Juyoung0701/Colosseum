package kr.co.namu.colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.namu.colosseum.adapters.TopicAdapter
import kr.co.namu.colosseum.data.Topic
import kr.co.namu.colosseum.data.User
import kr.co.namu.colosseum.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val mTopicList = ArrayList<Topic>()

    lateinit var mTopicAdapter: TopicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

    }

    override fun setValues() {
//        getUserInfoFromServer()

        mTopicAdapter = TopicAdapter(mContext, R.layout.topic_list_item, mTopicList)
        topicListView.adapter = mTopicAdapter

        getTopicListFromServer()
    }

//    진짜로 서버에서 토론목록 받아오기

    fun getTopicListFromServer(){

        ServerUtil.getRequestMainInfo(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

                val data = json.getJSONObject("data")
                val topics = data.getJSONArray("topics")

                for(i in 0..topics.length()-1) {
                    val topicJson = topics.getJSONObject(i)

                    val topic = Topic.getTopicFromJson(topicJson)

//                    바꿔준 topic을 목록에 추가
                    mTopicList.add(topic)

                }
                runOnUiThread {

                    //                목록을 모두 추가했으면 리스트뷰 새로고침
                    mTopicAdapter.notifyDataSetChanged()
                }


            }

        })

    }

//    임시로 하는 작업. => 서버에서 내 정보를 받아와서 닉네임 뿌려주기

    fun getUserInfoFromServer() {
        ServerUtil.getRequestMyInfo(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

                runOnUiThread {

//                    사용자의 닉네임을 받아서 => 텍스트뷰에 반영

                    val data  = json.getJSONObject("data")
                    val user = data.getJSONObject("user")

                    val loginUser = User.getUserFromJson(user)


//                    userNickTxt.text = loginUser.nickName
//                    userEmailTxt.text = loginUser.email
                }
            }

        })

    }




}