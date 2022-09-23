package gym.mima.workoutlog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import gym.mima.workoutlog.R
import gym.mima.workoutlog.databinding.ActivitySignUpBinding
import gym.mima.workoutlog.models.RegisterRequest
import gym.mima.workoutlog.models.RegisterResponse
import gym.mima.workoutlog.api.ApiClient
import gym.mima.workoutlog.api.ApiInterface
import gym.mima.workoutlog.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    val userViewModel: UserViewModel by viewModels()

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

       }

    override fun onResume() {
        super.onResume()
        userViewModel.registerResponseLiveData.observe(this, Observer{
                signupResponse->
            Toast.makeText(baseContext, signupResponse.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext, LoginActivity::class.java))

        })
        userViewModel.registerErrorLiveData.observe(this, Observer {
            signupError->
            Toast.makeText(baseContext,signupError,Toast.LENGTH_LONG).show()
        })
    }
    }
