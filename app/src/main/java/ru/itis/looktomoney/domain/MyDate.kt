package ru.itis.looktomoney.domain

class MyDate{
    private var day : Int = 0
    private var month : Int = 0
    private var year : Int = 0

    constructor(str : String) {
        val list = str.trim().split(".")
        this.day = list[0].toInt()
        this.month = list[1].toInt()
        this.year = list[2].toInt()
    }
    constructor(day : Int, month : Int,year : Int) {
        this.day = day
        this.month = month
        this.year = year

    }



    override fun toString(): String {
        return "$day.$month.$year)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MyDate

        if (day != other.day) return false
        if (month != other.month) return false
        if (year != other.year) return false

        return true
    }

    override fun hashCode(): Int {
        var result = day
        result = 31 * result + month
        result = 31 * result + year
        return result
    }
}