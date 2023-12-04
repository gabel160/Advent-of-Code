package day3

import kotlin.math.truncate

data class Game(var up: String, var mid: String, var down: String)

data class GameSet(val set: ArrayList<Game>)

fun mapToGameSet(lines: List<String>) : GameSet{
    val result = arrayListOf<Game>()

    for(i in lines.indices){
        val game = Game("", "", "")
        if(i != 0){
            game.up = (lines[i-1])
        }
        game.mid = (lines[i])
        if(i+1 != lines.size){
            game.down = (lines[i+1])
        }
        result.add(game)
    }
    return GameSet(result)
}

fun checkGameReturnValidNumbers(game: Game, gear: Boolean) : MutableList<Int> {
    var numbers = mutableListOf<IntRange>()
    if(!gear) numbers = getNumberIndex(game.mid)
    if(gear) numbers = getGearIndex(game.mid)
    val checkSymbolRegex = "[^0-9.]".toRegex()
    val checkGearRegex = "\\d+".toRegex()

    var matchresult = mutableListOf<Int>()
    for(number in numbers){
        var match = false
        var upSubstring = ""
        var midSubString = ""
        var downSubString = ""
        var multiplication = 0


//        if(number.first-1 == -1){
//            if(game.up.length != 0){
//                upSubstring = game.up.substring(0, number.last + 2)
//            }
//            midSubString = game.mid.substring(0, number.last + 2)
//            if(game.down.length != 0){
//                downSubString = game.down.substring(0, number.last + 2)
//            }
//        } else if (number.last + 2 > game.mid.length){
//            if(game.up.length != 0){
//                upSubstring = game.up.substring(number.first - 1, number.last -1)
//            }
//            midSubString = game.mid.substring(number.first - 1, number.last -1)
//            if(game.down.length != 0){
//                downSubString = game.down.substring(number.first - 1, number.last -1)
//            }
//        } else {
        upSubstring = game.up.substring(number.first - 1, number.last + 2)
        midSubString = game.mid.substring(number.first - 1, number.last + 2)
        downSubString = game.down.substring(number.first - 1, number.last + 2)

        }
        if(!gear){
            if(checkSymbolRegex.find(upSubstring) != null) match = true
            if(checkSymbolRegex.find(midSubString) != null) match = true
            if(checkSymbolRegex.find(downSubString) != null) match = true
        } else {
            var count = 0
            val upMatch = checkGearRegex.findAll(upSubstring)
            val midMatch = checkGearRegex.findAll(midSubString)
            val downMatch = checkGearRegex.findAll(downSubString)
            count += upMatch.count()
            count += midMatch.count()
            count += downMatch.count()
            if(count == 2) {
                match = true
                for (submatch in upMatch){
                    if(multiplication == 0) multiplication = extractNumber(game.up , number)
                    else multiplication *= extractNumber(game.up ,number)
                }
                for (submatch in midMatch){
                    if(multiplication == 0) multiplication = extractNumber(game.mid ,number)
                    else multiplication *= extractNumber(game.mid ,number)
                }
                for (submatch in downMatch){
                    if(multiplication == 0) multiplication = extractNumber(game.down ,number)
                    else multiplication *= extractNumber(game.down ,number)
                }
            }
        }

        if(match) {
            if(!gear) matchresult.add(game.mid.substring(number).toInt())
            else matchresult.add(multiplication)

        }
    }
    return matchresult
}

fun extractNumber(text: String, index: IntRange) : Int {
    var startIndex = index.start
    while (startIndex > 0 && text[startIndex - 1].isDigit()) {
        startIndex--
    }
    var endIndex = index.endInclusive
    while (endIndex < text.length && text[endIndex].isDigit()) {
        endIndex++
    }
    val numberSubstring = text.substring(startIndex, endIndex)
    return numberSubstring.toInt()
}

fun getNumberIndex(text: String) : MutableList<IntRange> {
    val result = mutableListOf<IntRange>()
    val regex = "\\d+".toRegex()
    val matches = regex.findAll(text)
    for(match in matches){
        result.add(match.range)
    }
    return result
}

fun getGearIndex(text:String) : MutableList<IntRange> {
    val result = mutableListOf<IntRange>()
    val regex = "\\*".toRegex()
    val matches = regex.findAll(text)
    for(match in matches){
        result.add(match.range)
    }
    return result
}

fun addAllGames(gameset: GameSet, part2: Boolean) : Int {
    var resultlist = mutableListOf<Int>()
    for (game in gameset.set){
        resultlist.addAll(checkGameReturnValidNumbers(game, part2))
    }
    var result = 0
    for (number in resultlist) {
        result += number
    }
    return result
}

fun getResult(lines: List<String>, part2: Boolean) : Int {
    val gameset = mapToGameSet(lines)

    return addAllGames(gameset, part2)
}
