package com.example.ch11

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ch11.databinding.ActivityMainBinding
import com.example.ch11.databinding.FragmentOneBinding

class TwoFragment : Fragment() {
    //참고 코드 : TwoFragment
    // 뷰 바인딩 기법으로 해당 뷰 객체를 선택하기 위한 도구.
    // 함수 밖에서 선언만 되었고 실제로 onCreateView 함수로 화면을 그릴 때,
    // binding 사용할 때, 초기화(할당) 됨.
    // 이런 방식은 많이 사용함.<자바,자바스크립트 등 자주 쓰는거 전역함수로 쓰는거처럼>
    lateinit var binding: FragmentOneBinding

    // 실제로 프래그먼트가 화면에 그려지는 함수.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 자동 생성. FragmentOneBinding
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}
