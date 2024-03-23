package com.ifs21038.dinopedia

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ifs21038.dinopedia.databinding.ActivityFamiliaBinding

class FamiliaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFamiliaBinding
    private var familia: Familia? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFamiliaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        familia = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FAMILIA,
                Familia::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILIA)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (familia != null) {
            supportActionBar?.title = "Famili ${familia!!.name}"
            loadData(familia!!)
        } else {
            finish()
        }

        binding.buttonListDino.setOnClickListener {
            val intentWithData = Intent(this@FamiliaActivity, DinoActivity::class.java)
            intentWithData.putExtra(DinoActivity.EXTRA_FAMILIA, familia!!)
            startActivity(intentWithData)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Kembali ke activity sebelumnya
                onBackPressed()
                true
            }
            R.id.menu_about -> {
                val intent = Intent(this@FamiliaActivity, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadData(familia: Familia) {
        binding.familiaImage.setImageResource(familia.icon)
        binding.tvFamiliaName.text = familia.name
        binding.tvDetailDescription.text = familia.description
        binding.StringPeriode.text = familia.periode
        binding.tvDetailCharacteristic.text = familia.characteristic
        binding.tvDetailHabitat.text = familia.habitat
        binding.tvPerilaku.text = familia.perilaku

    }
    companion object {
        const val EXTRA_FAMILIA = "com.ifs21038.dinopedia.EXTRA_FAMILIA"
    }
}
