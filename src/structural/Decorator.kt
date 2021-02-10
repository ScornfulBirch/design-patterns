package structural


/**
 * Decorator
 *
 * Decorator is a structural design pattern that lets you attach new behaviors
 * to objects by placing these objects inside special wrapper objects that contain the behaviors.
 */

interface DataSource {
    fun readData(): String
}

class FileDataSource(private val name: String) : DataSource {
    override fun readData(): String = "Data from: $name"
}

open class DataSourceDecorator(private val wrappee: DataSource) : DataSource {
    override fun readData(): String {
        return wrappee.readData()
    }
}

class EncryptionDecorator(source: DataSource) : DataSourceDecorator(source) {

    override fun readData(): String {
        return decode(super.readData())
    }

    private fun decode(data: String): String {
        println("Decode...")
        val result: ByteArray = data.toByteArray()
        return String(result)
    }
}

fun main() {
    val fileDataSource: DataSource = FileDataSource("out/demo.txt")
    val encryptDataSource: DataSource = EncryptionDecorator(FileDataSource("out/demo.txt"))
    println(fileDataSource.readData())
    println(encryptDataSource.readData())
}
