package gym.mima.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import gym.mima.workoutlog.R
import gym.mima.workoutlog.databinding.ActivityHomeBinding
import utils.Constants

class HomeActivity : AppCompatActivity() {
    lateinit var fcvHome:FragmentContainerView
    lateinit var binding: ActivityHomeBinding
    lateinit var bnvHome:BottomNavigationView
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            logoutRequest()
        }
        setupBottomNav()
        castViews()
        sharedPref = getSharedPreferences(Constants.prefsFile, MODE_PRIVATE)
        val token = sharedPref.getString(Constants.accessToken, Constants.EMPTY_STRING)
//        exerciseViewModel.fetchExerciseCategories(token!!)

    }

    override fun onResume() {
        super.onResume()
//        exerciseViewModel.exerciseCategoryLiveData.observe(this, {exerciseCategories->
//            Toast.makeText(this, "fetched ${exerciseCategories.size.size}categories", Toast.LENGTH_LONG)
//
//        })
    }

    fun castViews(){
        fcvHome = findViewById(R.id.fcvHome)
        bnvHome = findViewById(R.id.bnvHome)
    }

    fun setupBottomNav(){
        binding.bnvHome.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.plan ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, PlanFragment()).commit()
                    true
                }
                R.id.track ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, TrackFragment()).commit()
                    true
                }
                R.id.profile ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, ProfileFragment()).commit()
                    true
                }
                  else->false
            }
        }

    }
    fun logoutRequest(){
        sharedPref.edit().clear().commit()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}