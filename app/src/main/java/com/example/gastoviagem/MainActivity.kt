package com.example.gastoviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.gastoviagem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    //pego a interface toda e inicializo ela depois

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //pego o layout -> inflando para pegar
        setContentView(binding.root) //adiciono à rota do Android e ele reconhece

        binding.buttonCalculate.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        view.id.let {
            if (it == R.id.button_calculate) {
                calculate()
            }
        }
    }

    private fun validator(): Boolean {
        val distance = binding.editDistance.text.toString()
        val price = binding.editPrice.text.toString()
        val autonomy = binding.editAutonomy.text.toString()
        var validate = true

        if (distance == "") {
            validate = false
            binding.editDistance.error = getString(R.string.error_distancia)
        }
        if (price == "") {
            validate = false
            binding.editPrice.error = getString(R.string.error_preco)
        }
        if (autonomy == "") {
            validate = false
            binding.editAutonomy.error = getString(R.string.error_autonomia)
        }
        return validate
    }

    fun calculate() {
        if(validator() == false) {
            Toast.makeText(this, R.string.error_validacao, Toast.LENGTH_SHORT).show()
            return
        }
        val distance = binding.editDistance.text.toString().toFloat()
        val price = binding.editPrice.text.toString().toFloat()
        val autonomy = binding.editAutonomy.text.toString().toFloat()
        val totalValue = (distance * price) / autonomy
        binding.textTotalValue.text = "R$ ${"%.2f".format(totalValue)}"
        Toast.makeText(this, "Calculando...", Toast.LENGTH_SHORT).show()
    }

}