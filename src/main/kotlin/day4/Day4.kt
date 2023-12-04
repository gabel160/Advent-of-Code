package day4

import kotlin.math.pow

data class Card(val id: Int, val winningNumbers: Set<Int>, val myNumbers: Set<Int>)

fun parseCards(lines: List<String>, copiesMap: MutableMap<Int, Int>, totalMap: MutableMap<Int, Int>) : MutableList<Card> {
    val result = mutableListOf<Card>()
    for((index, line) in lines.withIndex()) {
        val numbers = line.split(":")[1]
        val splitNumbers = numbers.split("|")
        val splitWinningNumbers = splitNumbers[0].split(" ").filter(String::isNotEmpty)
        val splitMyNumbers = splitNumbers[1].split(" ").filter(String::isNotEmpty)
        val winningNumbers = splitWinningNumbers.map { it.toInt() }.toSet()
        val myNumbers = splitMyNumbers.map { it.toInt() }.toSet()
        val card = Card(index + 1, winningNumbers, myNumbers)
        copiesMap[index + 1] = 1
        totalMap[index + 1] = 1
        result.add(card)
    }
    return result
}

fun getNextCopies(card: Card, cards: MutableList<Card>, copiesMap: MutableMap<Int, Int>, totalMap: MutableMap<Int, Int>) {
    repeat(copiesMap[card.id]!!){
        val amount = matchingNumbersInCard(card)
        var index = card.id
        repeat(amount){
            index++
            if(index <= cards.size ){
                if(copiesMap[index] != null){
                    copiesMap[index] = copiesMap[index]!! + 1
                }
                if(totalMap[index] != null){
                    totalMap[index] = totalMap[index]!! + 1
                }
            }
        }
        copiesMap[card.id] = copiesMap[card.id]!! - 1
    }
}


fun matchingNumbersInCard(card: Card) : Int {
    var result = 0
    for (number in card.myNumbers){
        if(card.winningNumbers.contains(number)){
            result++
        }
    }
    return result
}

fun calculateMultiplication(int: Int) : Int{
    if(int == 0){
        return 0
    }
    return 2.toDouble().pow(int-1).toInt()
}

fun getResultPart2(lines: List<String>) : Int {
    val copiesMap = mutableMapOf<Int,Int>()
    val totalMap = mutableMapOf<Int,Int>()
    val cards = parseCards(lines, copiesMap, totalMap)
    var result = 0
    for(card in cards) {
        getNextCopies(card, cards, copiesMap, totalMap)
    }
    totalMap.forEach{ (id, amount) -> result+=amount}
    return result
}

fun getResult(lines: List<String>) : Int {
    val copiesMap = mutableMapOf<Int,Int>()
    val totalMap = mutableMapOf<Int,Int>()
    val cards = parseCards(lines, copiesMap, totalMap)
    var result = 0
    for (card in cards){
        result += calculateMultiplication(matchingNumbersInCard(card))
    }
    return result
}