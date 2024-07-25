package ru.easycode.zerotoheroandroidtdd

interface Count {

    fun initial(number: String): UiState
    fun increment(number: String): UiState
    fun decrement(number: String): UiState

    class Base(private val step: Int, private val max: Int, private val min: Int) : Count {

        init {
            if (step <= 0)
                throw IllegalStateException("step should be positive, but was $step")
            if (max <= 0)
                throw IllegalStateException("max should be positive, but was $max")
            if (step > max)
                throw IllegalStateException("max should be more than step")
            if (min > max)
                throw IllegalStateException("max should be more than min")
        }

        override fun initial(number: String): UiState {
            return if (number.toInt() + step > max)
                UiState.Max(number)
            else if (number.toInt() - step < min)
                UiState.Min(number)
            else
                UiState.Base(number)
        }

        override fun increment(number: String): UiState {
            val digits = (number.toInt() + step).toString()
            return initial(digits)
        }

        override fun decrement(number: String): UiState {
            val digits = (number.toInt() - step).toString()
            return initial(digits)
        }
    }
}