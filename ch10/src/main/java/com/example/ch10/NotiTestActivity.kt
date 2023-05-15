package com.example.ch10

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.RemoteInput
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call.Details
import androidx.core.app.NotificationCompat
import com.example.ch10.databinding.ActivityNotiTestBinding

class NotiTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNotiTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //알림 채널 만들고 -> 빌더에 인자로 넣고 -> Notification 객체 만들고 -> notify() 넣기.
        binding.btnChannel.setOnClickListener {
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val builder: NotificationCompat.Builder

            //26버전 기준으로, 26이상이면 A 메서드 형식, 26버젼 미만, B 메소드 형식.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // 채널을 만드는 작업.
                val channelId = "one-channel"
                val channelName = "My Channel One"
                val channel = NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                // 채널 옵션 설정.
                channel.description = "My Channel 테스트 중..235015"
                // 앱을 만들면, 기본 앱 아이콘 -> 알림 확인을 하지 않은 정보의 갯수가 표시 숫자로
                // 우리가 설정하는 것 X, 안드로이드 시스템에서 자동 설정. -> 확인 시 -> 숫자 사라짐.(카톡 바탕화면 새로온 거 빨간 숫자)
                channel.setShowBadge(true)
                // 소리 관련 설정.
                val soundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                // 소리 설정 적용.
                channel.setSound(soundUri, audioAttributes)
                //알림시 LED 깜빡임
                channel.enableLights(true)
                channel.lightColor = Color.RED
                // 알림시 진동 설정 on
                channel.enableVibration(true)
                // 진동 설정시 , 주기 옵션
                channel.vibrationPattern = longArrayOf(100, 200, 100, 200)

                // 만든 채널 NotificationManager 에 등록
                manager.createNotificationChannel(channel)

                // 채널을 이용해서 builder 생성.
                builder = NotificationCompat.Builder(this@NotiTestActivity, channelId)
            } else {
                builder = NotificationCompat.Builder(this@NotiTestActivity)
            }
            builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
            builder.setWhen(System.currentTimeMillis())
            builder.setContentTitle("임시 제목 액션 인텐트 2")
            builder.setContentText("전달할 임시 액션 인텐트 메시지 내용")
            // 자동 닫기 false
            builder.setAutoCancel(false)
            // 스와이프 로 자동 닫기.
            builder.setOngoing(true)
            //그래서, 위의 2가지 옵션을 다 켜두면, 사용자가 알림을 닫을 수 없음.
            //수동으로 : manager.cancle(11) 요청한 코드 번호로 닫아야 함.

            //intent -> 시스템에 메세지를 전달하는 도구. 요거 많이 씀. >later 자세히..
            // 역할 : 1) 화면 간의 전환 , 2) 화면 간의 데이터 전달.
            // 사용은 1) 현재 화면에서 -> DetailActivity 로 전환!<즉 화면 이동>
            val intent = Intent(this@NotiTestActivity, DetatilActivity::class.java)

            //기존의 intent 옵션 부가.
            // 1) 요청 번호, 10
            // 2) 깃발 이용해서 상태 표기.
            val pendingIntent =
                PendingIntent.getActivity(
                    this@NotiTestActivity, 10, intent,
                    PendingIntent.FLAG_IMMUTABLE
                ) // FLAG - 상태

            //2번째 액션 관련 부분, 액션 인텐트 확인.
            val actionIntent = Intent(this@NotiTestActivity, OneReceiver::class.java)
            val actionPendingIntent = pendingIntent.getBroadcast(
                this@NotiTestActivity, 20, actionIntent,
                        PendingIntent.FLAG_IMMUTABLE
            )
            builder.addAction(
                NotificationCompat.Action.Builder(
                    android.R.drawable.stat_notify_more,
                    "Action 제목입니다.",
                    actionPendingIntent
                ).build()
            )

            //3. 액션 부분에 답글을 다는 액션 하나만 더 작업 하기.
            val KEY_TEXT_REPLY = "키_텍스트_답장"
            val replyLabel: String = "답장 테스트"
            val remoteInput : RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
                setLabel(replyLabel)
                build()
            }

            // 답장 화면으로 전환하는 인텐트 설정.
            val replyIntent = Intent(this@NotiTestActivity,ReplyReceiver::class.java)
            val replyPendingIntent = pendingIntent.getBroadcast(this@NotiTestActivity,
            30,replyIntent, PendingIntent.FLAG_MUTABLE)
            builder.addAction(
                NotificationCompat.Action.Builder(
                    android.R.drawable.arrow_down_float,
                    "답장 테스트", replyPendingIntent
                ).addRemoteInput(remoteInput).build()
            )

        //    builder.setContentIntent(pendingIntent)
            builder.setAutoCancel(true)

            // notify 메소드에 인자 값으로 Notification 타입 객체 할당.
            //manager.notify(11, builder.build())
            manager.notify(11, builder.build())

            manager.cancel(11)
            builder.setAutoCancel(false)
            builder.setOngoing(true)
        }

    }
}