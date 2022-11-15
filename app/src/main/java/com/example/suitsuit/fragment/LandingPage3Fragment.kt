package com.example.suitsuit.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.suitsuit.databinding.FragmentLandingPage3Binding
import com.example.suitsuit.ui.MenuActivity

class LandingPage3Fragment : Fragment() {

    private lateinit var binding: FragmentLandingPage3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLandingPage3Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etNama.addTextChangedListener {
            Log.d("Tik", it.toString())
            if (it.toString().length > 0) {
                binding.ivNext.visibility = View.VISIBLE
            } else {
                binding.ivNext.visibility = View.GONE
            }

        }

        binding.ivNext.setOnClickListener {
            Intent(requireActivity(), MenuActivity::class.java).apply {
                putExtra("nama", binding.etNama.text.toString())
                startActivity(this)
            }
        }

    }
}
