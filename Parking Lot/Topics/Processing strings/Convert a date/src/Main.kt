fun main() {
    // write your code here

    val input = readln()

    val splittedInput = input.split("-").toMutableList()

    val yearsDate = splittedInput.first()

    splittedInput.removeFirst()

    splittedInput.add(splittedInput.lastIndex + 1, yearsDate)

    print(splittedInput.joinToString("/"))
}