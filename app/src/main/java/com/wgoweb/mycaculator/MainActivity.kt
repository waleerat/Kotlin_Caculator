package com.wgoweb.mycaculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNumeric: Boolean = false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        inputTextView.append((view as Button).text)
        if (inputTextView.text.startsWith("0")) {
            inputTextView.text = inputTextView.text.substring(1)
        }
        lastNumeric = true
    }

    fun onClear(view: View) {
        inputTextView.text = "0"
        lastNumeric = false
        lastDot = false
    }

    fun onEqual(view: View) {
        var result = ""
        if (lastNumeric) {
            var textViewValue = inputTextView.text.toString()
            var prefix = ""
            try {
                // if first number start with -
                if (textViewValue.startsWith("-")) {
                    var prefix = "-"
                    textViewValue = textViewValue.substring(1)
                }

                if (textViewValue.contains("/")){
                    // sprit numbers
                    val splitValue = textViewValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    // set operater 1 to minus
                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    result = (one.toDouble() / two.toDouble()).toString()
                } else if (textViewValue.contains("*")) {
                    // sprit numbers
                    val splitValue = textViewValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    // set operater 1 to minus
                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    result = (one.toDouble() * two.toDouble()).toString()
                } else if (textViewValue.contains("+")) {
                    // sprit numbers
                    val splitValue = textViewValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    // set operater 1 to minus
                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    result = (one.toDouble() + two.toDouble()).toString()
                } else if (textViewValue.contains("-")) {
                    // sprit numbers
                    val splitValue = textViewValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    // set operater 1 to minus
                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    result = (one.toDouble() - two.toDouble()).toString()
                } else if (textViewValue.contains("-")){
                    // sprit numbers
                    val splitValue = textViewValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    // set operater 1 to minus
                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    result = (one.toDouble() - two.toDouble()).toString()

                }
                inputTextView.text = removeZeroAfterDot(result)
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }


        Toast.makeText(this, "Get Answer", Toast.LENGTH_SHORT).show()
    }

    fun onDecimalPoint(view: View) {
       if (lastNumeric && !lastDot) {
           inputTextView.append(".")
           lastNumeric = false
           lastDot = true
       }
    }

    fun onOperator(view: View) {
        if (lastNumeric && !isOpreatorAdded(inputTextView.text.toString())) {
            inputTextView.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun removeZeroAfterDot(result: String) : String {
        var value = result
        if (result.contains(".0"))
            value = result.substring(0, result.length -2)
        return value
    }

    private fun isOpreatorAdded(value: String) : Boolean {
        return if (value.startsWith("-")) {
            return false
        } else  {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}