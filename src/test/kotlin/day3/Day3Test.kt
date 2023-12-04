package day3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import util.readInput

class Day3Test {

    @Test
    fun `part 1 - example input`() {
        val input = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..
        """.trimIndent()

        val result = getResult(
            input.lines(), false
        )

        assertThat(result).isEqualTo(4361)
    }

    @Test
    fun `part 1 - full input`() {
        val input = readInput(3)

        val result = getResult(
            input.lines(), false
        )

        assertThat(result).isEqualTo(527446)
    }

    @Test
    fun `part 2 - example input`() {
        val input = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..
        """.trimIndent()

        val result = getResult(
            input.lines(), true
        )

        assertThat(result).isEqualTo(4361)
    }
}
