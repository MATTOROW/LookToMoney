package ru.itis.looktomoney.domain

class Category {
    var name : String = ""
    var description : String = ""
    var icon : Int = 0
    var type : String = ""

    constructor()

    constructor(name : String, description : String, icon : Int, type : String){
        this.name = name
        this.description = description
        this.icon = icon
        this.type = type
    }

    override fun toString(): String {
        return "Category(name='$name', description='$description', icon='$icon', type='$type')"
    }
}