// Enum Class

enum class CharacterEnum {
    GOOD, BAD, UGLY
}

enum class CharacterEnumWithParam(val secondName: String) {
    GOOD("Blondie") {

        override fun fire(): Unit {
            println("Bang Bang")
        }
    },
    BAD2("Angel Eyes") {

        override fun fire(): Unit {
            println("Bang Bang")
        }
    },
    UGLY("Tuco") {

        override fun fire(): Unit {
            println("Bang Bang")
        }
    };

    abstract fun fire(): Unit
}

//// Sealed Class
//A sealed class is abstract by itself, it cannot be instantiated directly and can have abstract members.
sealed class CharacterSealed {

    // Class yada obje olarak olusturuyoruz. Yani kendi instancelari var
    object GOOD : CharacterSealed()

    object BAD : CharacterSealed()

    object UGLY : CharacterSealed()
}

sealed class CharacterSealedWithParam(val secondNameOriginal: String) {

    class GOOD(internal val secondName: String) : CharacterSealedWithParam(secondName) {

        override fun fire() {
            println("Bang Bang")
        }
    }

    class BAD(secondName: String) : CharacterSealedWithParam(secondName) {

        override fun fire() {
            println("Bang Bang")
        }
    }

    //   class UGLY_ERROR("Tuco") :CharacterSealedWithParam("Tuco")
    class UGLY(secondName: String = "Tuco") : CharacterSealedWithParam("Tuco") {

        override fun fire() {
            println("Bang Bang")
        }
    }

    object OZAN

    abstract fun fire(): Unit

}

sealed class CharacterSealedWithType {
    class GOOD<T>(secondName: T) : CharacterSealedWithType()
    object BAD : CharacterSealedWithType()
    class UGLY<T> : CharacterSealedWithType()
}

fun main() {

    /// Val olarak olusturuyoruz instancelari yok classin instance indan turuyorlar. yani single instance oluyor her bir value
    CharacterEnumWithParam.BAD2.apply { fire() }
    println("Same HashCode")
    CharacterEnumWithParam.BAD2.println()
    CharacterEnumWithParam.BAD2.println()  //disaridan parametre veremiyoruz
    CharacterEnum.BAD

    println("//// Sealed")
    CharacterSealedWithParam.GOOD("Blondie").apply {
        secondNameOriginal.println()
        secondName.println()
        fire()
    }
    println("Different HashCode")
    CharacterSealedWithParam.GOOD("Blondie").println()
    CharacterSealedWithParam.GOOD("Blondie").println()
    println("Different HashCode")
    CharacterSealedWithParam.UGLY().println()
    CharacterSealedWithParam.UGLY().println()
    //        val characterSealedWithParamError = CharacterSealedWithParam.BAD() // Parametre istiyor
    CharacterSealed.GOOD.println()

    CharacterSealedWithType.GOOD<Number>(1).println()
    CharacterSealedWithType.GOOD<String>("Blondie").println()
    CharacterSealedWithType.UGLY<Unit>().println()

}

/// Real Example

sealed class Resource<T> {

    data class Success<T>(val data: T) : Resource<T>()

    data class Error<T>(val exception: Throwable) : Resource<T>()

    data class Loading<T>(val data: T? = null) : Resource<T>()

    class BAD<T> : Resource<T>()

    fun <R> mapData(transform: (T) -> R): Resource<R> = when (this) {
        is Success -> Success(
            transform(data)
        )
        is Error -> Error(
            exception
        )
        is Loading -> Loading(
            data?.let { transform(it) }
        )
        is BAD -> {
            BAD()
        }
        else -> {
            BAD()
        }
        // the `else` clause is not required because we've covered all the cases
    }
}
