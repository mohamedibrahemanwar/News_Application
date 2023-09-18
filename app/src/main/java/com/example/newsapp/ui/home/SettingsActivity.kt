package com.example.newsapp.ui.home

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.newsapp.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivitySettingsBinding
    private var currentTheme = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        //sharedPreferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

        initLanguage()
        initThems()


    }

    private fun initThems() {
        val themeItems = arrayOf("System Default", "Light Mode", "Dark Mode")
        val themsAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, themeItems)

        viewBinding.spinnerThems.apply {
            viewBinding.spinnerThems.adapter = themsAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    //fix error
                    when (p2) {
                        0 -> {
                            // System Default (Follow system settings)
                            currentTheme = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                        }
                        1 -> {
                            // Light Mode
                            currentTheme = AppCompatDelegate.MODE_NIGHT_NO
                        }
                        2 -> {
                            // Dark Mode
                            currentTheme = AppCompatDelegate.MODE_NIGHT_YES
                        }
                    }
                    AppCompatDelegate.setDefaultNightMode(currentTheme)
                    delegate.applyDayNight()

                    viewBinding.darkenView.visibility =
                        if (currentTheme == AppCompatDelegate.MODE_NIGHT_YES) View.VISIBLE else View.GONE
                    setSelection(p2)

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
    }



    private fun initLanguage() {
        val languageItem = arrayOf("English", "Arabic")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languageItem)
        viewBinding.spinnerSettings.apply {
            viewBinding.spinnerSettings.adapter = adapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (languageItem[p2].equals("Arabic")) {
                    }
//                    Toast.makeText(this@SettingsActivity, languageItem[p2], Toast.LENGTH_LONG)
//                        .show()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        }
    }

}