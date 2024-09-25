package com.example.lala_bus.main_fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.lala_bus.R
import com.example.lala_bus.settings.SettingsActivity


class StationFragment : Fragment() {

    private lateinit var settingsButton : ImageButton


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_station, container, false)

        settingsButton = view.findViewById(R.id.settings_button)

        settingsButton.setOnClickListener {
            startActivity(Intent(requireContext(), SettingsActivity::class.java))
        }

        return view
    }
}