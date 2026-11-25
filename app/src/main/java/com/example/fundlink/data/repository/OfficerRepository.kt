package com.example.fundlink.data.repository

import android.content.Context
import com.example.fundlink.data.db.AppDatabase
import com.example.fundlink.data.model.FundingOpportunity
import com.example.fundlink.data.model.StudentApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OfficerRepository(context: Context) {

    private val opportunityDao =
        AppDatabase.getInstance(context).opportunityDao()

    private val applicationDao =
        AppDatabase.getInstance(context).applicationDao()

    suspend fun getActiveOpportunities(): List<FundingOpportunity> =
        withContext(Dispatchers.IO) {
            opportunityDao.active()
        }

    suspend fun getApplications(): List<StudentApplication> =
        withContext(Dispatchers.IO) {
            applicationDao.all()
        }

    suspend fun getOpportunityCount(): Int =
        withContext(Dispatchers.IO) {
            opportunityDao.activeCount()
        }

    suspend fun getPendingApplicationsCount(): Int =
        withContext(Dispatchers.IO) {
            applicationDao.pendingCount()
        }

    suspend fun addOpportunity(
        title: String,
        description: String,
        amount: Double,
        deadline: String,
        eligibility: String,
        officerId: Long
    ) = withContext(Dispatchers.IO) {

        opportunityDao.insert(
            FundingOpportunity(
                title = title,
                description = description,
                amount = amount,
                deadline = deadline,
                eligibility = eligibility,
                createdBy = officerId
            )
        )
    }

    suspend fun approveApplication(
        applicationId: Long,
        officerId: Long,
        comment: String
    ) = withContext(Dispatchers.IO) {

        applicationDao.decide(
            id = applicationId,
            status = "APPROVED",
            by = officerId,
            at = System.currentTimeMillis(),
            comment = comment
        )
    }

    suspend fun rejectApplication(
        applicationId: Long,
        officerId: Long,
        comment: String
    ) = withContext(Dispatchers.IO) {

        applicationDao.decide(
            id = applicationId,
            status = "REJECTED",
            by = officerId,
            at = System.currentTimeMillis(),
            comment = comment
        )
    }
}