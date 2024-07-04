package ru.itis.looktomoney.domain

class Change {
    var numb : Int = 0
    var category : Category? = null

    constructor()

    constructor(int: Int, category: Category){
        numb = int
        this.category = category
    }

    override fun toString(): String {
        return "Change(numb=$numb, category=$category)"
    }
}