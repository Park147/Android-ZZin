package com.example.test18_publicdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test18_publicdata.databinding.ActivityMain3Binding

class MainActivity : AppCompatActivity() {
    // 공공데이터 API
    // 부산맛집정보 서비스
    // 가입 후, 로그인, 해당 요청 정보에서, API 키 발급 신청.
    // 하루 만 사용가능한 키 바로 발급됨.
    // API 문서 다운로드 받기.
    // 참고할 샘플 코드 : test18 : reqres.in
    // build.gradle 파일,
    // 1) 뷰 바인딩 2) 레트로핏 3) gson 4)gson-converter 5)Glide
    // test18 있는 MainActivity3을 복사
    // 변경할 사항
    // 1) 모델 클래스(리스트 포함)
    // 2) baseUrl 주소 및 서버에 전달하는 파라미터 확인.
    // 3) 인터페이스에 정의된 함수의 매개변수도 확인.
    // 4) 뷰 의 틀은 그대로 재사용 - > 받아온 정보 중, 보여줄 정보를 선택해서 바인딩 작업.
    // 예) 맛집의 제목, 맛집의 썸네일 이미지 URL 주소 만 가져온다.
    lateinit var binding: ActivityMain3Binding
    // 리사이클러뷰 작업.
    // 참고 코드, 원본 소스 코드, test18 -> retrofit2 -> MyAdapter.kt, Retrofit 참고.
    // 뷰도 같이 복사했음. 1) item_retrofit.xml 2) activity_retrofit.xml
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val networkService = (applicationContext as MyApplication).networkService

        val userListCall = networkService.doGetUserList("1")
        Log.d("kkang", "url:" + userListCall.request().url().toString())

        userListCall.enqueue(object : Callback<UserListModel> {
            override fun onResponse(call: Call<UserListModel>, response: Response<UserListModel>) {

                val userList = response.body()
                Log.d("lsy","Test18 userList data 값 : ${userList?.data}")
                //.......................................

                binding.recyclerView.adapter= MyAdapter(this@MainActivity3, userList?.data)
                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(this@MainActivity3, LinearLayoutManager.VERTICAL)
                )

                binding.pageView.text=userList?.page
                binding.totalView.text=userList?.total
            }

            override fun onFailure(call: Call<UserListModel>, t: Throwable) {
                call.cancel()
            }
        })