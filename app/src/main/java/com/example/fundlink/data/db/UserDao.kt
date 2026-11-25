package com.example.fundlink.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fundlink.data.model.User

@Dao
interface UserDao {
<<<<<<< HEAD
    @Insert
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun findByEmail(email: String): User?

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    suspend fun findById(id: Long): User?

    @Query("SELECT COUNT(*) FROM users WHERE role = :role")
    suspend fun countByRole(role: String): Int
=======
    @Insert suspend fun insert(user: User): Long
    @Query("SELECT * FROM users WHERE email = :email LIMIT 1") suspend fun findByEmail(email: String): User?
    @Query("SELECT * FROM users WHERE id = :id LIMIT 1") suspend fun findById(id: Long): User?
    @Query("SELECT COUNT(*) FROM users WHERE role = :role") suspend fun countByRole(role: String): Int
>>>>>>> origin/main
}
