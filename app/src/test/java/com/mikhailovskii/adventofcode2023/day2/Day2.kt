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

    @Test
    fun part2() {
        val input = readInput("day2/input2")
        val sum = input
            .map { it.replace(Regex("Game (\\d+): "), "") }
            .map { it ->
                var reds = 1
                var greens = 1
                var blues = 1
                it.split(";").forEach { it ->
                    val pairs = it.split(",")
                        .map { it.trim() }

                    for (it in pairs) {
                        val arr = it.split(" ")
                        val amount = arr[0].toInt()
                        when (arr[1]) {
                            "red" -> {
                                reds = maxOf(reds, amount)
                            }

                            "green" -> {
                                greens = maxOf(greens, amount)
                            }

                            "blue" -> {
                                blues = maxOf(blues, amount)
                            }
                        }
                    }
                }
                reds * greens * blues
            }.sum()
        println(sum)
    }
}