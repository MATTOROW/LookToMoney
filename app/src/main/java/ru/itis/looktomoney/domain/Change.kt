package ru.itis.looktomoney.domain

class Change {
    var numb : Int = 0
    var category : Category? = null
    var wallet : Wallet? = null

    constructor()

    constructor(int: Int, category: Category, wallet: Wallet){
        numb = int
        this.category = category
        this.wallet = wallet
    }

    override fun toString(): String {
        return "Change(numb=$numb, category=$category, wallet=$wallet)"
    }


}