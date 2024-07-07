package ru.itis.looktomoney.DateValidator

import android.os.Parcel
import android.os.Parcelable
import java.text.DateFormat
import java.text.SimpleDateFormat

class DateValidatorUsingDateFormat (
    private val format : String
)
{
    fun isValid(str : String) : Boolean {
        val sdf : DateFormat = SimpleDateFormat(format)
        sdf.isLenient = false

        try{
            sdf.parse(str)
        } catch (_ : Exception){
            return false
        }

        return true
    }

}