package com.mikhailovskii.adventofcode2023.day1

import com.mikhailovskii.adventofcode2023.findAllWithOverlap
import com.mikhailovskii.adventofcode2023.println
import com.mikhailovskii.adventofcode2023.readInput
import org.junit.Test

class Day1 {
    @Test
    fun part1() {
        val input = readInput("day1/input1")
        val result = input.sumOf {
            (it.first { it.isDigit() }.toString() + it.last { it.isDigit() }).toInt()
        }
        println(result)
    }

    @Test
    fun part2() {
        val input = readInput("day1/input2")
        val numbersInStr = arrayOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        val regex = "\\d|one|two|three|four|five|six|seven|eight|nine".toRegex()
        fun toNumber(string: String): Int {
            val index = numbersInStr.indexOf(string)
            return if (index == -1) string.toInt()
            else index + 1
        }
        input.sumOf {
            val all = regex.findAllWithOverlap(it)
            val first = all.first().value
            val last = all.last().value
            (toNumber(first) * 10 + toNumber(last)).apply {
                println()
            }
        }.println()
    }
}