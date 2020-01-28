package model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Burger (

    @PrimaryKey
    var ref: String = "",
    var title : String = "",
    var description :  String = "",
    var thumbnail : String = "",
    var price : Double = 0.0,
    var panierId : Int = 0
)
