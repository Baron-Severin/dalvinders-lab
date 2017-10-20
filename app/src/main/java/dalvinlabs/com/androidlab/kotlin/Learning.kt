package dalvinlabs.com.androidlab.kotlin


/*
    1. If statements and comparison operators are same as JAVA.
    2. Companion object is required for static methods.
    3. Var is mutable
    4. Val is immutable.

 */
class Learning {

    companion object {
        fun variables() {
            // ";" is not required to terminate statement.
            // Mutable variable, type is decided by compiler based upon value assigned.
            // i.e. INT in this case
            var one = 123
            println("data = " + one)
            one = 789
            println("data = " + one)

            // Explicit type declaration
            var two: String = "alpha"
            println("data = " + two)
            two = "beta"
            println("data = " + two)

            // Immutable variable i.e. final in java
            val three = 123;
            println("data = " + three)
            // Following is not possible
            //three = 789

            // String interpolation is done through  "$"
            println("This is an interpolation one=$one with two=$two")
        }

        fun math() {
            // By default any thing with decimal is double
            val pi = 3.14
            val data = 2
            val outputDouble = pi * data
            // By default data will be converted to double hence output will be double
            println("outputDouble = " + outputDouble)

            // Explicit conversion
            val outputInt: Int
            outputInt = pi.toInt() * data
            println("outputInt = " + outputInt)
        }

        fun lists() {
            var data = listOf<Int>(1, 2, 3, 4, 5)
            println("data = " + data)
            // By default list is immutable, mutable list is required for modifications
            data = mutableListOf<Int>(1, 2, 3, 4, 5)
            data.add(6)
            println("data = " + data)
            // List can be used as an array
            println("data at index 0 = " + data[0])
        }

        fun forLoop() {
            for (i in 1..10) {
                println("i = " + i)
            }
            var data = listOf<String>("Foo", "Bar", "Alpha", "Beta")
            for (i in data) {
                println(i)
            }
        }

        fun map() {
            // No need to explicitly provide data type in <>
            var data = mapOf("foo" to 123, "bar" to 456, "alpha" to 789)
            // Access map
            println(data["bar"])

            for (i in data) {
                println(i)
            }

            // In order to modify map it has to be mutable
            data = mutableMapOf("foo" to 123, "bar" to 456, "alpha" to 789)
            data["beta"] = 0
            for (i in data) {
                println(i)
            }
        }

        fun functions() {

            // Return value
            fun one(): String {
                return "alpha"
            }

            // Parameter and return value
            fun two(name: String): String {
                return "Hello $name"
            }

            // Parameter with default value
            fun three(name: String = "Default"): String {
                return "Hello $name"
            }

            // Parameter with default value
            fun four(name: String = "Default", value: Int): String {
                return "Hello $name and $value"
            }

            fun five(a: Int, b: Int) = a + b

            println(one())

            println(two("Panda"))

            println(three())

            println(four("Dog", 5))

            // Invoke the method with default value with argument name
            println(four(value = 10))

            println(five(2, 3))
        }

        fun classes() {

            // Class One with properties msg and value with initial values assigned as well.
            class One(msg: String = "default", value: Int = 1)

            var one = One("hello", 5)
            // TODO: why one.msg and one.value is not accessible
            // It required var/val in primary constructor to expose properties
            println(one.toString())

            // Traditional way
            class Two {
                var msg: String = "hello"
                var value: Int = 1
            }

            var two = Two()
            println("two msg = ${two.msg}")
            println("two value = ${two.value}")

            // With init
            class Three {
                var msg: String
                var value: Int

                init {
                    msg = "Hello from init"
                    value = 1
                }
            }

            var three = Three()
            println("three msg = ${three.msg}")
            println("three value = ${three.value}")

            // With constructors
            class Four {
                var msg: String
                var value: Int

                constructor(msg: String, value: Int) {
                    this.msg = msg
                    this.value = value
                }

                constructor() {
                    this.msg = "Default msg"
                    this.value = 1
                }
            }

            var defaultFour = Four()
            println("four msg = ${defaultFour.msg}")
            println("four value = ${defaultFour.value}")

            var four = Four("Hello", 10)
            println("four msg = ${four.msg}")
            println("four value = ${four.value}")
        }
    }
}