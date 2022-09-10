package repository

import gym.mima.workoutlog.api.ApiClient
import gym.mima.workoutlog.api.ApiInterface
import gym.mima.workoutlog.models.LoginRequest
import gym.mima.workoutlog.models.RegisterRequest
import gym.mima.workoutlog.models.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun loginUser(loginRequest: LoginRequest) = withContext(Dispatchers.IO){
        val response = apiClient.login(loginRequest)
        return@withContext response
    }
    suspend fun registerUser(registerRequest: RegisterRequest):Response<RegisterResponse>
    = withContext(Dispatchers.IO){
        val response = apiClient.registerUser(registerRequest)
        return@withContext response
    }
}