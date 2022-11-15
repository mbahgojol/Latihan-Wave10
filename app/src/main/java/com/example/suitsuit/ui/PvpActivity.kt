package com.example.suitsuit.ui

import Callback
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.suitsuit.R
import com.example.suitsuit.controller.ControllerPvp
import com.example.suitsuit.databinding.ActivityPvpBinding
import com.example.suitsuit.databinding.CustomDialogBinding

class PvpActivity : AppCompatActivity(), Callback {

    private lateinit var binding: ActivityPvpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPvpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nama = intent.getStringExtra("nama")
        binding.tvPemain.text = nama

        Glide.with(this)
            .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
            .into(binding.ivJudulGame)

        val controllerPvp = ControllerPvp(this)

        val pemain1 = arrayListOf(
            binding.iv1, //batu
            binding.iv2, //kertas
            binding.iv3 //gunting
        )

        val pemain2 = arrayListOf(
            binding.iv4, //batu
            binding.iv5, //kertas
            binding.iv6 //gunting
        )

        var inputPemain1 = ""
        var inputPemain2 = ""

        pemain1.forEach { imageView ->
            imageView.setOnClickListener {
                imageView.selected()
                enableBtnP1(false)
                inputPemain1 = imageView.contentDescription.toString().lowercase()
                val pilihan = imageView.contentDescription.toString()
                Toast.makeText(this, "$nama memilih $pilihan", Toast.LENGTH_SHORT).show()
                if (inputPemain2 != "") nama?.let { it1 ->
                    controllerPvp.adu(
                        inputPemain1, inputPemain2,
                        it1
                    )
                }
            }
        }

        pemain2.forEach { imageView ->
            imageView.setOnClickListener {
                imageView.selected()
                enableBtnP2(false)
                val pilihan = imageView.contentDescription.toString()
                Toast.makeText(this, "Pemain 2 memilih $pilihan", Toast.LENGTH_SHORT).show()
                inputPemain2 = imageView.contentDescription.toString().lowercase()
                if (inputPemain1 != "") nama?.let { it1 ->
                    controllerPvp.adu(
                        inputPemain1, inputPemain2,
                        it1
                    )
                }
            }
        }

        //reset
        binding.iv7.setOnClickListener {
            Log.d("Click", "Button reset")
            pemain1.forEach { imageView -> imageView.unselected() }
            pemain2.forEach { imageView -> imageView.unselected() }
            enableBtnP1(true)
            enableBtnP2(true)
            binding.tvResult.setText(R.string.vs)
            binding.tvResult.textSize = 100F
            binding.tvResult.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            binding.tvResult.setTextColor(ContextCompat.getColor(this, R.color.red))
        }

        //close
        binding.iv8.setOnClickListener {
            finish()
        }
    }

    private fun enableBtnP1(isEnable: Boolean) {
        binding.iv1.isEnabled = isEnable
        binding.iv2.isEnabled = isEnable
        binding.iv3.isEnabled = isEnable
    }

    private fun enableBtnP2(isEnable: Boolean) {
        binding.iv4.isEnabled = isEnable
        binding.iv5.isEnabled = isEnable
        binding.iv6.isEnabled = isEnable
    }

    private fun ImageView.selected() {
        background = ContextCompat.getDrawable(this@PvpActivity, R.drawable.bg_rounded)
    }

    private fun ImageView.unselected() {
        background = ContextCompat.getDrawable(this@PvpActivity, R.drawable.bg_rounded_white)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun tampilanHasil(hasil: String, bg: Int, warnaText: Int, ukuranText: Float) {
        binding.tvResult.text = hasil
        binding.tvResult.textSize = ukuranText
        binding.tvResult.setBackgroundColor(getColor(bg))
        binding.tvResult.setTextColor(getColor(warnaText))

        val view = CustomDialogBinding.inflate(LayoutInflater.from(this), null, false)
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setView(view.root)

        val dialog = dialogBuilder.create()
        view.tvNama.text = hasil

        view.btnRestart.setOnClickListener {
            Log.d("Click", "Button restart")
            Toast.makeText(this, "Tekan tombol reset", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        view.btnBack.setOnClickListener {
            Log.d("Click", "Button kembali ke menu")
            Toast.makeText(this, "Kembali ke Menu", Toast.LENGTH_SHORT).show()
            finish()
            dialog.dismiss()
        }
        dialog.show()
    }
}