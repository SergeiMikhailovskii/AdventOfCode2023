package com.mikhailovskii.adventofcode2023.day3

import com.mikhailovskii.adventofcode2023.readInput
import org.junit.Test
import kotlin.math.max
import kotlin.math.min

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

    @Test
    fun part2() {
        val input = readInput("day3/input2")

        data class NumberWithCoords(
            val start: Int,
            val end: Int,
            val num: Int
        )

        fun findNumberByCoords(i: Int, j: Int): NumberWithCoords {
            var x = j
            while (x >= 0) {
                if (input[i][x].isDigit()) {
                    x--
                } else {
                    break
                }
            }
            val start = x
            x = j + 1
            while (x < input[i].length) {
                if (input[i][x].isDigit()) {
                    x++
                } else {
                    break
                }
            }
            val end = x
            var numStr = ""
            for (k in start + 1..<end) {
                numStr += input[i][k]
            }
            return NumberWithCoords(start, end, numStr.toInt())
        }

        fun findAllNumbersNearMultiply(i: Int, j: Int): List<Int> {
            val numbers = mutableSetOf<NumberWithCoords>()
            for (y in max(0, i - 1)..min(i + 1, input.size - 1)) {
                for (x in max(0, j - 1)..min(j + 1, input[i].length - 1)) {
                    if (input[y][x].isDigit()) {
                        numbers.add(findNumberByCoords(y, x))
                    }
                }
            }
            return numbers.map { it.num }.toList()
        }

        var totalSum = 0

        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] == '*') {
                    val nums = findAllNumbersNearMultiply(i, j)
                    if (nums.size == 2) {
                        totalSum += nums.fold(1) { acc, it ->
                            acc * it
                        }
                    }
                }
            }
        }

        println(totalSum)
    }
}