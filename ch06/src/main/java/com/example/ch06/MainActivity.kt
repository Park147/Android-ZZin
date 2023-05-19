package com.example.ch06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.ch06.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() { // AppcompatActivity 부모 클래스 로부터 상속 받음.
    // 코드 작업 공간, 뷰 작업도 가능, but View-> Xml 에서만 작업.
    // 여긴 데이터 관련 작업 및 이벤트 핸들러 작업하는 곳! 가독성,유지보수 위하여
    override fun onCreate(savedInstanceState: Bundle?) { //onCreate 액티비티의 생명주기에서 자세히 later)ch13.
        super.onCreate(savedInstanceState) // savedInstanceState -> 저장소 개념 test17_2. 객체에 저장
        // res -> layout -> xml 파일 불러서 실제 화면을 출력!
        // 리소스 R.java 파일이라는 곳에 상수 값으로 저장되는데, 여기서 불러옴.
        // So 코드에서 resource 값을 불러올 때! 항상 R.layout 등으로 시작 해서 불러옴.
        // 결 : 화면 출력하겠다. 레이아웃(View 그룹 : 뷰(text, image, input, checkbox, radio ...)를 모아주는 역할
        //setContentView(R.layout.activity_main)

        // 뷰 바인딩 기술을 통해서, 뷰들을, 특정 바인딩이라는 객체에 모두 모아서,
        // 접근을 쉽게 해주는 기술!
        // 사용하기 전 항상! build.gradle 파일에 설정 훅, sync now 적용해서 사용해야함.

        // 이 문법은 자동으로 생성된 바인딩 파일을 inflate 함수와, 인자는 : layoutInflater 고정
        // setContentView - > 화면에 그리기
        // binding 변수에, 모두 뷰가 들어가 있음. !결 : 여기서 필요한 뷰를 꺼내서 사용!.

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var status = 0

        //button1 을 뷰바인딩 기법으로 접근 *짱 중요
        binding.btn1.setOnClickListener {
            if (status == 0) {
                binding.img1.visibility = View.INVISIBLE
                status = 1
            } else {
                binding.img1.visibility = View.VISIBLE
                status = 0
            }
        }
//
//        val button1 = findViewById<Button>(R.id.btn1)
//        val img1 = findViewById<ImageView>(R.id.img1)
//        var status = 0
//
//        //button1 눌러서 -> img1 show/hide + 중!괄호는 람다식
//        button1.setOnClickListener {
//            if (status == 0) {
//                img1.visibility = View.INVISIBLE
//                status = 1
//            } else {
//                img1.visibility = View.VISIBLE
//                status = 0
//            }
//        }
    }
}
