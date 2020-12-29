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
import org.junit.jupiter.api.Test

private val log = KotlinLogging.logger {}

class Day1Test {

    @Test
    fun `Part One`() {
        val resultingFrequency = parseFrequencyChanges().sum()
        log.info { "Part One: $resultingFrequency" }
    }

    @Test
    fun `Part Two`() {
        val visited = mutableSetOf<Int>()
        val firstDup = generateSequence { parseFrequencyChanges() }
            .flatten()
            .runningReduce { acc, i -> acc + i }
            .first { !visited.add(it) }

        log.info { "Part Two: $firstDup" }
    }

    private fun parseFrequencyChanges() = readLines("/Day1.txt")
        .map { it.toInt() }
}