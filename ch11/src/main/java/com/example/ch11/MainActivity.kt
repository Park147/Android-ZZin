package com.example.ch11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.ch11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 재정의 샘플
//    override fun onSupportNavigateUp(): Boolean {
//        Toast.makeText(this@MainActivity,"메인 업버튼 동작확인",Toast.LENGTH_SHORT).show()
//        onBackPressed()
//        return super.onSupportNavigateUp()
//        }
//ㅇ 액션바의 메뉴 구성, 코드부분 참고.
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val menuItem1 : MenuItem? = menu?.add(0,0,0, "menu1")
//        val menuItem2 : MenuItem? = menu?.add(0,1,0, "menu2")
//        return super.onCreateOptionsMenu(menu)
//    }
    //메뉴가 선택 되었을 때, 이벤트 처리하는 함수 확인.

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        0 -> {
            Toast.makeText(this@MainActivity,"menual click",Toast.LENGTH_SHORT).show()
            true
        }
        1 -> {
            val intent = Intent(this@MainActivity,TwoActivity::class.java)
            // 단순 화면 전환으로만 사용하므로, 기본 startActivity 사용.
            startActivity(intent)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //코드로 업버튼 생성
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //버튼 클릭시 화면 전환 방법, 미리 인텐트를 이용.
        //구체적 데이터 전달 >later ch.13

        binding.btn1.setOnClickListener {

        val intent = Intent(this@MainActivity,TwoActivity::class.java)
        // 단순 화면 전환으로만 사용하므로, 기본 startActivity 사용.
        startActivity(intent)
        }
    }
}