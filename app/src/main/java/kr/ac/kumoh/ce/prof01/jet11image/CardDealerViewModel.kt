package kr.ac.kumoh.ce.prof01.jet11image

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class CardDealerViewModel : ViewModel() {
    private val _cards = mutableStateListOf<Int>(-1, -1, -1, -1, -1)
    //val cards: SnapshotStateList<Int>
    val cards: List<Int> = _cards

    fun shuffleCards() {
        _cards.clear()
        repeat(5) {
            var num: Int
            do {
                num = (0 until 52).random()
            } while (_cards.contains(num))
            _cards.add(num)
        }
        _cards.sort()
    }

    fun getCardName(c: Int): String {
        if (c < 0)
            return "c_red_joker"

        val shape = when (c / 13) {
            0 -> "spades"
            1 -> "diamonds"
            2 -> "hearts"
            3 -> "clubs"
            else -> return "c_red_joker"
        }

        val number = when (c % 13) {
            0 -> "ace"
            in 1..9 -> (c % 13 + 1).toString()
            10 -> "jack"
            11 -> "queen"
            12 -> "king"
            else -> return "c_red_joker"
        }

        return if (c % 13 in 10..12)
            "c_${number}_of_${shape}2"
        else
            "c_${number}_of_${shape}"
    }
}