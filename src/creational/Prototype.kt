package creational

/**
 * Prototype
 *
 * Prototype is a creational design pattern that allows cloning objects, even complex ones,
 * without coupling to their specific classes.
 */

class SimplePrototype(
    val value: String,
    val number: Int
) {

    fun copy(
        value: String = this.value,
        number: Int = this.number
    ): SimplePrototype = SimplePrototype(value, number)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SimplePrototype

        if (value != other.value) return false
        if (number != other.number) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + number
        return result
    }

}

data class DataPrototype(
    val value: String,
    val number: Int
)

class CloneablePrototype : Cloneable {

    private var result: String = ""

    fun copy(): CloneablePrototype {
        return (clone() as CloneablePrototype)
    }

    fun updateResult(someResult: String) {
        result = someResult
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CloneablePrototype

        if (result != other.result) return false

        return true
    }

    override fun hashCode(): Int {
        return result.hashCode()
    }

}


fun main() {
    val simplePrototype = SimplePrototype("", 2)
    val simplePrototypeCopy = simplePrototype.copy(number = 5)

    val dataPrototype = DataPrototype("", 2)
    val dataPrototypeCopy = dataPrototype.copy(number = 5)

    val cloneablePrototype = CloneablePrototype().apply { updateResult("result") }
    val cloneablePrototypeCopy = cloneablePrototype.copy()

    println(simplePrototype == simplePrototypeCopy)// should be false
    println(dataPrototype == dataPrototypeCopy) // should be false
    println(cloneablePrototype == cloneablePrototypeCopy) // should be true
}