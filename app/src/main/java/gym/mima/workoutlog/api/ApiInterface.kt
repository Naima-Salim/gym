package gym.mima.workoutlog.api

import gym.mima.workoutlog.models.LoginRequest
import gym.mima.workoutlog.models.LoginResponse
import gym.mima.workoutlog.models.RegisterRequest
import gym.mima.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @POST("/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}