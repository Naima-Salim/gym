package gym.mima.workoutlog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gym.mima.workoutlog.models.LoginRequest
import gym.mima.workoutlog.models.LoginResponse
import gym.mima.workoutlog.models.RegisterRequest
import gym.mima.workoutlog.models.RegisterResponse
import kotlinx.coroutines.launch
import repository.UserRepository

class UserViewModel: ViewModel() {
    val userRepository = UserRepository()
    var loginResponseLiveData = MutableLiveData<LoginResponse>()
    val loginErrorLiveData = MutableLiveData<String?>()
    var registerResponseLiveData = MutableLiveData<RegisterResponse>()
    val registerErrorLiveData = MutableLiveData<String?>()

    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response = userRepository.loginUser(loginRequest)
            if (response.isSuccessful){
                loginResponseLiveData.postValue(response.body())
            }
            else{
                val error = response.errorBody()?.string()
                loginErrorLiveData.postValue(error)
            }

        }

    }
    fun registerUser(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            val response = userRepository.registerUser(registerRequest)
            if (response.isSuccessful) {
                registerResponseLiveData.postValue(response.body())
            }
        }
    }


}