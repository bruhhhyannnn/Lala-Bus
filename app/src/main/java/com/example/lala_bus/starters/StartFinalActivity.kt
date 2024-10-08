package com.example.lala_bus.starters

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lala_bus.R
import com.example.lala_bus.databinding.ActivityStartFinalBinding
import com.example.lala_bus.input_form.InputFormCommuterActivity
import com.example.lala_bus.input_form.InputFormDriverActivity

class StartFinalActivity : AppCompatActivity() {

    private lateinit var binding : ActivityStartFinalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartFinalBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.commuterButton.setOnClickListener {
            startActivity(Intent(this, InputFormCommuterActivity::class.java))
        }
        binding.driverButton.setOnClickListener {
            startActivity(Intent(this, InputFormDriverActivity::class.java))
        }
    }
}