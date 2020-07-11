package kr.co.namu.colosseum.data

import org.json.JSONObject

class User {
//     사용자의 하위 정보들
    var id = 0
    var email = ""
    var nickName = ""

    companion object{

//        JsonObject를 적당한걸 넣으면 => User 객체로 바꿔주는 기능.

        fun getUserFromJson(json:JSONObject) : User {

            val user = User()

//             json의 내용을 분석해서 => user 의 데이터로 대입
            user.id = json.getInt("id")
            user.email = json.getString("email")
            user.nickName = json.getString("nick_name")

            return user
        }
    }
}