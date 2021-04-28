package fr.iamissam.bieredex.data.repository

import androidx.lifecycle.LiveData
import fr.iamissam.bieredex.data.BeerDao
import fr.iamissam.bieredex.data.models.BeerData

class BeerRepository(private val beerDao: BeerDao) {

    val getAllData: LiveData<List<BeerData>> = beerDao.getAllData()

    suspend fun insertData(beerData: BeerData) {
        beerDao.insertData(beerData)
    }

    suspend fun deleteData(beerData: BeerData) {
        beerDao.deleteData(beerData)
    }

}