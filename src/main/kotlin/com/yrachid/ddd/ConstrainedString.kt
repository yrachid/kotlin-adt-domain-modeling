package com.yrachid.ddd

typealias Violations = List<ConstrainedString.Violation>

sealed class ConstrainedString {
    data class LettersOnly private constructor(val value: String) : ConstrainedString() {
        companion object : AlphabeticalString<LettersOnly>(::LettersOnly)
    }

    data class Digits9 private constructor(val value: String) : ConstrainedString() {
        companion object : LimitedLengthDigit<Digits9>(maxLength = 9, creator = ::Digits9)
    }

    data class Digits2 private constructor(val value: String) : ConstrainedString() {
        companion object : LimitedLengthDigit<Digits2>(maxLength = 2, creator = ::Digits2)
    }

    data class Digits11 private constructor(val value: String) : ConstrainedString() {
        companion object : LimitedLengthDigit<Digits11>(maxLength = 11, creator = ::Digits11)
    }

    data class Digits10 private constructor(val value: String) : ConstrainedString() {
        companion object : LimitedLengthDigit<Digits10>(maxLength = 10, creator = ::Digits10)
    }

    open class LimitedLengthDigit<T>(private val maxLength: Int, private val creator: (String) -> T) {
        fun of(value: String): T {
            listOf(Validations.maxLength(maxLength)).mapNotNull { it(value) }
            return creator(value)
        }
    }

    open class AlphabeticalString<T>(private val creator: (String) -> T) {
        fun of(value: String): T {
            listOf(Validations::lettersOnly).mapNotNull { it(value) }
            return creator(value)
        }
    }

    private object Validations {
        fun maxLength(maxLength: Int): (String) -> Violation? = {
            when {
                it.length > maxLength -> Violation.MaxLengthExceeded(maxLength)
                else -> null
            }
        }

        fun lettersOnly(value: String): Violation? = null
    }

    sealed class Violation {
        data class MaxLengthExceeded(val maxLength: Int) : Violation()
    }
}
