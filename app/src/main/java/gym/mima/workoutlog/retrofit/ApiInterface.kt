package gym.mima.workoutlog.retrofit

import gym.mima.workoutlog.models.RegisterRequest
import gym.mima.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>
}