package structural

/**
 * Composite
 *
 * Composite is a structural design pattern that lets you compose objects into tree structures and
 * then work with these structures as if they were individual objects
 */

open class Equipment(private var price: Int, private var name: String) {
    open fun getPrice(): Int = price
}

open class Composite(name: String) : Equipment(0, name) {

    private val equipments = ArrayList<Equipment>()

    fun add(vararg equipment: Equipment) {
        equipments.addAll(equipment)
    }

    override fun getPrice(): Int {
        return equipments.map { it.getPrice() }.sum()
    }
}

class Cabinet : Composite("cabbinet")
class FloppyDisk : Equipment(70, "Floppy Disk")
class HardDrive : Equipment(250, "Hard Drive")
class Memory : Equipment(280, "Memory")


fun main() {
    val cabinet = Cabinet().apply {
        add(FloppyDisk(), HardDrive(), Memory())
    }

    println(cabinet.getPrice())
}