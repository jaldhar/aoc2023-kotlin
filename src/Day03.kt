// Advent of Code
// Solution by Jaldhar H. Vyas <jaldhar@braincells.com>
// Copyright (C) 2023, Consolidated Braincells Inc.  All rights reserved.
// "Do What Thou Wilt" shall be the whole of the license.

fun main() {
    fun adjacentSymbols(grid: MutableList<CharArray>, row: Int, col: Int): MutableList<String> {
        val deltas = listOf(
            Pair(-1,-1),
            Pair(-1, 0),
            Pair(-1, 1),
            Pair(0, -1),
            Pair(0,  1),
            Pair(1, -1),
            Pair(1,  0),
            Pair(1,  1),
        )
        val nonSymbolic = Regex("""\d|\.""")
        var neighbors = mutableListOf<String>()

        for (delta in deltas) {
            val posRow  = row + delta.first
            val posCol = col + delta.second

            if (posRow > 0 && posRow < grid.size &&
                posCol > 0 && posCol < grid[row].size &&
                ! nonSymbolic.matches(grid[posRow][posCol].toString())) {
                    neighbors.add("$posRow;$posCol")
            }
        }

        return neighbors
    }

    fun isSymbolAdjacent(grid: MutableList<CharArray>, row: Int, col: Int): Boolean {
        val deltas = listOf(
            Pair(-1,-1),
            Pair(-1, 0),
            Pair(-1, 1),
            Pair(0, -1),
            Pair(0,  1),
            Pair(1, -1),
            Pair(1,  0),
            Pair(1,  1),
        )
        val nonSymbolic = Regex("""\d|\.""")

        for (delta in deltas) {
            val posRow  = row + delta.first
            val posCol = col + delta.second

            if (posRow > 0 && posRow < grid.size &&
                posCol > 0 && posCol < grid[row].size &&
                ! nonSymbolic.matches(grid[posRow][posCol].toString())) {
                    return true
            }
        }

        return false
    }

    fun part1(input: List<String>): Int {
        var total = 0
        val grid = mutableListOf<CharArray>()

        for (line in input) {
            grid.add(line.toCharArray())
        }

        for (row in 0 .. grid.lastIndex) {
            // we need $skipUntil to workaround $col being immutable.
            var skipUntil = 0

            for (col in 0 .. grid[row].lastIndex) {
                if (col < skipUntil) {
                    continue
                }

                if (grid[row][col] in '0' .. '9') {
                    var digits = mutableListOf<Pair<Int, Int>>()
                    var i = col
                    while (i < grid[row].size && grid[row][i] in '0' .. '9') {
                        digits.add(Pair(row, i))
                        i++
                    }
                    skipUntil = i

                    if (digits.any { isSymbolAdjacent(grid, it.first, it.second) }) {
                        val part = digits
                            .map { grid[it.first][it.second]}
                            .joinToString("")
                            .toInt()
                        total += part
                    }
                }
            }
        }

        return total
    }

    fun part2(input: List<String>): Int {
        var total = 0
        val grid = mutableListOf<CharArray>()

        for (line in input) {
            grid.add(line.toCharArray())
        }

        var parts = mutableMapOf<String, MutableList<Int>>()

        for (row in 0 .. grid.lastIndex) {
            // we need $skipUntil to workaround $col being immutable.
            var skipUntil = 0

            for (col in 0 .. grid[row].lastIndex) {
                if (col < skipUntil) {
                    continue
                }

                if (grid[row][col] in '0' .. '9') {
                    var digits = mutableListOf<Pair<Int, Int>>()
                    var i = col
                    while (i < grid[row].size && grid[row][i] in '0' .. '9') {
                        digits.add(Pair(row, i))
                        i++
                    }
                    skipUntil = i

                    val part = digits
                        .map { grid[it.first][it.second]}
                        .joinToString("")
                        .toInt()

                    for (digit in digits) {
                        adjacentSymbols(grid, digit.first, digit.second).forEach {
                            // This seems way more complicated than it ought to be.
                            var v = parts.getOrDefault(it, mutableListOf<Int>())
                            v.add(part)
                            parts.put(it, v)
                        }
                    }
                }
            }
        }

        for (gear in parts.keys.filter { parts[it]!!.distinct().size == 2 }) {
            total += parts[gear]!!.distinct().reduce { acc, i -> acc * i }
        }

        return total
    }

    // test if implementation meets criteria from the description, like:
    // val testInput = readInput("Day03_test")
    // check(part1(testInput) == 8)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
