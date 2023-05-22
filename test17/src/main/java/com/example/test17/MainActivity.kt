package com.example.test17

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test17.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.File
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //참고 코드 -> ch17_database -> 할일 목록 만들기 , 할일 내용을 데이터베이스 저장,
        // 입력 뷰, 출력 뷰도 있어서 이걸 확인.

        binding.button1.setOnClickListener {
            //실제 물리 경로에, 물리 파일을 만드는 작업. 빈 파일.
            file = File(filesDir, "test230522-1.txt")
            // 해당 파일에 쓰기 작업.
            val writeStream: OutputStreamWriter = file.writer()
            // writeStream 객체에 write 함수로 쓰기 작업을 진행.
            writeStream.write("앱별 저장소에 파일 저장 테스트 내용.")
            //적용한다.
            writeStream.flush()

            // openFileOutput("test.txt", Context.MODE_PRIVATE).use {
//                it.write("hello world".toByteArray())
//        }

        }
        binding.button2.setOnClickListener {
            //IO - > Input Output, stream 단어가 없으면, 문자 기반, (문자열)
            // stream 이 있으면, 바이트 단위로 작업을 한다.
            val readStream: BufferedReader = file.writer().buffered()
            readStream.forEachLine {
                Log.d("psh","$it")
            }

            // openFileOutput("test.txt").bufferedReader().forEachLine {
//                Log.d("psh","$it")
//        }


    }



    }
}