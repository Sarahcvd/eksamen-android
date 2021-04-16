package no.kristiania.eksamen_androidprogrammering.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import no.kristiania.eksamen_androidprogrammering.entities.Wallet

const val DATABASE_NAME: String = "wallet_database"

@Database(entities = [Wallet::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun getWalletDAO(): WalletDAO


    companion object {
        private var db: DataBase? = null

        // Get the singleton instance of Database object.
        fun getDatabase(context: Context): DataBase {
            val newDb =
                db ?: Room.databaseBuilder(context, DataBase::class.java, DATABASE_NAME).build()
            return newDb.also {
                db = it
            }
        }

    }
}