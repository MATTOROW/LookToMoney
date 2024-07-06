package ru.itis.looktomoney.domain

class Wallet {
    var name : String = ""
    var numb : Int = 0
    var icon : Int = 0

    constructor()

    constructor(numb: Int, name: String, icon : Int) {
        this.numb = numb
        this.name = name
        this.icon = icon
    }

    override fun toString(): String {
        return "Wallet(name='$name', numb=$numb, icon=$icon)"
    }


}