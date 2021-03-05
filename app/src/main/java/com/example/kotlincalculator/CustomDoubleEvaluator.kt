package com.example.kotlincalculator

import com.fathzer.soft.javaluator.DoubleEvaluator
import com.fathzer.soft.javaluator.Function
import com.fathzer.soft.javaluator.Parameters
import kotlin.math.sqrt

class CustomDoubleEvaluator : DoubleEvaluator(PARAMS) {
    companion object {
        private val SQRT = Function("√", 1)
        private var PARAMS: Parameters?= null
        init {
            PARAMS = getDefaultParameters()
            PARAMS?.add(SQRT)
        }
    }

    override fun evaluate(function: Function, arguments: Iterator<Double>, evaluationContext: Any): Double {
        return if (function === SQRT) {
            sqrt(arguments.next())
        } else {
            super.evaluate(function, arguments, evaluationContext)
        }
    }
}