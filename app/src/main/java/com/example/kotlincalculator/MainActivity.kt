package com.example.kotlincalculator

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.reflect.InvocationTargetException
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(v: View?) {
        val textView = v as TextView
        val resultTextView = findViewById<TextView>(R.id.resultTV)
        var oldText = resultTextView.text.toString()

        when (textView.text.toString()) {
            "DEL" -> {
                if (oldText.isNotEmpty()) {
                    val newText = oldText.substring(0, oldText.length - 1)
                    resultTextView.text = newText
                }
            }
            "C" -> {
                resultTextView.text = null
            }
            "=" -> {
                val evaluator = CustomJavaDoubleEvaluator()
                try {
                    val result = evaluator.evaluate(resultTextView.text.toString())
                    resultTextView.text = result.toString()
                } catch (e: IllegalArgumentException) {
                    resultTextView.text = "НЕВОЗМОЖНО ПОСЧИТАТЬ"
                }
            }
            else -> {
                val toAppendString = textView.text.toString()
                try {
                    if (isOperator(toAppendString[0]) && isOperator(oldText[oldText.length - 1])) {
                        oldText = oldText.substring(0, oldText.length - 1)
                    }
                    val newText = oldText + toAppendString
                    resultTextView.text = newText
                } catch (c: StringIndexOutOfBoundsException) {
                    resultTextView.text = "ВВЕДИТЕ СНАЧАЛА ЧИСЛО"
                } catch (f: InvocationTargetException) {
                    resultTextView.text = "ОШИБКА"
                }
            }
        }
    }

    private fun isOperator(c: Char): Boolean {
        return when (c) {
            '.', '^', '*', '/', '+', '-' -> {
                true
            }
            else -> false
        }
    }
}