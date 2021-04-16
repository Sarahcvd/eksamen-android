package no.kristiania.eksamen_androidprogrammering.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallet_table")
data class Wallet(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "currency_name") val currencyName: String,
    @ColumnInfo(name = "volume") val volume: Double
)