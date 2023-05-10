
package com.example.ExTest0221

import java.util.Scanner

data class User(val id: String, val pw: String, val email: String, val phone: String)

class Login {
    //fun login(users: List<User>, id: String, pw: String) {
    //    val user = users.find { it.id == id && it.pw == pw }
    //    if (user != null) {
    //        println("로그인 성공 : $user")
    //    } else {
    //        println("로그인 실패")
    //    }
    fun login(users: List<User>, id: String, pw: String) {
        if (id == "admin" && pw == "1234") {
            println("로그인 성공 : $id")
        } else {
            println("로그인 실패")
        }
    }


}

class Register {
    fun register(users: MutableList<User>) {
        val scanner = Scanner(System.`in`)
        print("ID를 입력하세요 : ")
        val id = scanner.nextLine()
        print("PW를 입력하세요 : ")
        val pw = scanner.nextLine()
        print("email을 입력하세요 : ")
        val email = scanner.nextLine()
        print("phone을 입력하세요 : ")
        val phone = scanner.nextLine()
        users.add(User(id, pw, email, phone))
    }
}
fun main() {
    val users = mutableListOf(User("admin", "1234", "admin@example.com", "010-1234-5678"))
    val Login1 = Login()
    val register1 = Register()

    while (true) {
        println("1. 로그인 2. 회원가입")
        val scanner = Scanner(System.`in`)
        when (scanner.nextInt()) {
            1 -> {
                print("ID를 입력하세요 : ")
                val id = readLine()!!
                print("PW를 입력하세요 : ")
                val pw = readLine()!!
                Login1.login(users, id, pw)
            }

            2 -> register1.register(users)
            else -> break
        }
    }
}