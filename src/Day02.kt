// Advent of Code
// Solution by Jaldhar H. Vyas <jaldhar@braincells.com>
// Copyright (C) 2023, Consolidated Braincells Inc.  All rights reserved.
// "Do What Thou Wilt" shall be the whole of the license.

fun main() {
    fun part1(input: List<String>): Int {
        val maxRed = 12
        val maxGreen = 13
        val maxBlue = 14
        var total = 0

        line@ for (line in input) {
            val matches = Regex("""^Game (\d+)\:""").findAll(line).toList()
            val id = matches.first().groupValues.get(1).toInt()

            var rounds = line.substringAfter(": ").split("; ")

            for (round in rounds) {
                val cubes = round.split(", ")

                for (cube in cubes) {
                    val (quantity, color) = cube.split(" ")
                    if (color == "red" && quantity.toInt() > maxRed ||
                        color == "green" && quantity.toInt() > maxGreen ||
                        color == "blue" && quantity.toInt() > maxBlue) {
                            continue@line
                    }
                }
            }

            total += id
        }
        return total
    }

    fun part2(input: List<String>): Int {
        var total = 0

        for (line in input) {
            var maxRed = 0
            var maxGreen = 0
            var maxBlue = 0

            var rounds = line.substringAfter(": ").split("; ")

            for (round in rounds) {
                val cubes = round.split(", ")

                for (cube in cubes) {
                    val (quantity, color) = cube.split(" ")

                    if (color == "red" && quantity.toInt() > maxRed) {
                        maxRed = quantity.toInt()
                    }

                    if (color == "green" && quantity.toInt() > maxGreen) {
                        maxGreen = quantity.toInt()
                    }

                    if (color == "blue" && quantity.toInt() > maxBlue) {
                        maxBlue = quantity.toInt()
                    }
                }
            }

            val power = maxRed * maxGreen * maxBlue
            total += power
        }

        return total
    }

    // test if implementation meets criteria from the description, like:
    // val testInput = readInput("Day02_test")
    // check(part1(testInput) == 8)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
