package com.bignerdranch.android.criminalintent

import android.app.Application

//Hook up this subclass in the manifest.
//<application android:name=".CriminalIntentApplication" ...>

class CriminalIntentApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)
    }
}