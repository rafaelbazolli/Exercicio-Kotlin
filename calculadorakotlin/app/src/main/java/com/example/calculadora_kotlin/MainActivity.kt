package com.example.calculadora_kotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSoma.setOnClickListener {
            performOperation(Operacoes.Adicao)
        }

        binding.btnSubtracao.setOnClickListener {
            performOperation(Operacoes.Subtracao)
        }

        binding.btnMultiplicacao.setOnClickListener {
            performOperation(Operacoes.Multiplicacao)
        }

        binding.btnDivisao.setOnClickListener {
            performOperation(Operacoes.Divisao)
        }

        binding.btnSair.setOnClickListener {
            finish()
        }

        binding.btnLimpar.setOnClickListener {
            binding.txtNum1.text.clear()
            binding.txtNum2.text.clear()
        }
    }

    private fun performOperation(operation: Operacoes) {
        val num1 = binding.txtNum1.text.toString().toFloatOrNull()
        val num2 = binding.txtNum2.text.toString().toFloatOrNull()

        if (num1 == null || num2 == null) {
            Toast.makeText(this, "Digite algum valor válido!", Toast.LENGTH_SHORT).show()
            return
        }

        val result = when (operation) {
            Operacoes.Adicao -> num1 + num2
            Operacoes.Subtracao -> num1 - num2
            Operacoes.Multiplicacao -> num1 * num2
            Operacoes.Divisao -> {
                if (num2 != 0f) {
                    num1 / num2
                } else {
                    Toast.makeText(this, "Não permitido divisão por 0.", Toast.LENGTH_SHORT).show()
                    return
                }
            }
        }

        binding.textView.text = result.toString()
    }

    private enum class Operacoes {
        Adicao, Subtracao, Multiplicacao, Divisao
    }
}
