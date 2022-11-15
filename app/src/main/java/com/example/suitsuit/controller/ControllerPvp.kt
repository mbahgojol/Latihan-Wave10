package com.example.suitsuit.controller

import Callback
import android.content.Intent
import android.util.Log
import com.example.suitsuit.R

class ControllerPvp(private val callback: Callback) {

    fun adu(data1: String, data2: String, nama:String) {

        Log.d("Click", "$nama memilih $data1")
        Log.d("Click", "Pemain 2 memilih $data2")

        when (data1 + data2) {
            "batu" + "batu", "kertas" + "kertas", "gunting" + "gunting" -> {
                Log.d("Result", "Draw")
                callback.tampilanHasil("DRAW!", R.color.deep_blu, R.color.white, 45F)
            }
            "batu" + "kertas", "kertas" + "gunting", "gunting" + "batu" -> {
                Log.d("Result", "Pemain 2 menang")
                callback.tampilanHasil("PEMAIN 2\nMENANG", R.color.green, R.color.white, 30F)
            }
            "gunting" + "kertas", "kertas" + "batu", "batu" + "gunting" -> {
                Log.d("Result", "$nama menang")
                callback.tampilanHasil("$nama\nMENANG!", R.color.green, R.color.white, 30F)
            }
        }
    }
}