package gym.mima.workoutlog.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import gym.mima.workoutlog.databinding.FragmentProfileBinding
import gym.mima.workoutlog.models.ProfileRequest
import gym.mima.workoutlog.viewmodel.UserViewModel


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var userViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userViewModel=ViewModelProvider(this).get(userViewModel::class.java)
        binding=FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            validateProfile()
        }
    }


fun validateProfile(){
    var name = binding.etSex.toString()
    var email = binding.etId.toString()
    var password = binding.etDate.toString()
    var weight = binding.etWeight.toString()
    var error = false
    if (name.isBlank()){
        binding.tilName.error = "name is required"
    }
    if (email.isBlank()){
        binding.tilEmail.error = "email is required"
    }
    if (password.isBlank()){
        binding.tilPassword.error = "password is required"
    }
    if (weight.isBlank()){
        binding.tilWeight.error = "weight is required"
    }
    if (!error){
        val profilerequest = ProfileRequest(name,email,password,weight)
        userViewModel.profileUser(profilerequest)
    }
}
}