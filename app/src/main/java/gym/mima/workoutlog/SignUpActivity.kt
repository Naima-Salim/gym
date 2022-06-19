package gym.mima.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import gym.mima.workoutlog.databinding.ActivitySignUpBinding

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
                val intent=Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
        }
        fun validateSignUp(){
           var firstname=binding.etfirstname.text.toString()
           var lastname=binding.etlastname.text.toString()
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

       }

    }
