//최상위 영역
// java : int num = 1;
// 코틀린 : val(or var) 변수명 : 타입 = 값;
val num : Int = 1 ;

class Test {
}
// 우리가 왜 IDE 를 사용?
// 편의성. 기본적인 문법 체크를 해줌.
// 문법에 다 외울려고 X
// 통계적으로 자주 쓰는 거, 자주 연습하면 되.
//val num2 : String;

fun main() {
    val num3 : Any = "ParkSeohyun" // Any, Object와 비슷.
    fun sum2(no:Int,no2:Int){ // : Unit 생략 - 자바: void 생략된거
        val result = no + no2
        println("no + no2 = $result")
    }

    sum2(no: Int, no2: Int)

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