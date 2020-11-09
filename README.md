# kotlin-adt-domain-modeling
Domain Modeling with Algebraic Data Types

Inspired by [this talk](https://youtu.be/PLFl95c-IiU) and [this article.](https://medium.com/swlh/value-based-classes-and-error-handling-in-kotlin-3f14727c0565)

How can we diminish primitive obsession when modeling domain classes?

How can we make our domain classes better communicate what are their boundaries? E.g.: Instead of validating and documenting that a phone number must have only 9 digits, why not making a `Digits9` that will represent exactly that instead?

> "Make illegal states unrepresentable." - Yaron Minsky
