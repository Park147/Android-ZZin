package Test

class sh {
}
//고차함수 사용예제,
//고차함수는 매개변수 or 결과값 자리에 함수가 들어가는 형태
fun hotFun(arg:(Int) -> Boolean):() -> String{ // 매개변수<람다식 - 변수를 줘야함 - 인자값10받> : 결과값
    val result = if(arg(10)){
        "valid"
    } else {
        "invaild"
    }
    return {"hotFun result 확인 : $result"}
}

fun main(){
    // ? null 허용 연산자 및 , null 허용 변수 호출: 접근시 반드시 - ?. 접근.
    // ?: 엘비스 연산자 : null X, X 값 호출. null O, 지정한 기본값이 할당.
    val data20:String? = "psh"
    println("data20 의 길이 : ${data20?.length ?: 0}")


    //고차함수 사용예제
    val result16 = hotFun({no -> no > 0})
    println(result16())

    val some2 = {no1:Int, no2:Int -> println("출력")
        no1 *no2
    }

    println("익명함수 출력 확인 some2(1,2) : ${some2(1,2)}")
}



