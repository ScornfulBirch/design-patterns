package structural

/**
 * Bridge
 *
 * Bridge is a structural design pattern that lets you split a large class or a set of closely related classes
 * into two separate hierarchies—abstraction and implementation—which can be developed independently of each other.
 */

interface ImageLoader {

    fun load(): String
}

class Glide : ImageLoader {
    override fun load(): String = "Image loaded by glide"
}

class Picasso : ImageLoader {
    override fun load(): String = "Image loaded by glide"
}

abstract class View(val loader: ImageLoader) {
    abstract fun draw()
}

class ImageView(loader: ImageLoader) : View(loader) {
    override fun draw() = println("ImageView drawn" + loader.load())
}

class GifView(loader: ImageLoader) : View(loader) {
    override fun draw() = println("GifView drawn" + loader.load())
}

fun main() {
    ImageView(Picasso()).draw()
    GifView(Glide()).draw()
}