package com.example.suitsuit.ui

import Callback
import android.os.Build
import com.example.suitsuit.controller.ControllerPvc
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
import com.example.suitsuit.databinding.ActivityPvcBinding
import com.example.suitsuit.databinding.CustomDialogBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class PvcActivity : AppCompatActivity(), Callback {

    private lateinit var binding: ActivityPvcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPvcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nama = intent.getStringExtra("nama")
        binding.tvPemain.text = nama

        Glide.with(this)
            .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
            .into(binding.ivJudulGame)

        val controllerPvc = ControllerPvc(this)

        val pemain1 = arrayListOf(
            binding.iv1, //batu
            binding.iv2, //kertas
            binding.iv3 //gunting
        )

        val com = arrayListOf(
            binding.iv4, //batu
            binding.iv5, //kertas
            binding.iv6 //gunting
        )

        pemain1.forEach { imageView ->
            imageView.setOnClickListener {
                imageView.selected()
                val computer = com.random()
                computer.selected()
                enableBtn(false)
                val pilihanP1 = imageView.contentDescription.toString()
                val pilihanCom = computer.contentDescription.toString()
                Toast.makeText(this, "$nama memilih $pilihanP1", Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "CPU memilih $pilihanCom", Toast.LENGTH_SHORT).show()
                if (nama != null) {
                    controllerPvc.adu(
                        imageView.contentDescription.toString().lowercase(),
                        computer.contentDescription.toString().lowercase(),
                        nama
                    )
                }
            }
        }

        //reset
        binding.iv7.setOnClickListener {
            Log.d("Click", "Button reset")
            pemain1.forEach { imageView -> imageView.unselected() }
            com.forEach { imageView -> imageView.unselected() }
            enableBtn(true)
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

    private fun loopImage(){
        val com = listOf(
            binding.iv4,
            binding.iv5,
            binding.iv6,
            binding.iv4,
            binding.iv5,
            binding.iv6
        )
        GlobalScope.launch  (Dispatchers.IO){
            com.forEachIndexed{index, imageView ->
                launch (Dispatchers.Main){
                    if(index !=0) com[index-1].unselected()
                    if(index== com.size.minus(1))com.random().selected()
                    else imageView.selected()
                }
                Thread.sleep(500)
            }
        }
    }

    private fun enableBtn(isEnable: Boolean) {
        binding.iv1.isEnabled = isEnable
        binding.iv2.isEnabled = isEnable
        binding.iv3.isEnabled = isEnable
    }

    private fun ImageView.selected() {
        background = ContextCompat.getDrawable(this@PvcActivity, R.drawable.bg_rounded)
    }

    private fun ImageView.unselected() {
        background = ContextCompat.getDrawable(this@PvcActivity, R.drawable.bg_rounded_white)
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

