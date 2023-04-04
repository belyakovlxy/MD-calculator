package com.example.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.calc.databinding.ActivityMainBinding
import com.example.calc.viewmodel.CalculatorRealisation

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.calc = CalculatorRealisation()
    }
}