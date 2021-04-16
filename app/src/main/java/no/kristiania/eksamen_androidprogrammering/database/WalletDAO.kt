package no.kristiania.eksamen_androidprogrammering.database

import androidx.room.*
import no.kristiania.eksamen_androidprogrammering.entities.Wallet

@Dao
interface WalletDAO {

    @Insert
    suspend fun insert(wallet: Wallet)

    @Update
    suspend fun update(wallet: Wallet)

    @Delete
    suspend fun delete(wallet: Wallet)

    @Query("SELECT * FROM wallet_table")
    suspend fun fetchAll(): List<Wallet>

    @Query("select * from wallet_table where id = :currencyID")
    suspend fun getCurrencyWithID(currencyID: Long): Wallet

}