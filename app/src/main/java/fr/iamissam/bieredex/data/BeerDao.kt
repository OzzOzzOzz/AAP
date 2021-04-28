package fr.iamissam.bieredex.data

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.iamissam.bieredex.data.models.BeerData

@Dao
interface BeerDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<BeerData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(beerData: BeerData)

    @Delete
    suspend fun deleteData(beerData: BeerData)
}