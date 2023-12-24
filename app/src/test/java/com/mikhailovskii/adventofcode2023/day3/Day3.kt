package com.mikhailovskii.adventofcode2023.day3

import com.mikhailovskii.adventofcode2023.readInput
import org.junit.Test

class Day3 {
    private val Char.isValidSymbol: Boolean
        get() = this != '.' && !this.isDigit()

    @Test
    fun part1() {
        val input = readInput("day3/input1")
        var sum = 0
        input.forEachIndexed { line, s ->
            var activeNumber = ""
            var isValidNumber = false
            s.forEachIndexed { index, c ->
                if (c.isDigit()) {
                    if (((index - 1) > 0 && s[index - 1].isValidSymbol)
                        || ((index + 1) < s.length && s[index + 1].isValidSymbol)

                        || ((line - 1) > 0 && input[line - 1][index].isValidSymbol)
                        || (((line - 1) > 0) && (index - 1 > 0) && input[line - 1][index - 1].isValidSymbol)
                        || (((line - 1) > 0) && (index + 1 < s.length) && input[line - 1][index + 1].isValidSymbol)

                        || ((line + 1) < input.size && input[line + 1][index].isValidSymbol)
                        || (((line + 1) < input.size) && (index - 1 > 0) && input[line + 1][index - 1].isValidSymbol)
                        || (((line + 1) < input.size) && (index + 1 < s.length) && input[line + 1][index + 1].isValidSymbol)
                    ) {
                        isValidNumber = true
                    }
                    activeNumber += c
                    if (index == s.length - 1 && activeNumber.isNotEmpty() && isValidNumber) {
                        sum += activeNumber.toInt()
                    }
                } else {
                    if (activeNumber.isNotEmpty() && isValidNumber) {
                        sum += activeNumber.toInt()
                    }
                    activeNumber = ""
                    isValidNumber = false
                }
            }
        }
        println(sum)
    }
}