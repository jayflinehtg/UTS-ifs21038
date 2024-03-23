package com.ifs21038.dinopedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.ifs21038.dinopedia.databinding.ActivityDetailDinosaurusBinding

class DetailDinosaurus : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDinosaurusBinding
    private var dino: Dino? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDinosaurusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Pastikan untuk mengambil data Dino dari intent
        dino = intent.getParcelableExtra(EXTRA_DINO)
        dino?.let {
            loadData(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Kembali ke activity sebelumnya
                onBackPressed()
                true
            }
            R.id.tampilan_berbagi -> {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.setType("text/plain")
                shareIntent.putExtra(Intent.EXTRA_TEXT, "${dino!!.name}, ${dino!!.description}, ${dino!!.characteristic}, ${dino!!.kelompok}")
                startActivity(Intent.createChooser(shareIntent, "Bagikan melalui"))
                true
            }
            else -> false // Tambahkan kasus else ini
        }
    }

    private fun loadData(dino: Dino) {
        binding.ivgambarDino.setImageResource(dino.icon)
        binding.tvDinoName.text = dino.name
        binding.tvDescDino.text = dino.description
        binding.tvKarakteristikDino.text = dino.characteristic
        binding.tvKelompokDinosaurus.text = dino.kelompok
        binding.tvHabitatDinosaurus.text = dino.habitat
        binding.tvMakananDinosaurus.text = dino.makanan
        binding.tvPanjangDinosaurus.text = dino.panjang
        binding.tvTinggiDinosaurus.text = dino.tinggi
        binding.tvBobotDinosaurus.text = dino.bobot

    }

    companion object {
        const val EXTRA_DINO = "com.ifs21038.dinopedia.EXTRA_DINO"
    }
}