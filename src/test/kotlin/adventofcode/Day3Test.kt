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

import adventofcode.fabric.Claim
import adventofcode.io.Resources.readLines
import mu.KotlinLogging
import org.junit.jupiter.api.Test

private val log = KotlinLogging.logger {}

class Day3Test {
    @Test
    fun `Part One`() {
        val count = readLines("/Day3.txt")
            .map(this::parseClaim)
            .flatMap(Claim::points)
            .groupingBy { it }
            .eachCount()
            .filterValues { it > 1 }
            .count()
        log.info { "Part One: $count" }
    }

    @Test
    fun `Part Two`() {
        val claims = readLines("/Day3.txt")
            .map { line -> parseClaim(line) }

        val pointCounts = claims
            .flatMap(Claim::points)
            .groupingBy { it }
            .eachCount()

        val id = claims
            .first { it.points().all { point -> pointCounts[point] == 1 } }
            .id

        log.info { "Part Two: $id" }
    }

    fun parseClaim(input: String): Claim {
        val regex = """#(\d+) @ (\d+),(\d+): (\d+)x(\d+)""".toRegex()
        return regex.matchEntire(input)
            ?.destructured
            ?.let { (id, x, y, width, height) ->
                Claim(
                    id.toInt(),
                    x.toInt(),
                    y.toInt(),
                    width.toInt(),
                    height.toInt()
                )
            }
            ?: throw IllegalArgumentException("Bad input '$input!'")
    }
}