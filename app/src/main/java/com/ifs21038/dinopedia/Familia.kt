package com.ifs21038.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Familia(
    var name: String,
    var icon: Int,
    var description: String,
    var characteristic: String,
    var habitat: String,
    var periode: String,
    var perilaku: String,
    var awalanIndex: Int,
    var akhiranIndex: Int
) : Parcelable