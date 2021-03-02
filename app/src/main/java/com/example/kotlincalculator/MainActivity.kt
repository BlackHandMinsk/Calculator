package com.example.kotlincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.fathzer.soft.javaluator.DoubleEvaluator

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onClick(v: View?) {
        var textView = v as TextView
        var resultTextView = findViewById<TextView>(R.id.resultTV)
        var oldText = resultTextView.text.toString()

        when(textView.text.toString()){
            "DEL" -> {
                if(oldText.length>0){
                    var newText = oldText.substring(0,oldText.length-1)
                    resultTextView.setText(newText)
                }
            }
            ""->{resultTextView.setText(null)}
            "="->{
                var evaluator = DoubleEvaluator()
               //  var expression = resultTextView.text.replace()
                var result = evaluator.evaluate(resultTextView.text.toString())
                resultTextView.setText(result.toString())

            }
            else->{
                var toAppendString = textView.text.toString()
                if(isOperator(toAppendString[0])&&isOperator(oldText[oldText.length-1])){
                    oldText = oldText.substring(0,oldText.length-1)
                }
                var newText = oldText+toAppendString
                resultTextView.setText(newText)
            }
        }
    }

    public fun isOperator(c:Char):Boolean{
        when(c){
        'X','x','*','/','+','-' ->{return true}
            else -> return false
        }
    }
}