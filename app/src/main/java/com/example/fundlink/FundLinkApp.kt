package com.example.fundlink

import android.app.Application
import com.example.fundlink.data.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FundLinkApp : Application() {
    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            AuthRepository(this@FundLinkApp).seedOfficer()
        }
    }
}
