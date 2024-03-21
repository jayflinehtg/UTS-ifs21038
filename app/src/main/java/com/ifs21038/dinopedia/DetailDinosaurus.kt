package com.ifs21038.dinopedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DetailDinosaurus : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_dinosaurus)
    }
    companion object {
        const val EXTRA_DINO = "com.ifs21038.dinopedia.EXTRA_DINO"
    }
}