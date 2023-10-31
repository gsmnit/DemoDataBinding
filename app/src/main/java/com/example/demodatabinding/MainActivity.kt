package com.example.demodatabinding

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.demodatabinding.databinding.ActivityMainBinding

/**
 * Main Activity of the AboutMe app.
 * This codelab demonstrates how to add:
 *     * Data binding between MainActivity and activity_main.xml. How to remove findViewById,
 *       and how to display data in views using the data binding object.
 * This is the starter app.
 */


class MainActivity : AppCompatActivity() {
    private val LOGTAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName("Ganpat Singh")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.doneButton.setOnClickListener() {
            addNickname(it)
        }
        binding.myName = myName

        val configuration = resources.configuration
        val currentMode = configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (currentMode) {
            Configuration.UI_MODE_NIGHT_NO -> { Log.i(LOGTAG,"Day Mode")} // Night mode is not active, we're using the light theme.
            Configuration.UI_MODE_NIGHT_YES -> {Log.i(LOGTAG, "Night Mode")} // Night mode is active, we're using dark theme.
        }
    }

    /**
     * Click handler for the Done button.
     */
    private fun addNickname(view: View) {

        binding.apply {
            myName?.nickname = binding.nicknameEdit.text.toString()
            binding.nicknameEdit.visibility = View.GONE
            binding.doneButton.visibility = View.GONE
            binding.nicknameText.visibility = View.VISIBLE
            invalidateAll()
        }

        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}