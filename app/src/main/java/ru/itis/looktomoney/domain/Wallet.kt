package ru.itis.looktomoney.domain

class Wallet {
    var name : String = ""
    var numb : Int = 0

    constructor()

    constructor(numb: Int, name: String) {
        this.numb = numb
        this.name = name
    }

    override fun toString(): String {
        return "Wallet(name='$name', numb=$numb)"
    }

}