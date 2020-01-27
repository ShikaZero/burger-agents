package bdd

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import model.Burger

@Dao
interface BurgerListDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertEvent(burgers: List<Burger>)

    @Query("SELECT * FROM burger")
    fun getAllBurgers(): LiveData<MutableList<Burger>>

    @Query("SELECT * FROM burger")
    fun getAllBurgersData(): MutableList<Burger>
}