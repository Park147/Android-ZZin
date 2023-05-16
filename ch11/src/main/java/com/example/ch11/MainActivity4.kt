package com.example.ch11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ch11.databinding.ActivityMain4Binding
import com.example.ch11.databinding.Item342Binding

// 참고 코드 : MainActivity342
// 리사이클러뷰가 재료로 다른 액티비티에서 재활용 될 예정.
class MainActivity4 : AppCompatActivity() {

    // 뷰홀더 만들기. (item.342.xml 의 자동생성된 바인딩 파일 사용.)
// 여기에 항목은 목록의 구성 요소들의 뷰 객체를 모아 두었다.
// 샘플로 텍스트 뷰 한개만 구성됨. Item342Binding
    class MyViewHolder(val binding: Item342Binding) : RecyclerView.ViewHolder(binding.root)

    //어댑터 만들기.
// - 데이터 연동 - > 현재 임시 데이터 리스트 9개 값을 -> 해당 뷰 홀더의 아이템 요소의 값으로 사용.
// 나중에, 텍스트, 이미지 등 여러 데이터를 해당 뷰에 할당 작업.
// 보통 데이터는 API 서버에서 받아온 값(중간 데이터로 json 형식으로 받아서, 필요한 요소만 사용할 예정.)
// 예를 들어, 공공 데이터, 한 아이템의 요소의 값이 10개 있으면, 그 중에서 4개 정도 선택한다.
// 썸네일 이미지 1개, 제목 1개 , 위치 1개, 전화번호 1개
// 해당 뷰에 하나씩 재할당 함.

        class MyAdapter(val datas: MutableList<String>) :
            RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            //필수적으로 재정의 해야하는 함수들
            // 자동완성으로 해당 클래스에서 선택하면, 자동 구현 링크 클릭하기. 자바 Getter Setter같은.
            // 재정의한 함수는 다 자동 호출. 순서 상관X.
            override fun getItemCount(): Int {
                Log.d("kkang", "init datas size: ${datas.size}")
                return datas.size
            }

            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): RecyclerView.ViewHolder =
            //MyViewHolder() 이부분에 정의 부분,
            //참고 코드 : - 실제 item_342.xml
            // 자동 생성 - Item342Binding -> 리사이클러뷰의 하나의 아이템 구성.
            // 순 : 목록 리스트, 구성품을 아이템,
            // 설계 순 : 큰 -> 작은 것
                // 개발 순 : 설계 순과 반대.
                MyViewHolder(
                    Item342Binding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                //holder : RecyclerView.ViewHolder 형 , 부모 클래스 타입
                // holder 형변환, -> 다운캐스팅 MyViewHolder
                val binding = (holder as MyViewHolder).binding

                //뷰에 데이터 출력 : 아이템의 하나의 요소, 어댑터 데이터를 해당 뷰에 연결해주기.
                binding.itemData.text = datas[position]

                //뷰에 이벤트 추가. 리사이클러 뷰 그룹. 즉 레이아웃.
                // 옵션.
                binding.itemRoot.setOnClickListener {
                    // 어댑터 클래스는 액티비티 컴포넌트 클래스X, 해당 부모 클래스에서 상속
                    // 받아야 사용가능. 일단, 로그캣 출력으로 대체.
                    //Toast.makeText(this@MyAdapter,"메세지 요소 인덱스 : $position", Toast.LENGTH_SHORT).show()
                    Log.d("kkang", "item root click : $position")
                }
            }
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main4)
            val binding = ActivityMain4Binding.inflate(layoutInflater)
            setContentView(binding.root)

            //임시 데이터
            val datas = mutableListOf<String>()
            for (i in 1..9) {
                datas.add("Item $i")
            }
            // 설정 아래 클래스 확인.
            // 1) 뷰 홀더, 2) 어댑터 3) 레이아웃 매니저

            // 설정을 적용
            // 출력을 위한 목록의 틀이 필요함. - > activity_main4 여기에 아이디(recyclerView)를 추가하기.
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            //틀에 해당 어댑터 붙이기 작업.
            binding.recyclerView.adapter = MainActivity5.MyAdapter(datas)
            // 옵션 : 아이템 간의 구분선을 넣는 작업.
            binding.recyclerView.addItemDecoration(
                DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
            )
        }
    }
