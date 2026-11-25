package com.example.fundlink.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "applications", indices = [Index("studentId"), Index("opportunityId")])
data class StudentApplication(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val studentId: Long,
    val studentName: String,
    val opportunityId: Long,
    val opportunityTitle: String,
    val requestedAmount: Double,
<<<<<<< HEAD
    val statement: String = "",
=======
    val statement: String,
>>>>>>> origin/main
    val status: String = "PENDING",
    val submittedAt: Long = System.currentTimeMillis(),
    val reviewedBy: Long = 0,
    val reviewedAt: Long = 0,
    val reviewComment: String = ""
)
