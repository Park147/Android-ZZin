package com.example.test17_crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.test17_crud.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // 전역으로 선언만 했지, 할당 X.
    // So, onCreate 라는 함수에서, 최초 1회 실행시.
    // 할당을 하는 구조.
    var myDB: DatabaseHelper? = null
    lateinit var binding: ActivityMainBinding

    var editTextName: EditText? = null
    var editTextPhone: EditText? = null
    var editTextAddress: EditText? = null
    var editTextID: EditText? = null
    var buttonInsert: Button? = null
    var buttonView: Button? = null
    var buttonUpdate: Button? = null
    var buttonDelete: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 전역에 선언된 변수들을 할당하는 구조.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)
        //DatabaseHelper 클래스 를 사용한다.
        // 객체 생성한다. - > 스티커 메모 23.위에 참고)자바처럼 new 없고 바로 불러쓴다.
        myDB = DatabaseHelper(this)

        // 자바 버전에 코드 -> 코틀린 변경.
        // findViewById -> 바인딩 기법으로 변경.
        editTextName = binding.editTextName
        editTextPhone = binding.editTextPhone
        editTextAddress = binding.editTextAddress
        editTextID = binding.editTextID
        buttonInsert = binding.buttonInsert
        buttonView = binding.buttonView
        buttonUpdate = binding.buttonUpdate
        buttonDelete = findViewById(R.id.buttonDelete)
        buttonDelete = binding.buttonDelete
        // 최초 1회 실행시, 직접 만든 함수를 호출하는 부분.
        AddData()
        viewAll()
        UpdateData()
        DeleteData()
    }

    //데이터베이스 추가하기
    fun AddData() {
        buttonInsert!!.setOnClickListener {
            val isInserted = myDB!!.insertData(
                editTextName!!.text.toString(),
                editTextPhone!!.text.toString(),
                editTextAddress!!.text.toString()
            )
            if (isInserted == true) Toast.makeText(this@MainActivity, "데이터추가 성공", Toast.LENGTH_LONG)
                .show()
            else Toast.makeText(this@MainActivity, "데이터추가 실패", Toast.LENGTH_LONG).show()
        }
    }

    // 데이터베이스 읽어오기
    fun viewAll() {
        buttonView!!.setOnClickListener(View.OnClickListener {
            // res에 조회된, 테이블의 내용이 들어가 있다. select 의 조회의 결괏값 있다.
            val res = myDB!!.allData
            // 결과값 X 때
            if (res.count == 0) {
                ShowMessage("실패", "데이터를 찾을 수 없습니다.")
                return@OnClickListener
            }
            // 결과가 있다면.
            // 자바에서, String 단점, 새로운 문자열이 있다면, 매번 새로 주소를 생성.
            // StringBuffer 하나의 객체에 해당 문자열을 추가만 하는 형태라서, 주소를 새로 생성X.

            val buffer = StringBuffer()
            //res 형 -> Cursor, 쉽게 액셀 마치 테이블, 0행부터 시작한다.
            // res.moveToNext() -> 1행을 의미.
            while (res.moveToNext()) {
                buffer.append(
                    //코틀린 3중 따옴표, 멀티 라인.(=다중 주석)
                    // 1행의 첫번째 컬럼을 가져오기.
                    //  아래 trim 가지 치기 = 공백을 제거 한다.
                    """
    ID: ${res.getString(0)}
    
    """.trimIndent()
                )//""" ~ 이까지 한 묶음. 아래 buffer.append( ~ 이름: ${res.getString(1)} 한 묶음.
                buffer.append(
                    """
    이름: ${res.getString(1)}
    """.trimIndent()
                )
                buffer.append(
                    """
    전화번호: ${res.getString(2)}
    
    """.trimIndent()
                )
                buffer.append(
                    """
    주소: ${res.getString(3)}
    
    
    """.trimIndent()
                )
            }
            ShowMessage("데이터", buffer.toString())
        })
    }

    //데이터베이스 수정하기
    fun UpdateData() {
        buttonUpdate!!.setOnClickListener {
            val isUpdated = myDB!!.updateData(
                editTextID!!.text.toString(),
                editTextName!!.text.toString(),
                editTextPhone!!.text.toString(),
                editTextAddress!!.text.toString()
            )
            if (isUpdated == true) Toast.makeText(this@MainActivity, "데이터 수정 성공", Toast.LENGTH_LONG)
                .show() else Toast.makeText(this@MainActivity, "데이터 수정 실패", Toast.LENGTH_LONG)
                .show()
        }
    }

    // 데이터베이스 삭제하기
    fun DeleteData() {
        buttonDelete!!.setOnClickListener {
            val deleteRows = myDB!!.deleteData(editTextID!!.text.toString())
            if (deleteRows > 0) Toast.makeText(this@MainActivity, "데이터 삭제 성공", Toast.LENGTH_LONG)
                .show() else Toast.makeText(this@MainActivity, "데이터 삭제 실패", Toast.LENGTH_LONG)
                .show()
        }
    }

    fun ShowMessage(title: String?, Message: String?) { // 사용자정의 함수
        val builder = AlertDialog.Builder(this) // 기본 샘플
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }
}