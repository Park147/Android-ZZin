package com.example.test18

import android.app.Application
import com.example.test18.model.UserListModel
import com.example.test18.retrofit.INetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyApplication : Application() {
    // MyApplication : Application() 상속을 받아야함.!!
    // 역할 : INetworkService 인터페이스 타입의 변수를 선언
    // Retrofit 타입의 객체를 생성.
    // retrofit.create(INetworkService::class.java)
    // -> 위에서 정의한 인터페이스를 구현한 객체를 반환한 값을 가지고와서
    // networkService 에 할당함.

    // 현)선언만 함.할당해야지. 아래 init 이 할당한다. = 초기화 된다.

    // 주의 사항, 사용여부 - > 매니페스트 파일의 <application 태그 내부에
    // name으로 설정해서, 해당 앱을 실행할 때, 메모리상에 등록해서 사용한다.


    //순서5
    //인터페이스 타입의 서비스 객체 얻기.
    //예시)
    var networkService: INetworkService

    //    순서4
//    Retrofit 객체 생성
//    예시)
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    init { // 클래스를 생성 또는 사용하면, 할당작업.

        //순서5
        //인터페이스 타입의 서비스 객체 얻기.
        //예시)
        networkService = retrofit.create(INetworkService::class.java)
        //retrofit.create 함수 부분이
        //직접 만든 인터페이스 인자로 사용하고,
        //반환 값 : 인터페이스를 구현한 클래스의 객체를 반환해줌.
        //실제 네트워크가 필요할 때 이 객체의 함수를 호출하면 됨.

    }

}