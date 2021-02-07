package creational

/**
 * Factory Method
 *
 * Factory Method is a creational design pattern that provides an interface for creating objects in a superclass,
 * but allows subclasses to alter the type of objects that will be created.
 */

interface View {
    fun render()
}

class ButtonView : View {
    override fun render() = println("You have rendered ButtonView")
}

class TextView : View {
    override fun render() = println("You have rendered TextView")
}

abstract class Message {

    fun render() {
        getView().render()
    }

    abstract fun getView(): View
}

class TextMessage : Message() {
    override fun getView(): View = TextView()
}

class ButtonMessage : Message() {
    override fun getView(): View = ButtonView()
}

fun main() {
    TextMessage().render()
    ButtonMessage().render()
}