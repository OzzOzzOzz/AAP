package fr.iamissam.bieredex.data.models

import android.net.Uri
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "todo_table")
@Parcelize
data class BeerData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var uri: String?
) : Parcelable