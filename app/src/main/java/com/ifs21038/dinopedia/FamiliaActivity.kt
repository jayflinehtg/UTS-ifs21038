package com.ifs21038.dinopedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ifs21038.dinopedia.databinding.ActivityFamiliaBinding

class FamiliaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFamiliaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFamiliaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val familia = intent.getParcelableExtra<Familia>(EXTRA_FAMILIA)
        familia?.let {
            // Tampilkan detail dinosaurus di sini
            // Misalnya, binding.tvNamaFamilia.text = it.nama
        }
    }

    companion object {
        const val EXTRA_FAMILIA = "com.ifs21038.dinopedia.EXTRA_FAMILIA"
    }
}
