package ru.itis.looktomoney.domain

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ru.itis.looktomoney.R

class DB_wallet_Helper(
    val context : Context,
    val factory : SQLiteDatabase.CursorFactory?
) : SQLiteOpenHelper(context, "wallet", factory, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE  wallets(id INT PRIMARY KEY, name TEXT, numb REAL, icon Int)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS wallets")
        onCreate(db)
    }

    fun addWallet(wl : Wallet){
        val values = ContentValues()

        values.put("name", wl.name)
        values.put("numb", wl.numb)
        values.put("icon", wl.icon)

        //проверка, нет ли такого счёта уже
        val list = getAll()
        for (wal in list){
            if (wal.name == wl.name) return
        }

        val db = this.writableDatabase
        db.insert("wallets", null, values)

        db.close()
    }

    fun plusToWallet(string: String, int : Double){
        val wallet : Wallet = getByName(string)!!
        delteByName(string)

        wallet.numb += int

        addWallet(wallet)
    }

    fun minusToWallet(string: String, int : Double){
        val wallet : Wallet = getByName(string)!!
        delteByName(string)

        wallet.numb -= int

        addWallet(wallet)
    }


    fun getByName(string: String) : Wallet?{
        val list = getAll()
        for (wal in list){
            if (wal.name.equals(string)) return wal
        }
        return null
    }

    fun delteByName(string: String){
        val db = this.writableDatabase

        db.execSQL("DELETE FROM wallets WHERE name = '$string'")
    }

    fun getAll() : ArrayList<Wallet>{
        val db = this.readableDatabase
        val values = db.rawQuery("SELECT * FROM wallets", null)
        val list : ArrayList<Wallet> = ArrayList()
        if (values.moveToFirst()){
            do {
                val numb = values.getDouble(2)
                val name = values.getString(1)
                val icon = values.getInt(3)
                list.add(Wallet(numb, name, icon))
            } while (values.moveToNext())
        }
        values.close()
        db.close()
        return list
    }
}