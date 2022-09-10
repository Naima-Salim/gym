package gym.mima.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import gym.mima.workoutlog.R
import gym.mima.workoutlog.databinding.ActivityLoginBinding
import gym.mima.workoutlog.models.LoginRequest
import gym.mima.workoutlog.models.LoginResponse
import gym.mima.workoutlog.retrofit.ApiClient
import gym.mima.workoutlog.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedprefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedprefs = getSharedPreferences("android.content.Context.MODE_PRIVATE", MODE_PRIVATE)


        binding.btnLogin.setOnClickListener {
            val intent=Intent(this, HomeActivity::class.java)
            startActivity(intent)
            validateLogin()
//            startActivity(Intent(this, HomeActivity::class.java))

        }
//        binding.tvSignup.setOnClickListener {
//            val intent = Intent(this, HomeActivity::class.java)
//            startActivity(intent)
//        }
    }
    fun validateLogin(){
        var email=binding.etEmail.text.toString()
        var password=binding.etPassword.text.toString()
        var error=false

        if (email.isBlank()) {
            binding.tilEmail.error = getString(R.string.email_required)
            error=true
        }
        if (password.isBlank()) {
            binding.tilPassword.error = getString(R.string.password_required)
            error=true
        }
//        if(!error){
//            startActivity(Intent(this, SignUpActivity::class.java))
//            finish()
//        }
        
        if (!error){
            var loginRequest=LoginRequest(email, password)
            makeLoginRequest(loginRequest)

        }
        fun makeLoginRequest(loginRequest: LoginRequest){
            var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
            val request = apiClient.login(loginRequest)

            request.enqueue(object :Callback<LoginResponse>{
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful){


                    }else{
                        val error = response.errorBody()?.string()
                        Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()

                }
            })
        }
        fun saveLoginResponse(loginResponse: LoginResponse){
            val editor = sharedprefs.edit()
            editor.putString("ACCESS TOKEN", loginResponse.accessToken)
            editor.putString("USER_ID", loginResponse.userId)
            editor.putString("PROFILE_ID", loginResponse.profileId)
            editor.apply()




        }
    }

    private fun makeLoginRequest(loginRequest: LoginRequest) {

    }
}