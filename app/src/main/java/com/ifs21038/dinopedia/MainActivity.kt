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
import com.ifs21038.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataFamilias = ArrayList<Familia>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvFamilias.setHasFixedSize(false)
        dataFamilias.addAll(getListFamilias())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_about -> {
                val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("Recycle")
    private fun getListFamilias(): ArrayList<Familia> {
        val dataName = resources.getStringArray(R.array.familia_name)
        val dataIcon = resources.obtainTypedArray(R.array.familia_icon)
        val dataDescription = resources.getStringArray(R.array.familia_desc)
        val dataCharacteristic = resources.getStringArray(R.array.familia_characteristic)
        val dataHabitat = resources.getStringArray(R.array.familia_habitat)
        val dataProcess = resources.getStringArray(R.array.familia_periode)
        val dataBehavior = resources.getStringArray(R.array.familia_perilaku)
        val listFamilia = ArrayList<Familia>()
        for (i in dataName.indices) {
            val familia = Familia(
                dataName[i],
                dataIcon.getResourceId(i, -1),
                dataDescription[i],
                dataCharacteristic[i],
                dataHabitat[i],
                dataProcess[i],
                dataBehavior[i]
            )
            listFamilia.add(familia)
        }
        return listFamilia
    }
    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvFamilias.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvFamilias.layoutManager =
                LinearLayoutManager(this)
        }
        val listFamiliaAdapter = ListFamiliaAdapter(dataFamilias)
        binding.rvFamilias.adapter = listFamiliaAdapter
        listFamiliaAdapter.setOnItemClickCallback(object :
            ListFamiliaAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Familia) {
                showSelectedFamilia(data)
            }
        })
    }
    private fun showSelectedFamilia(familia: Familia) {
        val intentWithData = Intent(this@MainActivity,
            FamiliaActivity::class.java)
        intentWithData.putExtra(FamiliaActivity.EXTRA_FAMILIA, familia)
        startActivity(intentWithData)
    }
}