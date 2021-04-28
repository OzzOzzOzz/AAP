package fr.iamissam.bieredex.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import fr.iamissam.bieredex.data.BeerDatabase
import fr.iamissam.bieredex.data.models.BeerData
import fr.iamissam.bieredex.data.repository.BeerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BeerViewModel(application: Application) : AndroidViewModel(application) {

    private val beerDao = BeerDatabase.getDatabase(application).beerDao()
    private val repository: BeerRepository

    val getAllData: LiveData<List<BeerData>>

    init {
        repository = BeerRepository(beerDao)
        getAllData = repository.getAllData
    }

    fun insertData(beerData: BeerData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(beerData)
        }
    }


    fun deleteItem(beerData: BeerData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData(beerData)
        }
    }

}