<<<<<<< HEAD

=======
>>>>>>> origin/main
package com.example.fundlink.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

<<<<<<< HEAD
@Entity(
    tableName = "users",
    indices = [Index(value = ["email"], unique = true)]
)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

=======
@Entity(tableName = "users", indices = [Index(value = ["email"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
>>>>>>> origin/main
    val fullName: String,
    val email: String,
    val passwordHash: String,
    val salt: String,
    val role: String = "STUDENT",
    val createdAt: Long = System.currentTimeMillis()
)
