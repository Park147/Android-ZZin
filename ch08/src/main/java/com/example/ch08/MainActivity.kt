package com.example.ch08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.CompoundButton
import com.example.ch08.databinding.ActivityMainBinding

// 방법2, 클래스로 구현( 인터페이스 구현
class MyEventHandler : CompoundButton.OnCheckedChangeListener {
    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        Log.d("psh", "클래스로 구현 방법2 체크박스 클릭")
    }

    //class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {
    class MainActivity : AppCompatActivity() {
        //뷰 이벤트 처리 방법1 , 인터페이스 구현시 , 재정의
//    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
//        Log.d("lsy","체크박스 클릭.")
//    }
        override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> Log.d("psh", "백키 누름")
                KeyEvent.KEYCODE_VOLUME_UP -> Log.d("psh", "볼륨 업 누름")
                KeyEvent.KEYCODE_VOLUME_DOWN -> Log.d("psh", "볼륨 다운 누름")
            }
            // Log.d("psh","onKeyDown 실행")
            return super.onKeyDown(keyCode, event)
        }
        override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
            // Log.d("psh","onKeyUp 실행")
            return super.onKeyUp(keyCode, event)
        }

        override fun onTouchEvent(event: MotionEvent?): Boolean {
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.d("psh", "Touch down event x: ${event.x}, rawX:${event.rawX}")
                }

                MotionEvent.ACTION_UP -> {
                    Log.d("psh", "Touch up event 발생함.")
                }
            }
            return super.onTouchEvent(event)
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
            setContentView(binding.root) //요걸로 대신 적으면 특정 파일? 접근하기가 쉽다. 복습

            //방법1:
            //리스너 인터페이스를 메인 엑티비티에서 구현 후 , 이벤트 처리방법.
//        binding.check1.setOnCheckedChangeListener(this)

            //방법2:
            //클래스로 정의된 리스너를 사용함.
//        binding.check1.setOnCheckedChangeListener(MyEventHandler())

            //방법3: SAM 기법(single abstract method) ,자바: 함수형 인터페이스, 람다식 구현.
            //인터페이스 : 구성을 추상 메서드로 구성된 것
            //추상 메서드 : 메서드의 선언부는 있지만, 구현부가 없는 것 말함.
            //보통 인터페이스를 구현해서 사용할 때, 보통 재정의해서 사용함.
            binding.check1.setOnCheckedChangeListener
            { a, b ->
                Log.d("psh", "방법3 SAM 기법 구현:체크박스 클릭")
            }

            binding.btn1.setOnLongClickListener
            {
                Log.d("psh", "롱 클릭")
                true
            }

        //
//
//    override fun onBackPressed(){
//        Log.d("psh", "뒤로가기를 눌럿네요.")
//    }
//
//    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
//        Log.d("psh", "onKeyUp 발생")
//        return super.onKeyUp(keyCode, event)
//    }
//
//
//

        }
    }
}
    


