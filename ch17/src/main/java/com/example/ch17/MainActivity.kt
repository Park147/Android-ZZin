package com.example.ch17

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ch17.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    //test17_2 -> 할일 목록 만들기, 할일 내용을 DB 저장.
    // 입력 뷰, 출력 뷰도 있어서 요걸 확인.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    class DBTest(context: Context) : SQLiteOpenHelper(context, "textDB", null, 1) {
        override fun onCreate(db: SQLiteDatabase?) {
            if (db != null) {
                db?.execSQL(
                    "create table USER_TBL (" +
                            "id integer primary key autoincrement," + "name not null,count integer)"
                )
            }
        }

            override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
                TODO("Not yet implemented")
            }


        }
    }
