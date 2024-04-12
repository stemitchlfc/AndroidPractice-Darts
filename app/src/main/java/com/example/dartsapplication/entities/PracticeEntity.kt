package com.example.dartsapplication.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = tableName)
data class PracticeEntity(
    @PrimaryKey(autoGenerate = true)
    @SerialName("id")
    val id: Int = 0,

    @SerialName("playerName")
    val name: String? = "",

    @SerialName("startingScore")
    val startingScore: String = "",

    @SerialName("remainingScore")
    val remainingScore: String = "",

    @SerialName("previousScore")
    val previousScore: String = "",

    @SerialName("average")
    val average: String = "",

    @SerialName("dartsThrown")
    val dartsThrown: Int = 0,

    @SerialName("isOnDouble")
    val isOnDouble: Boolean = false,

    @SerialName("dartsAtDouble")
    val dartsAtDouble: Int = 0,

    @SerialName("checkoutRate")
    val checkoutRate: Double? = 0.0,

    @SerialName("legComplete")
    val legComplete: Boolean = false
)


const val tableName = "PracticeEntity"