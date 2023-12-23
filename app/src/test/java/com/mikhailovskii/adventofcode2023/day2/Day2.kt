package com.mikhailovskii.adventofcode2023.day2

import com.mikhailovskii.adventofcode2023.readInput
import org.junit.Test

class Day2 {
    @Test
    fun part1() {
        val input = readInput("day2/input1")
        val reds = 12
        val greens = 13
        val blues = 14
        var valid = 0
        input.forEachIndexed { index, it ->
            val game = it.replace(Regex("Game (\\d+): "), "")
            var isInvalid = false
            for (it in game.split(";")) {
                val pairs = it.split(",")
                    .map { it.trim() }

                for (it in pairs) {
                    val arr = it.split(" ")
                    val amount = arr[0].toInt()
                    val color = arr[1]
                    isInvalid =
                        (color == "red" && amount > reds) || (color == "green" && amount > greens) || (color == "blue" && amount > blues)
                    if (isInvalid) break
                }
                if (isInvalid) break
            }

            if (!isInvalid) {
                valid += index + 1
            }
        }
        println(valid)
    }
}