package com.example.user.contactlist

import com.example.user.contactlist.data.model.Contact
import java.sql.DriverManager.println

fun main(args: Array<String>) {
    val letter = 'A'
    println("$letter")

    val bin: Boolean
    bin = true
    println("$bin")

    // var can be modified bur val cannot
    var one: String
    one = "One"
    println("$one")
    one = "two"
    println("$one")

    val contact : Contact = Contact("narges", "1", "photo")
    println(contact.name)

    // range
    val i = 2
    if (i in 1..10)
        println(i.toString())

    // when (switch case state)
    val x = 5
    when(x) {
        1 -> println("X in one")
        2 -> println("X in two")
        3,4 -> println("X is three or four")
        else -> println("X is upper than four")
    }

    //____________________________________________

    var st: String = ""
    //st = null has error
    println(st)

    val stt: String?
    stt = null
    println(stt!!)

    //____________________________________________
}