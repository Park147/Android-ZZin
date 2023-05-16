package com.example.ch11

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ch11.databinding.ActivityMain5Binding
import com.example.ch11.databinding.Item342Binding

// 참고 코드 : MainActivity350
// binding 만 입력하고 나머지 전부 복사 후, 변경해서 사용
class MainActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMain5Binding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // 여서부터 끝까지 복사
        // 임시 데이터
        val datas = mutableListOf<String>()
        for(i in 1..9){
            datas.add("Item $i")
        }
        // 설정 적용.
        // 현재 파일이 xml 레이아웃에, recyclerView가 부모 태그 설정 X, 아이디 설정도 안되있어서.
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = MyAdapter(datas)
        binding.recyclerView.addItemDecoration(MyDecoration(this))
    }
    //1번 : val binding: Item342Binding 재사용. 아이템 목록의 하나의 요소, 뷰
    class MyViewHolder(val binding: Item342Binding): RecyclerView.ViewHolder(binding.root)
    //2번 : 주생성자에 임시 데이터 리스트가 정의 되어 있음.
    class MyAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

        override fun getItemCount(): Int{
            Log.d("kkang", "init datas size: ${datas.size}")
            return datas.size
        }
// 우리가 만든 뷰 홀더 생성자 호출하는 부분,
        // 액티비티에서 바인딩 설정 후 , 적용하는 코드 부분과 비슷.
        // 보통 샘플 설정 코드라고 하는데, 이 패턴으로 자주 사용하니, 단순히 이렇게 사용한다 정도로 보기.
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
                = MyViewHolder(Item342Binding.inflate(LayoutInflater.from(parent.context), parent, false))

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            Log.d("kkang","onBindViewHolder : $position")
            val binding=(holder as MyViewHolder).binding
            //뷰에 데이터 출력
            binding.itemData.text= datas[position]

            //뷰에 이벤트 추가
            // 옵션, 클릭시 이벤트.
            binding.itemRoot.setOnClickListener{
                Log.d("kkang", "item root click : $position")
            }
        }
    } // 반복 패턴
    //3번. 옵션, 이미지 넣고, 이미지 사이즈 조절하고, 위치 할당하는 코드
    class MyDecoration(val context: Context): RecyclerView.ItemDecoration() {
        // 리스트 목록을 출력하기 전에, 해당 이미지를 그리는 함수.
        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDraw(c, parent, state)
            // 이미지를 출력하는 메서드.
            // 샘플코드에서, 해당 샘플이미지를 복사, and
            // res -> 이미지를 불러오는 역할.
            c.drawBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.and), 0f,0f,null);
        }
    // 리스트 목록을 출력 후에, 해당 이미지를 그리는 함수.
        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDrawOver(c, parent, state)
            //뷰 사이즈 계산
            val width = parent.width
            val height = parent.height
            //이미지 사이즈 계산 -> 참고만. 넘어가! 가로세로 폭 적당히 조절해 뷰에 넣는 임의의 계산 방법임.
        // 몰라도 됨. 걍 해당 뷰를 적당히 가운데 위치 시키고 싶을 때 사용
        // 원리는 가로,세로 길이의 절반을 구해서, 그 위치에 배치하는 원리.
            val dr: Drawable? = ResourcesCompat.getDrawable(context.getResources(), R.drawable.chromeimage, null)
            val drWidth = dr?.intrinsicWidth
            val drHeight = dr?.intrinsicHeight
            //이미지가 그려질 위치 계산
            val left = width / 2 - drWidth?.div(2) as Int
            val top = height / 2 - drHeight?.div(2) as Int
            c.drawBitmap(
                BitmapFactory.decodeResource(context.getResources(), R.drawable.chromeimage),
                left.toFloat(),
                top.toFloat(),
                null
            )

        }

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            val index = parent.getChildAdapterPosition(view) + 1

            if (index % 3 == 0) //left, top, right, bottom
                outRect.set(10, 10, 10, 60)
            else
                outRect.set(10, 10, 10, 0)

            view.setBackgroundColor(Color.LTGRAY)
            ViewCompat.setElevation(view, 20.0f)

        }
    }
}