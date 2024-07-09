package ru.itis.looktomoney.domain

import java.time.LocalDate

class Day (
    val data : MyDate,
    val changes : ArrayList<Change>
) : Comparable<Day>{

    fun addChange(chr : Change){
        changes.add(chr)
    }

    override fun compareTo(other: Day): Int {
        return data.compareTo(other.data)
    }
}