package com.example.fundlink.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fundlink.data.model.StudentApplication

@Dao
interface ApplicationDao {
<<<<<<< HEAD
    @Insert
    suspend fun insert(app: StudentApplication): Long

    @Query("SELECT * FROM applications WHERE studentId = :studentId ORDER BY submittedAt DESC")
    suspend fun byStudent(studentId: Long): List<StudentApplication>

    @Query("SELECT * FROM applications ORDER BY submittedAt DESC")
    suspend fun all(): List<StudentApplication>

    @Query("SELECT COUNT(*) FROM applications WHERE studentId = :studentId")
    suspend fun countByStudent(studentId: Long): Int

    @Query("SELECT COUNT(*) FROM applications WHERE status = 'PENDING'")
    suspend fun pendingCount(): Int

    @Query("SELECT COUNT(*) FROM applications WHERE studentId = :studentId AND opportunityId = :opportunityId")
    suspend fun existing(studentId: Long, opportunityId: Long): Int

=======
    @Insert suspend fun insert(app: StudentApplication): Long
    @Query("SELECT * FROM applications WHERE studentId = :studentId ORDER BY submittedAt DESC")
    suspend fun byStudent(studentId: Long): List<StudentApplication>
    @Query("SELECT * FROM applications ORDER BY submittedAt DESC")
    suspend fun all(): List<StudentApplication>
    @Query("SELECT COUNT(*) FROM applications WHERE studentId = :studentId")
    suspend fun countByStudent(studentId: Long): Int
    @Query("SELECT COUNT(*) FROM applications WHERE status = 'PENDING'")
    suspend fun pendingCount(): Int
    @Query("SELECT COUNT(*) FROM applications WHERE studentId = :studentId AND opportunityId = :opportunityId")
    suspend fun existing(studentId: Long, opportunityId: Long): Int
>>>>>>> origin/main
    @Query("UPDATE applications SET status = :status, reviewedBy = :by, reviewedAt = :at, reviewComment = :comment WHERE id = :id AND status = 'PENDING'")
    suspend fun decide(id: Long, status: String, by: Long, at: Long, comment: String): Int
}
