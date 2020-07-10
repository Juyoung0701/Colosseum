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