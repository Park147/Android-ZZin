package com.example.test17_2

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.test17_2.databinding.ActivityMainBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date


class MainActivity : AppCompatActivity() {
    //전역으로 바인딩 선언.
    lateinit var binding: ActivityMainBinding

    // 코드 복사, 후 처리, 외부 앱에서 연동된 데이터는 원래의 앱으로 돌아온다.
    lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>
    lateinit var requestContractsLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
// 사용, 할당하므로 해서, lateinit 동작.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestPermissionLauncher = registerForActivityResult(
            // 여기에는, 1)후처리, 2) 특정의 퍼미션의 결과를 받을 수 있음.

            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            // 넘어온 정보는 해당 앱에 접근을 하기 위한 권한에 대한 승인 정보
            // it, 하나의 매개변수를 가리킴.
            for (entry in it.entries) {
                // android.permission.READ_CONTACTS : 액션 문자열, 상수로 정해져 있음.
                // 외부저장소, 특정 권한 관련된 문자 상수 여러개.
                if (entry.key == "android.permission.READ_CONTACTS" && entry.value) {//&&, || 단락회로
                    Log.d("kkang", "granted ok...")
                    // Intent.ACTION_PICK - > 두 번째 매개변수 데이터가, 연락처, 위도경도, 웹주소 등에 따라서
                    // 호출하는 앱이 다름. 연락처 앱, 지도 앱, 브라우저.
                    val intent =
                        Intent(
                            Intent.ACTION_PICK,
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                        )
                    // 후처리의 시작. 현재앱 호출 -> 연락처 앱: 데이터 선택 -> 현재 앱 으로 돌아옵니다.
                    requestContactsLauncher.launch(intent)
                }else {
                    Toast.makeText(this, "required permission..", Toast.LENGTH_SHORT).show()
                }
            }

        }

        //위에서, 연락처에 접근하기 위한 권한 설정을 후처리.
                // 연락처 앱에 접근해서, 데이터 선택 후, 다시 돌아오는 과정.
                // 똑같은 패턴. 사진첩, 카메라. 모든 외부앱의 데이터 연동 부분.
                requestContactsLauncher = registerForActivityResult(
                    // 이 부분이, 후 처리 데이터 결과를 처리
                    ActivityResultContracts.StartActivityForResult()
                )
                {
                    // 연락처 접근 승인이 받은 상태이면, 이제, 넘어온 연락처 정보를 불러오는 과정.
                    if (it.resultCode == RESULT_OK) {
                    }
                    // it.data 이부분 선택된 연락처 정보 하나가 있음.
                    Log.d("kkang", "${it.data?.data}")

                    //커서(sql light때 자세히 알려줌.)
                    val cursor = contentResolver.query(
                        it!!.data!!.data!!,
                        arrayOf<String>(
                            // 연락처, 사람 이름.
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                            // 연락처, 전화번호, 상수로 등록된 문자열.
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        ),
                        null,
                        null,
                        null
                    )
                    Log.d("kkang", "cursor size....${cursor?.count}")
                    if (cursor!!.moveToFirst()) {
                        val name = cursor?.getString(0)
                        val phone = cursor?.getString(1)
                        binding.resultContact.text = "name: $name, phone: $phone"
                    }
                }
            }
            //연락처 버튼, 순서)
            // 버튼 클릭 ->
            binding.contactButton.setOnClickListener {
                if (ContextCompat.checkSelfPermission(
                        this,
                        "android.permission.READ_CONTACTS"
                    ) !== PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissionLauncher.launch(arrayOf("android.permission.READ_CONTACTS"))
                } else {
                    val intent = Intent(
                        Intent.ACTION_PICK,
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                    )
                    requestContactsLauncher.launch(intent)
                }
            }
        }
    }
