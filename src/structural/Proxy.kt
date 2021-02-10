package structural

/**
 * Proxy
 *
 * Proxy is a structural design pattern that lets you provide a substitute or placeholder for another object. A proxy controls access to the original object,
 * allowing you to perform something either before or after the request gets through to the original object.
 */

interface File {
    fun read(name: String)
}

class NormalFile : File {
    override fun read(name: String) = println("Reading file: $name")
}

class SecuredFile : File {
    private val normalFile = NormalFile()
    var password: String = ""

    override fun read(name: String) {
        if (password == "secret") {
            println("Password is correct: $password")
            normalFile.read(name)
        } else {
            println("Incorrect password. Access denied!")
        }
    }
}

fun main(){
    val securedFile = SecuredFile()
    securedFile.read("readme.md")

    securedFile.password = "secret"
    securedFile.read("readme.md")
}