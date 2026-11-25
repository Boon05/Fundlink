package com.example.fundlink.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fundlink.data.model.FundingOpportunity

@Dao
interface OpportunityDao {
    @Insert suspend fun insert(item: FundingOpportunity): Long
    @Query("SELECT * FROM funding_opportunities WHERE active = 1 ORDER BY id DESC")
    suspend fun active(): List<FundingOpportunity>
    @Query("SELECT COUNT(*) FROM funding_opportunities WHERE active = 1")
    suspend fun activeCount(): Int
}
