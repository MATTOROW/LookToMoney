package ru.itis.looktomoney.domain

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.time.LocalDate

class DB_days_Helper (
    val context : Context,
    val factory : SQLiteDatabase.CursorFactory?
) : SQLiteOpenHelper(context, "all_days", factory, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE days (id INT PRIMARY KEY, date TEXT, changes TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS days")
        onCreate(db)
    }

    fun addChange(change : Change, date : MyDate){

        val arr : ArrayList<Change> = getAll(date)

        val db = this.writableDatabase

        if (arr.size != 0){
            db.execSQL("DELETE FROM days WHERE date = '$date'")
        }

        val values = ContentValues()

        values.put("date", date.toString())

        val mapper = ObjectMapper()
        arr.add(change)
        values.put("changes", mapper.writeValueAsString(arr))


        db.insert("days", null, values)

        db.close()

    }

    fun getAll(this_date: MyDate) : ArrayList<Change>{
        val db = this.readableDatabase
        val values = db.rawQuery("SELECT * FROM days", null)
        var list : ArrayList<Change> = ArrayList()
        if (values.moveToFirst()){
            do {
                val date = values.getString(1)
                if (!date.equals(this_date.toString())){
                    continue
                }
                val json = values.getString(2)
                val mapper = ObjectMapper()
                list = mapper.readValue(json)
                break
            }
            while (values.moveToNext())
        }
        values.close()
        db.close()
        return list
    }
}