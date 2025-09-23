package com.erna.ratna

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private var input: String = ""
    private var angka1: Double = 0.0
    private var angka2: Double = 0.0
    private var operator: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)

        // angka
        val angkaBtn = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot
        )

        val angkaListener = { v: android.view.View ->
            val b = v as Button
            input += b.text
            tvResult.text = input
        }

        angkaBtn.forEach { id ->
            findViewById<Button>(id).setOnClickListener(angkaListener)
        }

        // operator
        val operatorBtn = listOf(R.id.btnAdd, R.id.btnSub, R.id.btnMul, R.id.btnDiv)

        val operatorListener = { v: android.view.View ->
            if (input.isNotEmpty()) {
                angka1 = input.toDouble()
                val b = v as Button
                operator = b.text.toString()
                input = ""
                tvResult.text = operator
            }
        }

        operatorBtn.forEach { id ->
            findViewById<Button>(id).setOnClickListener(operatorListener)
        }

        // sama dengan
        findViewById<Button>(R.id.btnEqual).setOnClickListener {
            if (input.isNotEmpty()) {
                angka2 = input.toDouble()
                val hasil = when (operator) {
                    "+" -> angka1 + angka2
                    "-" -> angka1 - angka2
                    "*" -> angka1 * angka2
                    "/" -> if (angka2 != 0.0) angka1 / angka2 else {
                        tvResult.text = "Error"
                        return@setOnClickListener
                    }
                    else -> 0.0
                }
                tvResult.text = hasil.toString()
                input = ""
                operator = ""
            }
        }

        // clear
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            input = ""
            angka1 = 0.0
            angka2 = 0.0
            operator = ""
            tvResult.text = "0"


            // tombol persen
            findViewById<Button>(R.id.btnPercent).setOnClickListener {
                if (input.isNotEmpty()) {
                    val value = input.toDouble()
                    val hasil = value / 100
                    tvResult.text = hasil.toString()
                    input = hasil.toString()
                }
            }

// tombol delete (hapus 1 digit)
            findViewById<Button>(R.id.btnDel).setOnClickListener {
                if (input.isNotEmpty()) {
                    input = input.dropLast(1)
                    if (input.isEmpty()) {
                        tvResult.text = "0"
                    } else {
                        tvResult.text = input
                    }
                }
            }

        }
    }
}
