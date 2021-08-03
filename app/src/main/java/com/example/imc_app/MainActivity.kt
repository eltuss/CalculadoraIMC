package com.example.imc_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_main.*
// plugin kotlinx adicionado no build.gradle(:app) como extension para funcionar a busca direta

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()

    }
    private fun setListeners(){
        // ? - checa a nullability da view alturaEDT
        alturaEDT?.doAfterTextChanged { text ->
            Toast.makeText(this, text.toString(), Toast.LENGTH_LONG).show()
        }
        pesoEDT?.doOnTextChanged{ text, _, _, _ ->
            titleTXT?.text = text
        }
        calcularBTN?.setOnClickListener{
            calcularIMC(pesoEDT.text.toString(), alturaEDT.text.toString())
        }

    }
    private fun calcularIMC(peso: String, altura: String){
        val peso = peso.toFloatOrNull() // aqui ele converte o valor para float ou nulo para não quebrar o app
        val altura = altura.toFloatOrNull()
        if(peso != null && altura != null){
            val imc = peso /(altura * altura) // formula do imc =  peso vezes altura ao quadrado
            titleTXT.text = "seu IMC %.2f".format(imc)
        }
    }

}