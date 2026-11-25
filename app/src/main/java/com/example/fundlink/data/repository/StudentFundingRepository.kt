package com.example.fundlink.data.repository

import android.content.Context
import com.example.fundlink.data.db.AppDatabase
import com.example.fundlink.data.model.FundingOpportunity
import com.example.fundlink.data.model.StudentApplication
import com.example.fundlink.data.session.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StudentFundingRepository(context: Context) {
    private val db = AppDatabase.get(context)
    private val session = SessionManager(context)

    suspend fun opportunities(): List<FundingOpportunity> =
        withContext(Dispatchers.IO) { db.opportunityDao().active() }

    suspend fun opportunityCount(): Int =
        withContext(Dispatchers.IO) { db.opportunityDao().activeCount() }

    suspend fun applicationCount(): Int =
        withContext(Dispatchers.IO) { db.applicationDao().countByStudent(session.userId()) }

    suspend fun myApplications(): List<StudentApplication> =
        withContext(Dispatchers.IO) { db.applicationDao().byStudent(session.userId()) }

    suspend fun submit(
        item: FundingOpportunity, studentName: String, amount: Double, statement: String
    ): Result<Long> = withContext(Dispatchers.IO) {
        runCatching {
            val uid = session.userId()
            if (uid == -1L) error("Sign in again")
            if (session.role() != "STUDENT") error("Only students can apply")
            require(statement.isNotBlank()) { "Statement is required" }
            require(amount > 0 && amount <= item.amount) {
                "Amount must be between 0 and KES ${item.amount}"
            }
            if (db.applicationDao().existing(uid, item.id) > 0)
                error("You already applied for this opportunity")
            db.applicationDao().insert(
                StudentApplication(
                    studentId = uid, studentName = studentName,
                    opportunityId = item.id, opportunityTitle = item.title,
                    requestedAmount = amount, statement = statement
                )
            )
        }
    }
}
