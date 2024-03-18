package com.ifs21038.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Dino(
    var name: String,
    var icon: Int,
    var description: String,
    var characteristic: String,
    var habitat: String,
    var kelompok: String,
    var makanan: String,
    var panjang: String,
    var tinggi: String,
    var bobot: String,
) : Parcelable