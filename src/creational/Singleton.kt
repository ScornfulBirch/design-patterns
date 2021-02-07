package creational

/**
 * Singleton
 *
 * Singleton is a creational design pattern that lets you ensure that a class has only one instance,
 * while providing a global access point to this instance.
 */

class LazySingleton private constructor() {

    companion object {

        private var instance: LazySingleton? = null

        @JvmStatic
        fun getInstance(): LazySingleton {
            if (instance == null) {
                instance = LazySingleton()
            }

            return instance as LazySingleton
        }
    }

}

class ThreadSafeSingleton private constructor() {

    companion object {

        @Volatile
        private var instance: ThreadSafeSingleton? = null

        @JvmStatic
        fun getInstance(): ThreadSafeSingleton {
            // Double-checked locking (DCL)
            // To prevent race condition between multiple threads that may
            // attempt to get singleton instance at the same time, creating separate
            // instances as a result.

            val result = instance
            if (result != null) {
                return result
            }

            synchronized(ThreadSafeSingleton::class) {
                if (instance == null) {
                    instance = ThreadSafeSingleton()
                }
                return instance as ThreadSafeSingleton
            }
        }
    }

}

// public final class KotlinSingleton {
//    @NotNull
//    public static final KotlinSingleton INSTANCE;
//
//    private KotlinSingleton() {
//    }
//
//    static {
//        KotlinSingleton var0 = new KotlinSingleton();
//        INSTANCE = var0;
//    }
// }
object KotlinSingleton


