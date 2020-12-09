package com.example.timer

import Interfaces.ISettings
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat

class SettingsFragment : PreferenceFragmentCompat() {
    lateinit var settingsListener : ISettings
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val themePreference: ListPreference?=findPreference("sync_theme")
        themePreference?.onPreferenceChangeListener=object : Preference.OnPreferenceChangeListener {
            override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
                if(newValue=="day"){
                    settingsListener.setDayTheme()
                }
                else{
                    settingsListener.setNightTheme()
                }
                return true
            }
        }

        val fontPref: ListPreference?=findPreference("sync_frequency")
        fontPref?.onPreferenceChangeListener=object : Preference.OnPreferenceChangeListener{
            override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
                if(newValue=="big"){
                    settingsListener.setNormalFont()
                }
                else{
                    settingsListener.setTinyFont()
                }
                return true
            }
        }

        val langPref: ListPreference?=findPreference("sync_lang")
        langPref?.onPreferenceChangeListener=object: Preference.OnPreferenceChangeListener{
            override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
                if(newValue == "eng"){
                    settingsListener.SetEnglish()
                }
                else{
                    settingsListener.SetRussian()
                }
                return true
            }
        }

        val cleanData: SwitchPreferenceCompat?=findPreference("clean_data")
        cleanData?.onPreferenceChangeListener=object: Preference.OnPreferenceChangeListener{
            override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
                if(cleanData!!.isChecked)
                {
                    settingsListener.onDataClicked()
                }
                return true
            }
        }
    }
    companion object {

        @JvmStatic
        fun newInstance(settingsL: ISettings) =
            SettingsFragment().apply {
                settingsListener=settingsL
            }

    }
}