package ru.itis.looktomoney.domain

class MyDate(
    var day : Int,
    var month : Int,
    var year : Int
) {
    override fun toString(): String {
        return "$day.$month.$year)"
    }
}