package creational

import java.awt.Color

/**
 * Builder
 *
 * Builder is a creational design pattern, which allows constructing complex objects step by step.
 */

class FoodOrder private constructor(
    val bread: String?,
    val condiments: String?,
    val meat: String?,
    val fish: String?
) {

    fun printOrder() {
        val productsToOrder = arrayOf(bread, condiments, meat, fish).filterNotNull()
        productsToOrder.joinToString(separator = ", ").forEach(::print)
    }

    class Builder {

        private var bread: String? = null
        private var condiments: String? = null
        private var meat: String? = null
        private var fish: String? = null

        fun bread(bread: String) = apply { this.bread = bread }
        fun condiments(condiments: String) = apply { this.condiments = condiments }
        fun meat(meat: String) = apply { this.meat = meat }
        fun fish(fish: String) = apply { this.fish = fish }

        fun build() = FoodOrder(bread, condiments, meat, fish)
    }
}

class ImageRequest private constructor(
    val transformOptions: TransformOptions?,
    val url: String
) {

    data class TransformOptions(
        var size: Pair<Int, Int>? = null,
        var tint: Color? = null
    ) {
        fun size(width: Int, height: Int) = apply { this.size = Pair(width, height) }
        fun size(size: Int) = apply { size(size, size) }
        fun tint(tint: Color) = apply { this.tint = tint }
    }

    class Builder(private val url: String) {

        private var transformOptions: TransformOptions? = null

        fun transform(init: TransformOptions.() -> TransformOptions) {
            transformOptions = TransformOptions().apply { init() }
        }

        fun build(): ImageRequest = ImageRequest(transformOptions, url)
    }

    fun perform() = println("$url with options: $transformOptions")

}

fun imageRequest(url: String, init: ImageRequest.Builder.() -> Unit = {}): ImageRequest {
    return ImageRequest.Builder(url).apply(init).build()
}

fun main() {
    val order = FoodOrder.Builder()
        .meat("Fresh")
        .bread("Bread")
        .build()

    order.printOrder()

    println()

    val request = imageRequest("some/path/to/image.jpg") {
        transform {
            size(60)
            tint(Color.GREEN)
        }
    }

    request.perform()
}