package com.example.ch08test

import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ch08test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var initTime = 0L
    var pauseTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //바인딩, 여러 뷰를 한번 접근하기 편한 기술.
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // 이벤트 핸들러 부분. 방법 3, SAM
        binding.startButton.setOnClickListener {
            binding.chronometer.base = SystemClock.elapsedRealtime()+pauseTime // 흐르는 시간() +멈춘 시간
            binding.chronometer.start()
            binding.stopButton.isEnabled=true
            binding.resetButton.isEnabled=true
            binding.startButton.isEnabled=false
        }

        binding.stopButton.setOnClickListener {
            pauseTime=binding.chronometer.base - SystemClock.elapsedRealtime() // 멈춘 시간 = 기본시간 - 흐르는 시간
            binding.chronometer.stop()
            binding.stopButton.isEnabled=false
            binding.resetButton.isEnabled=true
            binding.startButton.isEnabled=true
        }

        binding.resetButton.setOnClickListener {
            pauseTime = 0L
            binding.chronometer.base = SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.stopButton.isEnabled=false
            binding.resetButton.isEnabled=false
            binding.startButton.isEnabled=true
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode === KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis() - initTime > 3000){
                Toast.makeText(this, "종료하려면 한 번 더 누르세요!!", Toast.LENGTH_SHORT).show()
                initTime = System.currentTimeMillis()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}