package ru.itis.looktomoney.domain

import java.time.LocalDate

class Day (
    val data : MyDate,
    val changes : ArrayList<Change>
){

    fun addChange(chr : Change){
        changes.add(chr)
    }
}