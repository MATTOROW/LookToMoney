package ru.itis.looktomoney.domain

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ru.itis.looktomoney.R
import ru.itis.looktomoney.adapters.Icons

class DB_category_Helper(
    val context : Context,
    val factory : SQLiteDatabase.CursorFactory?
) : SQLiteOpenHelper(context, "category", factory, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE categorys (id INT PRIMARY KEY, name TEXT, description TEXT, icon TEXT, type TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS categorys")
        onCreate(db)
    }

    fun addCategory(ic : Category){
        val values = ContentValues()

        values.put("name", ic.name)
        values.put("description", ic.description)
        values.put("icon", ic.icon)
        values.put("type", ic.type)

        //проверка, нет ли такой категории уже
        val list = getAll(ic.type)
        for (cat in list){
            if (cat.name == ic.name) return
        }

        val db = this.writableDatabase
        db.insert("categorys", null, values)

        db.close()
    }

    fun getAllIncomeCategorys() : ArrayList<Category>{
        return getAll("Income")
    }

    fun getAllOutcomeCategorys() : ArrayList<Category>{
        return getAll("Outcome")
    }

    fun deleteThisCategory(category : Category){
        val db = this.writableDatabase

        db.execSQL("DELETE FROM categorys WHERE name = '${category.name}' AND type = '${category.type}' ")

        val db_days = DB_days_Helper(context, null)
        db_days.replaceAllWithoutThisCategory(category)
    }


    private fun getAll(str : String) : ArrayList<Category>{
        val db = this.readableDatabase
        val values = db.rawQuery("SELECT * FROM categorys", null)
        val list : ArrayList<Category> = ArrayList()
        if (values.moveToFirst()){
            do {
                val type = values.getString(4)
                if (!type.equals(str)){
                    continue
                }
                val name = values.getString(1)
                val description = values.getString(2)
                val icon = values.getInt(3)
                list.add(Category(name = name, description = description, icon = icon, type = type))
            } while (values.moveToNext())
        }
        values.close()
        db.close()
        return list
    }

}