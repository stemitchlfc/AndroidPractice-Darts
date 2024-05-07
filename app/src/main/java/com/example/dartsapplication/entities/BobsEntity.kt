package com.example.dartsapplication.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = bobsTable)
data class BobsEntity(
    @PrimaryKey(autoGenerate = true)
    @SerialName("id")
    val id: Int? = null,

    @SerialName("playerName")
    val name: String? = "",

    @SerialName("startingScore")
    val startingScore: String = "",

    @SerialName("remainingScore")
    val remainingScore: String = "",

    @SerialName("previousScore")
    val previousScore: String = "",

    @SerialName("doublesHit")
    val average: String = "",

    @SerialName("misses")
    val dartsThrown: String = "",

    @SerialName("totalThrown")
    val isOnDouble: String = "",

    @SerialName("checkoutRate")
    val checkoutRate: Double = 0.0,

    @SerialName("gameComplete")
    val legComplete: Boolean = false
)


const val bobsTable = "BobsEntity"