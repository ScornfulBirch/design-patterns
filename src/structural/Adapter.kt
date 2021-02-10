package structural

/**
 * Adapter
 *
 * Adapter is a structural design pattern, which allows incompatible objects to collaborate.
 * It catches calls for one object and transforms them to format and interface recognizable by the second object.
 */

interface Temperature {
    var temperature: Double
}

class CelsiusTemperature(override var temperature: Double) : Temperature

class FahrenheitTemperature(var celsiusTemperature: CelsiusTemperature) : Temperature {

    override var temperature: Double
        get() = celsiusToFahrenheit(celsiusTemperature.temperature)
        set(value) {
            celsiusTemperature.temperature = fahrenheitToCelsius(value)
        }

    private fun fahrenheitToCelsius(f: Double): Double = (f - 32) * 5 / 9

    private fun celsiusToFahrenheit(c: Double): Double = (c * 9 / 5) + 32

}

fun main() {
    val celsiusTemperature = CelsiusTemperature(0.0)
    val fahrenheitTemperature = FahrenheitTemperature(celsiusTemperature)

    celsiusTemperature.temperature = 36.8
    println("${celsiusTemperature.temperature} C -> ${fahrenheitTemperature.temperature} F")

    fahrenheitTemperature.temperature = 98.24
    println("${fahrenheitTemperature.temperature} F -> ${celsiusTemperature.temperature} C")
}
