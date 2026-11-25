package com.example.fundlink.data.repository

import android.content.Context
import com.example.fundlink.data.db.AppDatabase
import com.example.fundlink.data.model.User
import com.example.fundlink.data.security.PasswordHasher
import com.example.fundlink.data.session.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository(context: Context) {
<<<<<<< HEAD

    private val dao = AppDatabase.getInstance(context).userDao()
    private val session = SessionManager(context)

    // Register a new student account
    suspend fun register(
        name: String,
        email: String,
        password: String
    ): Result<User> = withContext(Dispatchers.IO) {
        runCatching {
            val mail = email.trim().lowercase()

            if (dao.findByEmail(mail) != null) {
                error("Email already registered")
            }

            val salt = PasswordHasher.newSalt()

            val user = User(
                fullName = name.trim(),
                email = mail,
                passwordHash = PasswordHasher.hash(password, salt),
                salt = salt,
                role = "STUDENT"
            )

            user.copy(id = dao.insert(user))
        }
    }

    // Authenticate an existing user
    suspend fun login(
        email: String,
        password: String
    ): Result<User> = withContext(Dispatchers.IO) {
        runCatching {
            val user = dao.findByEmail(email.trim().lowercase())
                ?: error("Invalid email or password")

            if (PasswordHasher.hash(password, user.salt) != user.passwordHash) {
                error("Invalid email or password")
            }

            session.save(user)
            user
        }
    }

    // Retrieve the currently logged-in user
    suspend fun currentUser(): User? = withContext(Dispatchers.IO) {
        val id = session.userId()

        if (id == -1L) {
            null
        } else {
            dao.findById(id)
        }
    }

    // Create the initial officer account if one does not exist
    suspend fun seedOfficer() {
        if (dao.countByRole("OFFICER") > 0) return

        val salt = PasswordHasher.newSalt()

        dao.insert(
            User(
                fullName = "Funding Officer",
                email = "officer@fundlink.com",
                passwordHash = PasswordHasher.hash("Officer@123", salt),
                salt = salt,
                role = "OFFICER"
=======
    private val dao = AppDatabase.get(context).userDao()
    private val session = SessionManager(context)

    suspend fun register(name: String, email: String, password: String): Result<User> =
        withContext(Dispatchers.IO) {
            runCatching {
                val mail = email.trim().lowercase()
                if (dao.findByEmail(mail) != null) error("Email already registered")
                val salt = PasswordHasher.newSalt()
                val user = User(
                    fullName = name.trim(), email = mail,
                    passwordHash = PasswordHasher.hash(password, salt),
                    salt = salt, role = "STUDENT"
                )
                user.copy(id = dao.insert(user))
            }
        }

    suspend fun login(email: String, password: String): Result<User> =
        withContext(Dispatchers.IO) {
            runCatching {
                val user = dao.findByEmail(email.trim().lowercase())
                    ?: error("Invalid email or password")
                if (PasswordHasher.hash(password, user.salt) != user.passwordHash)
                    error("Invalid email or password")
                session.save(user)
                user
            }
        }

    suspend fun currentUser(): User? = withContext(Dispatchers.IO) {
        val id = session.userId()
        if (id == -1L) null else dao.findById(id)
    }

    suspend fun seedOfficer() {
        if (dao.countByRole("OFFICER") > 0) return
        val salt = PasswordHasher.newSalt()
        dao.insert(
            User(
                fullName = "Funding Officer", email = "officer@fundlink.com",
                passwordHash = PasswordHasher.hash("Officer@123", salt),
                salt = salt, role = "OFFICER"
>>>>>>> origin/main
            )
        )
    }

<<<<<<< HEAD
    // Log out the current user
    fun logout() {
        session.clear()
    }
=======
    fun logout() = session.clear()
>>>>>>> origin/main
}
