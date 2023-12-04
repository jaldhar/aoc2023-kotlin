// Advent of Code
// Solution by Jaldhar H. Vyas <jaldhar@braincells.com>
// Copyright (C) 2023, Consolidated Braincells Inc.  All rights reserved.
// "Do What Thou Wilt" shall be the whole of the license.

fun main() {
    fun makeMatches(input: List<String>): List<Int> {
        val whitespace = Regex("""\s+""")
        var matches = mutableListOf<Int>()

        for (line in input) {
            val (winning, having) = line.substringAfter(":").split('|')
            val winningNumbers = winning.split(whitespace).filter { it.isNotEmpty() }
            val havingNumbers = having.split(whitespace).filter { it.isNotEmpty() }
            matches.add((winningNumbers intersect havingNumbers).size)
        }

        return matches.toList()
    }

    fun part1(input: List<String>): Int {
        
        return makeMatches(input)
          .map { Math.pow(2.toDouble(), (it - 1).toDouble()).toInt()}
          .sum()
    }

    fun part2(input: List<String>): Int {
        val matches = makeMatches(input)
        val scratchcards = MutableList<Int>(matches.size) { 1 }
        val end = scratchcards.size

        for (key in 0 .. scratchcards.lastIndex) {
            for (i in (key + 1) .. (key + matches[key])) {
                if (i >= end) {
                    break
                }

                scratchcards[i] += scratchcards[key]
            }
        }
        return scratchcards.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part2(testInput) == 30)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
