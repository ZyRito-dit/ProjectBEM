package com.example.projectbem.Data

import com.example.projectbem.Data.response.divisi.AlatResponseItem
import com.example.projectbem.Data.response.divisi.DataItem
import com.example.projectbem.Data.response.divisi.DataMenu
import com.example.projectbem.Data.response.divisi.MakananResponseItem
import com.example.projectbem.Data.response.divisi.Notulen
import com.example.projectbem.Data.response.divisi.ObatResponseItem
import com.example.projectbem.Data.response.divisi.PendidikanResponseItem
import com.example.projectbem.Data.response.divisi.SakitResponse
import com.example.projectbem.Data.response.user.LoginRequest
import com.example.projectbem.Data.retrofit.ApiService
import com.example.projectbem.Data.room.BemDao
import com.example.projectbem.Data.room.BemEntity

class BemRepository private constructor(
    private val bemDao: BemDao,
    private var apiService: ApiService
){
    suspend fun login(request: LoginRequest) = apiService.login(request)

    suspend fun getProfile(token: String) = apiService.getProfile("token=$token")

    suspend fun getPendidikanBarang(token: String): List<PendidikanResponseItem>? {
        return try {
            val response = apiService.getAllBarang("token=$token")
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun saveUserData(entity: BemEntity) = bemDao.insertUser(entity)

    suspend fun getUserData(): BemEntity? = bemDao.getUser()

    suspend fun logout() = bemDao.deleteUser()

    suspend fun getAllEvents() = apiService.getAllEvents()

    suspend fun getNotulenByBulan(bulan: String): List<Notulen> {
        return apiService.getNotulenByBulan(bulan)
    }

    suspend fun getAllPiketMakan(): List<DataItem?>? {
        val response = apiService.getAllPiket()
        return if (response.isSuccessful) {
            response.body()?.data
        } else {
            null
        }
    }

    suspend fun getAllMakan(): List<MakananResponseItem?>?{
        val response = apiService.getAllMakanan()
        return if (response.isSuccessful){
            response.body()
        } else {
            null
        }
    }

    suspend fun getAllObat(): List<ObatResponseItem?>?{
        val response = apiService.getAllObat()
        return  if (response.isSuccessful){
            response.body()
        }else{
            null
        }
    }

    suspend fun getAllMenuMakan(): List<DataMenu?>? {
        val response = apiService.getAllMenu()
        return if (response.isSuccessful) {
            response.body()?.data
        } else {
            null
        }
    }

    suspend fun getAllPasien(): List<SakitResponse>? {
        val response = apiService.getAllPasien()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    suspend fun getAllAlat(): List<AlatResponseItem?>? {
        val response = apiService.getAllAlat()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
            }
    }

    companion object {
        @Volatile
        private var INSTANCE: BemRepository? = null

        fun getInstance(bemDao: BemDao, apiService: ApiService): BemRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: BemRepository(bemDao, apiService).also { INSTANCE = it }
            }
        }
    }
}