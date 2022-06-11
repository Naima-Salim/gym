package gym.mima.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {
        lateinit var btnSign_Up: Button
        lateinit var tvLog_in: TextView
        lateinit var tilFirstname: TextInputLayout
        lateinit var etfirstname: TextInputEditText
        lateinit var tilSecondName: TextInputLayout
        lateinit var etlastname: TextInputEditText
        lateinit var tilemail: TextInputLayout
        lateinit var etemail: TextInputEditText
        lateinit var tilPassword: TextInputLayout
        lateinit var etPassword: TextInputEditText
        lateinit var tilconfirm: TextInputLayout
        lateinit var etconfirm: TextInputEditText


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_sign_up)

            btnSign_Up=findViewById(R.id.btnSign_Up)
            tvLog_in=findViewById(R.id.tvLog_in)
            tilFirstname=findViewById(R.id.tilFirstname)
            etfirstname=findViewById(R.id.etfirstname)
            tilSecondName=findViewById(R.id.tilSecondName)
            etlastname=findViewById(R.id.etlastname)
            tilemail=findViewById(R.id.tilemail)
            etemail=findViewById(R.id.etemail)
            tilPassword=findViewById(R.id.tilPassword)
            etPassword=findViewById(R.id.etPassword)
            tilconfirm=findViewById(R.id.tilconfirm)
            etconfirm=findViewById(R.id.etconfirm)

            btnSign_Up.setOnClickListener {
                validateSignUp()
            }
            tvLog_in.setOnClickListener {
                val intent=Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
        }
        fun validateSignUp(){
           var firstname=etfirstname.text.toString()
           var lastname=etlastname.text.toString()
           var email=etemail.text.toString()
           var password=etPassword.text.toString()
           var confirm=etconfirm.text.toString()


           if (firstname.isBlank()) {
               tilFirstname.error = getString(R.string.enter_firstname)

           }
           if (lastname.isBlank()) {
               tilSecondName.error = getString(R.string.enter_lastname)
           }
           if (email.isBlank()) {
               tilemail.error = getString(R.string.email_required)
           }
           if (password.length<8) {
              tilPassword.error = "only 8 characters are accepted"
           }
           if (confirm.length>8) {
              tilconfirm.error = "password does not match"
           }

       }

    }
