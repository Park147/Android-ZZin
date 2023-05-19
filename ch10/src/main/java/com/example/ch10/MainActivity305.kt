package com.example.ch10

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import com.example.ch10.databinding.ActivityMain305Binding
import kotlin.concurrent.thread

class MainActivity305 : AppCompatActivity() {
    //전체 소스 코드 (답지 코드)에서,


    // 테스트 가능함.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain305Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val builder: NotificationCompat.Builder

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelId = "one-channel"
                val channelName = "My Channel One"
                val channel = NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_LOW
                )
                //채널에 다양한 정보 설정
                channel.description = "My Channel One Description"
                manager.createNotificationChannel(channel)

                //채널을 이용하여 builder 생성
                builder = NotificationCompat.Builder(this, channelId)
            } else {
                builder = NotificationCompat.Builder(this)
            }

            builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
            builder.setWhen(System.currentTimeMillis())
            builder.setContentTitle("Content Title")
            builder.setContentText("Content Massage")

            builder.setProgress(100, 0, false)
            manager.notify(11, builder.build())

            // 스레드(=작업하는 단위), 간단한 예
            // File IO, 입력과 출력을 동시에 진행할 때,
            // 채팅 예) , 제가 채팅 메세지를 입력과 출력을 동시에 메시지 수신을 받을 수 있음.
            // 스레드 없이, 동작을 하게 되면, 동기적 진행, 무조건, 앞에 어떤 프로세스 실행이
            // 끝난 다음에, 메세지를 받을 수 있음.
            // 앱에서 연산을 수행하는 부분이 시간이 좀 걸림.
            // 동기적으로 처리를 한다면, 오래 걸리는 작업이 순서가 앞에 나왔다.
            // 그러면, 뒷 서비스는 아무것도 동작할 수 없다.
            // 비동기적 처리를, 멀티 스레드로 동작을, 병렬 처리가 가능하다.
            // 스레드 업그레이드 -> 경량식 스레드 -> 코루틴 작업을 함.
            // 실제로 많은 외부 API들은 이미 코루틴 기법을 이용해서 비동기적으로 작업을 많이 함.
            // 일단, 현재 우리는 잘 이용하는 것에 초점을 두고, 수준이 올라오면 그때 API나 라이브러리를 만들어서 이용하면 됨!.
            // 스레드 참고자료 쌤 git - test17_2_Thread_~
            thread {
                for (i in 1..100){
                    builder.setProgress(100, i, true)
                    manager.notify(11, builder.build())
                    SystemClock.sleep(100)
                }
            }
        }
    }
}