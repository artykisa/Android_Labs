package com.example.timer

import Interfaces.ISettings
import adapters.MyLocale
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import com.example.timer.SettingsFragment.Companion.newInstance
import database.DbRep
import org.eclipse.jgit.nls.NLS.setLocale


class SettingsActivity : AppCompatActivity(), ISettings {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment.newInstance(this))
            .commit()
    }

    override fun setDayTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        delegate.applyDayNight()
        recreate()
    }

    override fun setNightTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        delegate.applyDayNight()
        recreate()
    }

    override fun setNormalFont() {
        val configuration: Configuration = resources.configuration
        configuration.fontScale = 1F
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        recreate()
    }

    override fun SetEnglish() {
        MyLocale.setLocale(this, "en");
        recreate()
    }

    override fun SetRussian() {
        MyLocale.setLocale(this, "ru");
        recreate()
    }

    override fun setTinyFont() {
        val configuration: Configuration = resources.configuration
        configuration.fontScale =0.8F
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        recreate()
    }

    override fun onDataClicked() {
        DbRep(application).clean_all_data()
    }


}