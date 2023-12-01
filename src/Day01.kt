// Advent of Code
// Solution by Jaldhar H. Vyas <jaldhar@braincells.com>
// Copyright (C) 2023, Consolidated Braincells Inc.  All rights reserved.
// "Do What Thou Wilt" shall be the whole of the license.

fun main() {
    fun part1(input: List<String>): Int {
        val re = Regex("""\d""")

        return input.sumOf {
            val matches = re.findAll(it).toList()
            (
                matches.first().value.orEmpty() +
                matches.last().value.orEmpty()
            )
            .toInt()
        }
    }

    fun part2(input: List<String>): Int {
        val re = Regex("""(?=(one|two|three|four|five|six|seven|eight|nine|\d))""")
        val conv = mapOf (
            "one"   to "1",
            "two"   to "2",
            "three" to "3",
            "four"  to "4",
            "five"  to "5",
            "six"   to "6",
            "seven" to "7",
            "eight" to "8",
            "nine"  to "9",
            "1"     to "1",
            "2"     to "2",
            "3"     to "3",
            "4"     to "4",
            "5"     to "5",
            "6"     to "6",
            "7"     to "7",
            "8"     to "8",
            "9"     to "9",
        )

        return input.sumOf {
            val matches = re.findAll(it).toList()
            (
                conv[matches.first().groupValues.get(1)].orEmpty() +
                conv[matches.last().groupValues.get(1)].orEmpty()
            )
            .toInt()
        }
    }

    // test if implementation meets criteria from the description, like:
    // val testInput = readInput("Day01_test")
    // check(part1(testInput) == 1)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
