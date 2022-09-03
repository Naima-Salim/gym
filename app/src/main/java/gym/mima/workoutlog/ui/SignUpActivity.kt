package gym.mima.workoutlog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import gym.mima.workoutlog.R
import gym.mima.workoutlog.databinding.ActivitySignUpBinding
import gym.mima.workoutlog.models.RegisterRequest
import gym.mima.workoutlog.models.RegisterResponse
import gym.mima.workoutlog.retrofit.ApiClient
import gym.mima.workoutlog.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding=ActivitySignUpBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.btnSignUp.setOnClickListener {
                validateSignUp()
            }
            binding.tvLogin.setOnClickListener {
                val intent=Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        fun validateSignUp(){
           var firstname=binding.etfirstname.text.toString()
           var lastname=binding.etlastname.text.toString()
            var phonenumber=binding.etPhoneNUmber.text.toString()
           var email=binding.etemail.text.toString()
           var password=binding.etPassword.text.toString()
           var confirm=binding.etconfirm.text.toString()


           if (firstname.isBlank()) {
               binding.tilFirstname.error = getString(R.string.enter_firstname)

           }
           if (lastname.isBlank()) {
               binding.tilSecondName.error = getString(R.string.enter_lastname)
           }
           if (email.isBlank()) {
               binding.tilemail.error = getString(R.string.email_required)
           }
           if (password.length<8) {
              binding.tilPassword.error = "only 8 characters are accepted"
           }
           if (confirm.length>8) {
              binding.tilconfirm.error = "password does not match"


           }
            val registerRequest = RegisterRequest(firstname,lastname,email, phonenumber,password)
            makeRegistrationRequest(registerRequest)

       }

    fun makeRegistrationRequest(registerRequest: RegisterRequest){
        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.registerUser(registerRequest)

        request.enqueue(object : Callback<RegisterResponse>{
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful){
                    var message = response.body()?.message
                    Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()
                    //intent to login
                }else{
                    val error=response.errorBody()?.string()
                    Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
    }
