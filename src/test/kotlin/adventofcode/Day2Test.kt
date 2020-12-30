/*
 * Copyright (c) 2020 James Carman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package adventofcode

import adventofcode.io.Resources.readLines
import mu.KotlinLogging
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.math.absoluteValue

private val log = KotlinLogging.logger {}

class Day2Test {

    @Test
    fun `Part One`() {
        val counts: List<Set<Int>> = readLines("/Day2.txt")
            .map { it.groupingBy { c -> c }.eachCount().values.toSet() }
        val twos = counts
            .filter { it.contains(2) }
            .count()
        val threes = counts
            .filter { it.contains(3) }
            .count()
        val checksum = twos * threes
        assertThat(twos * threes).isEqualTo(5681)
        log.info { "Part One: $checksum" }
    }

    @Test
    fun `Part Two`() {
        val answer = readLines("/Day2.txt")
            .sorted()
            .zipWithNext()
            .filter { isOffByOneCharacter(it) }
            .map { commonLetters(it) }
            .first()

        assertThat(answer).isEqualTo("uqyoeizfvmbistpkgnocjtwld")
        log.info { "Part Two: $answer" }
    }

    private fun isOffByOneCharacter(it: Pair<String, String>) = distance(it) == 1

    private fun commonLetters(strings: Pair<String, String>): String = characterPairsOf(strings)
        .filter { it.first == it.second }
        .map { it.first }
        .joinToString(separator = "")

    private fun distance(strings: Pair<String, String>): Int = characterPairsOf(strings)
        .map { it.first - it.second }
        .map { it.absoluteValue }
        .sum()

    private fun characterPairsOf(strings: Pair<String, String>) = strings.first.zip(strings.second)
}