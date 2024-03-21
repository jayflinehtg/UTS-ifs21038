package com.ifs21038.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21038.dinopedia.databinding.ActivityDinoBinding

class DinoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDinoBinding
    private val dataDinos = ArrayList<Dino>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvDinos.setHasFixedSize(false)
        dataDinos.addAll(getListDinos())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_about -> {
                val intent = Intent(this@DinoActivity, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("Recycle")
    private fun getListDinos(): ArrayList<Dino> {
        val dataName = resources.getStringArray(R.array.dino_name1)
        val dataIcon = resources.obtainTypedArray(R.array.dino_icon1)
        val dataDescription = resources.getStringArray(R.array.dino_desc1)
        val dataCharacteristic = resources.getStringArray(R.array.dino_characteristic1)
        val dataHabitat = resources.getStringArray(R.array.dino_habitat1)
        val dataProcess = resources.getStringArray(R.array.dino_kelompok1)
        val dataFood = resources.getStringArray(R.array.dino_makanan1)
        val dataPanjang = resources.getStringArray(R.array.dino_panjang1)
        val dataTinggi = resources.getStringArray(R.array.dino_tinggi1)
        val dataBobot = resources.getStringArray(R.array.dino_bobot1)
        val listDino = ArrayList<Dino>()
        for (i in dataName.indices) {
            val dino = Dino(
                dataName[i],
                dataIcon.getResourceId(i, -1),
                dataDescription[i],
                dataCharacteristic[i],
                dataHabitat[i],
                dataProcess[i],
                dataFood[i],
                dataPanjang[i],
                dataTinggi[i],
                dataBobot[i]
            )
            listDino.add(dino)
        }
        return listDino
    }
    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvDinos.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvDinos.layoutManager =
                LinearLayoutManager(this)
        }
        val listDinoAdapter = ListDinoAdapter(dataDinos)
        binding.rvDinos.adapter = listDinoAdapter
        listDinoAdapter.setOnItemClickCallback(object :
            ListDinoAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dino) {
                showSelectedDino(data)
            }
        })
    }

    companion object {
        const val EXTRA_DINO = "com.ifs21038.dinopedia.EXTRA_DINO"
    }


    private fun showSelectedDino(dino: Dino) {
        val intentWithData = Intent(this@DinoActivity,
            DinoActivity::class.java)
        intentWithData.putExtra(DinoActivity.EXTRA_DINO, dino)
        startActivity(intentWithData)
    }
}