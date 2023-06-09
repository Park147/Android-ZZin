package com.example.ch11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView

class ThreeActivity : AppCompatActivity() {
    // xml 에서 만든 메뉴를 해당 액티비티 화면에 적용하는 코드임.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //한줄로 표현.
//        menuInflater.inflate(R.menu.menu_three, menu)

        //서치 뷰 이벤트 처리 관련 코드를, >> Test11- MainActivity330 참고
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_three, menu)

        val menuItem = menu?.findItem(R.id.searchMenu)
        val searchView = menuItem?.actionView as SearchView
        // 액션바 -> 서치 뷰의 -> 이벤트 핸들러 정의.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //기본으로 제공하는 3가지 함수.
            override fun onQueryTextChange(newText: String?): Boolean {
                //검색어가 변경시 수행하는 함수.
                Log.d("psh", "검색어가 변경시 내용: ${newText}")
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                // 키보드의 검색 버튼을 클릭한 순간의 이벤트
                Toast.makeText(this@ThreeActivity, "클릭시 이벤트 발생!", Toast.LENGTH_SHORT).show()
                return true
            }
        })
        return true
    }

//        return super.onCreateOptionsMenu(menu)
    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_three)

