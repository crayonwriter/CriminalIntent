package com.bignerdranch.android.criminalintent

import androidx.lifecycle.ViewModel

class CrimeListViewModel: ViewModel() {
    //get the repository
    private val crimeRepository = CrimeRepository.get()
    //have the repository get the crimes
    val crimeListLiveData = crimeRepository.getCrimes()
}