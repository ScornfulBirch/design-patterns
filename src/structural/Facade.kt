package structural

/**
 * Facade
 *
 * Facade is a structural design pattern that provides a simplified interface to a library,
 * a framework, or any other complex set of classes.
 */

class ComplexSystemStore(private val filePath: String) {

    init {
        println("Reading data from file: $filePath")
    }

    private val store = HashMap<String, String>()

    fun store(key: String, payload: String) {
        store[key] = payload
    }

    fun read(key: String): String {
        return store[key] ?: ""
    }

    fun commit() = println("Storing cached data: $store to file: $filePath")
}

data class User(val login: String)

// Facade
class UserRepository {
    private val systemPreferences = ComplexSystemStore("path/to/data/default.prefs")

    fun save(user: User) {
        systemPreferences.store("USER_KEY", user.login)
        systemPreferences.commit()
    }

    fun findFirst(): User {
        val data: String = systemPreferences.read("USER_KEY")
        return User(data)
    }

}

fun main() {
    val user = User("username")
    val userRepository = UserRepository()
    userRepository.save(user)
    val resultUser = userRepository.findFirst()
    println("Found stored user: $resultUser")
}