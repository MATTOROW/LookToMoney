package ru.itis.looktomoney.domain

import java.time.LocalDate

class Day (
    val data : MyDate,
    private val changes : ArrayList<Change>
){

    fun addChange(chr : Change){
        changes.add(chr)
    }
}