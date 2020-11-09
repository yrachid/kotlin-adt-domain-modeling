package com.yrachid.ddd

import com.yrachid.ddd.ConstrainedString.*

object domain {

    sealed class Document {
        data class CPF(val id: Digits11) : Document()
        data class RG(val id: Digits10) : Document()
        data class CNH(val id: Digits11) : Document()
    }

    data class Person(
        val name: PersonName,
        val phone: PhoneNumber,
        val documents: List<Document>
    )

    data class PersonName(
        val first: LettersOnly,
        val middle: LettersOnly? = null,
        val last: LettersOnly
    )

    data class PhoneNumber(
        val countryCode: PhoneCountryCode,
        val areaCode: Digits2,
        val number: Digits9
    )

    enum class PhoneCountryCode(value: String) {
        BRAZIL("+55")
    }

    val p = Person(
        name = PersonName(
            first = LettersOnly.of("Yasser"),
            last = LettersOnly.of("Rachid")
        ),
        phone = PhoneNumber(
            countryCode = PhoneCountryCode.BRAZIL,
            areaCode = Digits2.of("54"),
            number = Digits9.of("990989999")
        ),
        documents = listOf(
            Document.CPF(
                id = Digits11.of("12345678911")
            ),
            Document.RG(
                id = Digits10.of("7777777777")
            ),
            Document.CNH(
                id = Digits11.of("12345678900")
            )
        )
    )
}
