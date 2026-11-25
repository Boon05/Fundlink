package com.example.fundlink.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "funding_opportunities")
data class FundingOpportunity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    val amount: Double,
    val deadline: String,
    val eligibility: String,
    val createdBy: Long = 0,
    val active: Boolean = true
)
