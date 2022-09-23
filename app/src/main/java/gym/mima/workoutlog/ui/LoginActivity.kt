package gym.mima.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import gym.mima.workoutlog.R
import gym.mima.workoutlog.databinding.ActivityLoginBinding
import gym.mima.workoutlog.models.LoginRequest
import gym.mima.workoutlog.models.LoginResponse
import gym.mima.workoutlog.api.ApiClient
import gym.mima.workoutlog.api.ApiInterface
import gym.mima.workoutlog.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.KProperty

class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding
    lateinit var sharedPrefs: SharedPreferences
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs = getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
        binding.btnLogin.setOnClickListener {
            validateLogin()
            startActivity(Intent(this, HomeActivity::class.java))
            finish()

        }
        binding.tvSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer{loginResponse->
            Toast.makeText(baseContext, loginResponse?.message,Toast.LENGTH_LONG).show()

            startActivity(Intent(baseContext,HomeActivity::class.java))
            finish()
        })
        userViewModel.loginErrorLiveData.observe(this, Observer{ error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })
    }
    fun validateLogin(){
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        var error = false

        if (email.isBlank()){
            binding.etEmail.error = "Email required"
            error = true
        }

        if (password.isBlank()){
            binding.etPassword.error = "Password required"
            error = true
        }
        if (!error){
            var loginRequest = LoginRequest(email,password)
            userViewModel.loginUser(loginRequest)
        }
    }

    fun saveLoginDetails(loginResponse:LoginResponse){
        val editor = sharedPrefs.edit()
        val token = "Bearer ${loginResponse.accessToken}"
        editor.putString("ACCESS_TOKEN", loginResponse.accessToken)
        editor.putString("USER_ID", loginResponse.userId)
        editor.putString("PROFILE_ID", loginResponse.profileId)
        editor.apply()

    }
}

