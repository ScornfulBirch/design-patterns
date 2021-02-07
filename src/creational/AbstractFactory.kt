package creational

/**
 * Abstract Factory
 *
 * Abstract Factory is a creational design pattern that lets you produce families of related objects
 * without specifying their concrete classes.
 */

interface Text {
    fun paint()
}

interface Button {
    fun paint()
}

interface PaintFactory {
    fun makeText(): Text
    fun makeButton(): Button
}

class LinuxText : Text {
    override fun paint() = println("You have created LinuxText.")
}

class WindowsText : Text {
    override fun paint() = println("You have created WindowsText.")
}

class LinuxButton : Button {
    override fun paint() = println("You have created LinuxButton.")
}

class WindowsButton : Button {
    override fun paint() = println("You have created WindowsButton.")
}

class LinuxPaintFactory : PaintFactory {
    override fun makeText(): Text = LinuxText()
    override fun makeButton(): Button = LinuxButton()
}

class WindowsPaintFactory : PaintFactory {
    override fun makeText(): Text = WindowsText()
    override fun makeButton(): Button = WindowsButton()
}

class Application(paintFactory: PaintFactory) {

    private val text = paintFactory.makeText()
    private val button = paintFactory.makeButton()

    fun paint() {
        text.paint()
        button.paint()
    }
}

fun main() {
    Application(LinuxPaintFactory()).paint()
    Application(WindowsPaintFactory()).paint()
}