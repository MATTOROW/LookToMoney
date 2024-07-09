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

        val db_wallet = DB_wallet_Helper(context, factory)

        if (change.category!!.type.equals("Income")){
            db_wallet.plusToWallet(change.wallet!!.name, change.numb)
        }
        else{
            db_wallet.minusToWallet(change.wallet!!.name, change.numb)
        }

    }

    private fun addDay(day : Day, wallet: Wallet){
        val db = this.writableDatabase

        val arr : ArrayList<Change> = ArrayList()

        for (ch in day.changes){
            if (ch.wallet!!.name != wallet.name) arr.add(ch)
        }

        if (arr.size != 0){
            val values = ContentValues()
            values.put("date", day.data.toString())

            val mapper = ObjectMapper()
            values.put("changes", mapper.writeValueAsString(arr))

            db.insert("days", null, values)
        }

        db.close()
    }

    fun replaceAllWithoutThisCategory(category: Category){
        val list = getAll()
        val db = this.writableDatabase
        db.execSQL("DELETE FROM days")
        db.close()

        for (day in list){
            addDay(day, category)
        }
    }

    private fun addDay(day : Day, category: Category){
        val db = this.writableDatabase

        val arr : ArrayList<Change> = ArrayList()

        for (ch in day.changes){
            if (ch.category!!.name != category.name || ch.category!!.type != category.type) arr.add(ch)
        }

        if (arr.size != 0){
            val values = ContentValues()
            values.put("date", day.data.toString())

            val mapper = ObjectMapper()
            values.put("changes", mapper.writeValueAsString(arr))

            db.insert("days", null, values)
        }

        db.close()
    }

    fun replaceAllWithoutThisWallet(wallet: Wallet){
        val list = getAll()
        val db = this.writableDatabase
        db.execSQL("DELETE FROM days")
        db.close()

        for (day in list){
            addDay(day, wallet)
        }
    }

    fun getAll() : ArrayList<Day>{
        val db = this.readableDatabase
        val values = db.rawQuery("SELECT * FROM days", null)
        var list : ArrayList<Day> = ArrayList()
        if (values.moveToFirst()){
            do {
                val date = values.getString(1)
                val json = values.getString(2)
                var change_list = ArrayList<Change>()
                val mapper = ObjectMapper()
                change_list = mapper.readValue(json)
                list.add(Day(MyDate(date), change_list))
            }
            while (values.moveToNext())
        }
        values.close()
        db.close()
        return list
    }

    fun deleteChangeByDate(this_date: MyDate, change: Change){
        var arr = getAll(this_date)

        val db = this.writableDatabase
        db.execSQL("DELETE FROM days WHERE date = '${this_date.toString()}'")
        db.close()

        addDay(Day(this_date, arr), change)
    }


    private fun addDay(day: Day, change: Change){
        val db = this.writableDatabase

        val arr : ArrayList<Change> = ArrayList()

        var flag = false

        for (ch in day.changes){
            if (flag
                || ch.category!!.name != change.category!!.name
                || ch.category!!.type != change.category!!.type
                || ch.wallet!!.name != change.wallet!!.name
                || ch.numb != change.numb
                ) arr.add(ch)
            else{
                flag = true
            }
        }

        if (arr.size != 0){
            val values = ContentValues()
            values.put("date", day.data.toString())

            val mapper = ObjectMapper()
            values.put("changes", mapper.writeValueAsString(arr))

            db.insert("days", null, values)
        }

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