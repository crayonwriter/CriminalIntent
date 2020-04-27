package com.bignerdranch.android.criminalintent

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.bignerdranch.android.criminalintent.database.CrimeDatabase
import java.util.*

//CrimeRepository is a singleton
private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(context: Context){

    //Room.databaseBuilder() creates a concrete implementation of abstract CrimeDatabase
    //using three parameters
    private val database: CrimeDatabase = Room.databaseBuilder(
        context.applicationContext,
        //The database class you want Room to create
        CrimeDatabase::class.java,
        //The name of the database file you want Room to create
        DATABASE_NAME
    ).build()

    private val crimeDao = database.crimeDao()

    //Functions added for each function in the DAO so other compononents
    //can perform any operations they need to on the database

    fun getCrimes(): LiveData<List<Crime>> = crimeDao.getCrimes()

    fun getCrime(id: UUID): LiveData<Crime?> = crimeDao.getCrime(id)

    //Store references to database and DAO objects
    companion object {
        private var INSTANCE: CrimeRepository? = null

        //initializes a new instance of the repository
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        //accesses the repository
        fun get(): CrimeRepository {
            return INSTANCE ?:
            throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}