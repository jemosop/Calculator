package com.example.mycalculator

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mycalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var expression: Expression
    var lastNumeric = false
    var stateError = false
    var lastDecimal = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun onDigitClick(view: View) {
        if (stateError){
            binding.dataTv.text =(view as Button).text
            stateError = false
        }
        else{
            binding.dataTv.append((view as Button).text)
        }
        lastNumeric = true
        onEqual()
    }
    fun onOperatorClick(view: View) {
        if (!stateError && lastDecimal){
            binding.dataTv.append((view as Button).text)
            lastDecimal =false
            lastNumeric = false
            onEqual()

        }
    }
    fun onBackClick(view: View) {
        binding.dataTv.text = binding.dataTv.text.toString().dropLast(1)
        try {
            val lastChar = binding.dataTv.text.toString().last()
            if (lastChar.isDigit()){
                onEqual()
            }
        }
        catch (e : Exception){
            binding.resultsTv.visibility = View.GONE
            Log.e("lastChar error", e.toString())
        }
    }
    fun onAllClearClick(view: View) {
        stateError = false
        lastDecimal = false
        lastNumeric = false
        binding.dataTv.text = ""
        binding.resultsTv.text = ""
        binding.resultsTv.visibility = View.GONE
    }
    fun onEqualClick(view: View) {
        onEqual()
        binding.dataTv.text = binding.resultsTv.text.toString()
    }

    fun onClearClick(view: View) {
        binding.dataTv.text = ""
        lastNumeric = false
    }
    fun onEqual() {
        if(lastNumeric && !!stateError){
            val text = binding.resultsTv.text.toString()
            expression = ExpressionBuilder(text).build()
        }
        try {
            val result = expression.evaluate()
            binding.resultsTv.visibility =  View.VISIBLE
            binding.resultsTv.text = "=" + result.toString()
        }
        catch (ex:ArithmeticException){
            Log.e("evaluate error", ex.toString())
            binding.resultsTv.text ="Error"
            stateError = true
            lastNumeric = false

        }
    }
}