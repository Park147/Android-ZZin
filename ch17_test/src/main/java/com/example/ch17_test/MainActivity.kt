package com.example.ch17_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch17_test.AddActivity
import com.example.ch17_test.DBHelper
import com.example.ch17_test.MyAdapter
import com.example.ch17_test.SettingActivity
import com.example.ch17_test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //ch17_database : 복사함.
    //모듈은 만듬. 나머지 일단 복사해서 재사용.

    //현) api33, 원본 api 32, 참고
    //뷰 바인딩 부분, build.gradle 항목에 dependencies 부분, 확인 후 복사.
    //파일 복사시, 주의사항, 코드, xml 화면 복사 후 ,매니페스트 파일 확인 부탁.
    // res -> 이미지, 등록된, 문자열, 크기, 컬러, 테마, 등

    // 중요 하게 보아야할 것들 1. SQLite 적용 방법. ->DBHelper 부분
    // 2. 글쓰기와, 하나의 할일 이 리사이클러뷰에 어떻게 적용되늕 부분
    // 3. 어댑터, 뷰홀더, 레이아웃 매니저 연결해서, 적용하는 부분

    lateinit var binding: ActivityMainBinding
    var datas: MutableList<String>? = null
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            it.data!!.getStringExtra("result")?.let {
                datas?.add(it)
                adapter.notifyDataSetChanged()
            }
        }
        binding.mainFab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            requestLauncher.launch(intent)
        }

        datas= mutableListOf<String>()

        //add......................

        val db = DBHelper(this).readableDatabase
        val cursor = db.rawQuery("select * from TODO_TB", null)
        cursor.run {
            while(moveToNext()){
                datas?.add(cursor.getString(1))
            }
        }
        db.close()

        val layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.layoutManager=layoutManager
        adapter=MyAdapter(datas)
        binding.mainRecyclerView.adapter=adapter
        binding.mainRecyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId===R.id.menu_main_setting){
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

}