package com.example.mycalculator

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mycalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression

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

    fun onDigitClick(view: View) {}
    fun onOperatorClick(view: View) {}
    fun onBackClick(view: View) {}
    fun onAllClearClick(view: View) {}
    fun onEqualClick(view: View) {}
    fun onClearClick(view: View) {}
}