import androidx.lifecycle.viewmodel.CreationExtras.Empty.map

//최상위 영역
// java : int num = 1;
// 코틀린 : val(or var) 변수명 : 타입 = 값;
val num : Int = 1 ;

class Test {
}
// 우리가 왜 IDE 를 사용?
// > 편의성. 기본적인 문법 체크를 해줌.
// 문법에 다 외울려고 X
// 통계적으로 자주 쓰는 거, 자주 연습하면 되.
val num2 : String = "";

fun main() {

    var data9 : Any = "abcd"
    when(data9) {
        is String -> println("data9 의 값은 문자열 : $data9")
        10 -> println("data9 의 값은 10")
        in 1..10 -> println("data9 의 값은  숫자 : $data9")
        "10" -> println("data9 의 값은 10")
        else -> {
            println("data8 의 값은 ??")
        }
    }

    //when 쓰임 좋음.
    var data8 = "abc"
    when(data8) {
        "10" -> println("data8 의 값은 10")
        "abc" -> println("data8 의 값은 abc")
        else -> {
            println("data8 의 값은 ??")
        }
    }

    var data7 = 10
    when(data7) {
        10 -> println("data7 의 값은 10")
        20 -> println("data7 의 값은 20")
        else -> {
            println("data7 의 값은 ??")
        }
    }

    var data5 = 10;
    var data6 = if(data5>0){
       println("표현식확인")
        30
    } else {

        }


    val map = mapOf<String,String>(Pair("one","hello"), "two" to "2")
    println("""
        map size : ${map.size}
        map data : ${map.get("one")}, ${map.get("two")}
     """.trimIndent())

    //가변리스트 확인.
    var mL = mutableListOf<Int>(1,2,3)
    mL.add(3,100)
    println("""
        list size : ${mL.size}
        list data : ${mL.[0]}, ${mL.get(1)}
     """.trimIndent())

    var list = listOf<Int>(10,20,30)
    // 불변 리스트 변경 불가.
    //list[0] = 100
    println("""
        list size : ${list.size}
        list data : ${list[0]}, ${list.get(1)}
     """.trimIndent())

    val data4 = intArrayOf(1,2,3)
    val data3 = arrayOf<Int>(10,20,30)

//타입을 제너릭이 아닌 IntArray 기초 타입으로 할 수 있다.
    val data2 : IntArray = IntArray(3,{0})
    data2[0] = 100
    println("data2의 값 조회 : ${data2[0]}")

    // -> 1번Array, 2번 list, 3번 map 잘 쓰인다.

    //배열 -> 자바(코틀린) : *동일한* 데이터 타입의 값들을 할당함
    //Javascript : *여러 가지*의 데이터 타입의 값들을 할당함.
    //Array(배열의 갯수, 초깃값)
    // 람다(λ)식은 문법이 : { 매개변수 -> 실행될 문장 }
    // 람다식에서 매개변수가 1개면 화살표, 매개변수를 생략
    // : {실행될 문장. }
    val data1: Array<Int> = Array(3, {0}) // Array<Int> - <>는 제너릭인데 타입의 안정성을 위해 씀.
    println("data1의 값 조회 : ${data1[0]}")

    // 함수의 매개변수에는 var, val 키워드 사용 XX
    // 자동으로 val 가 들어가 있어서. later>자바랑 다른 생성자 조금 헷갈림.
    fun sum3(no:Int, no2:Int){ // : Unit 생략 - 자바: void 생략된거
        val result = no + no2
        println("no + no2 = $result")
    }
    
    // 함수의 결과값의 타입dmf Nothing
    fun some1(): Nothing? { // ?는 null 허용이 가능한 연산자!
        return null
    }

    val num3 : Any = "ParkSeohyun" // Any, Object와 비슷.
    fun sum2(no:Int,no2:Int){ // : Unit 생략 - 자바: void 생략된거
        val result = no + no2
        println("no + no2 = $result")
    }

    //sum2( no: 10, no2: 20)

    fun sum(no:Int):Int {
        // 타입 생략 -> 추론 가능해서.[javascript 도]
        var sum = 0
        for (i in 1..no ){
            sum += i
        }
        return sum
    }
    val result = sum(10)
    println("result : $result")

    println("hi android")
    println("num 의 값 : $num")

}