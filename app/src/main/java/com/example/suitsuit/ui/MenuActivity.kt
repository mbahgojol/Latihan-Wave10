package com.example.suitsuit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.suitsuit.databinding.ActivityMenuBinding
import com.example.suitsuit.fragment.LandingPage3Fragment
import com.google.android.material.snackbar.Snackbar
//abd
class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nama = intent.getStringExtra("nama")
        binding.tvPvp.text = "$nama vs Pemain"
        binding.tvPvc.text = "$nama vs CPU"

        val snackBar = Snackbar.make(binding.view,"Selamat datang $nama",Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction("Tutup"){
            snackBar.dismiss()
        }

        snackBar.show()

        binding.ivPvp.setOnClickListener {
            Log.d("Click","Button vs Pemain")
            Intent(this, PvpActivity::class.java).apply {
                putExtra("nama", nama)
                startActivity(this)
            }
        }

        binding.ivPvc.setOnClickListener {
            Log.d("Click","Button vs CPU")
            Intent(this, PvcActivity::class.java).apply {
                putExtra("nama", nama)
                startActivity(this)
            }

        }
    }
}