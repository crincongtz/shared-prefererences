package com.crincongtz.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

const val MY_COUNTER_KEY = "counter"

class MainActivity : AppCompatActivity() {

    private var btnCounter: Button? = null
    private var tvCounter: TextView? = null

    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)

        tvCounter = findViewById(R.id.tv_counter)
        btnCounter = findViewById(R.id.btn_counter)
        btnCounter!!.setOnClickListener {
            incNumber()
        }
    }

    private fun incNumber() {
        var currentNumber = tvCounter?.text.toString().toInt()
        currentNumber++
        tvCounter!!.text = currentNumber.toString()
    }

    override fun onStart() {
        super.onStart()
        if (sharedPreferences!!.contains(MY_COUNTER_KEY)) {
            val counter = sharedPreferences!!.getInt(MY_COUNTER_KEY, 0)
            tvCounter!!.text = counter.toString()
        }
    }

    override fun onPause() {
        super.onPause()
        val counter = tvCounter?.text.toString().toInt()
        sharedPreferences!!.edit().putInt(MY_COUNTER_KEY, counter).apply()
    }
}
