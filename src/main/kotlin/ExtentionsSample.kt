fun <T> T.println(): T {
    println("value :$this")
    println("hashCode:${this.hashCode()}")
    return this
}

fun main() {
    CharacterSealedWithParam.UGLY().println()
    val number2: Number = 2.println()
    println(number2)
}
