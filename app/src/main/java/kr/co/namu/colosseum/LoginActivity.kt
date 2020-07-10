package kr.co.namu.colosseum

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kr.co.namu.colosseum.utils.ServerUtil
import org.json.JSONObject

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        signUpBtn.setOnClickListener {
            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)

        }
        loginBtn.setOnClickListener {
//            입력한 아이디 / 비번 받아오기
            val inputEmail = idEdt.text.toString()
            val inputPw = pwEdt.text.toString()

//            서버에 로그인 요청 시도
            ServerUtil.postRequestLogin(
                mContext,
                inputEmail,
                inputPw,
                object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(json: JSONObject) {

//                    json - 제일 큰 껍데기

//                    code = json.get Int값  받아오기

                        val code = json.getInt("code")

                        runOnUiThread {
                            if (code == 200) {
                                Toast.makeText(mContext, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()

//                                서버에서 내가 누군지 구별하는데 쓰는 토큰값을 받아다 저장.
//                                저장 : 기기에 아예 저장해서, 모든 화면에서 꺼내 쓰도록 + 앱/폰을 꺼도 유지되도록 저장.
//                                => 자동로그인 기능에 활용된다.

//                                중간 줄괄호 {} data 추출
                                val data = json.getJSONObject("data")

//                                그 안에 있는 토큰 값을 추출

                                val loginUserToken = data.getString("token")

//                                기기 내부에 저장.



                            } else {
                                Toast.makeText(mContext, "로그인 실패.", Toast.LENGTH_SHORT).show()
                            }


                        }

                    }

                })

        }

    }

    override fun setValues() {

    }


}