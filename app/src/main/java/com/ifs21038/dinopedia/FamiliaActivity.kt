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

        val button2 = findViewById<Button>(R.id.button2)

        button2.setOnClickListener {
            val intent = Intent(this@FamiliaActivity, DinoActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        familia = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FAMILIA,
                Familia::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILIA)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (familia != null) {
            supportActionBar?.title = "Menu ${familia!!.name}"
            loadData(familia!!)
        } else {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
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
