package gym.mima.workoutlog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gym.mima.workoutlog.models.ExerciseCategory
import gym.mima.workoutlog.models.ProfileRequest
import kotlinx.coroutines.launch
import repository.ExerciseRepository

class ExerciseViewModel: ViewModel() {
    val exerciseRepository = ExerciseRepository()
    val exerciseCategoryLiveData = MutableLiveData<List<ExerciseCategory>>()
    val errorLiveData = MutableLiveData<String?>()

    fun fetchExerciseCategories(accessToken: String){
        viewModelScope.launch{
            val respone = exerciseRepository.fetchExerciseCategories(accessToken)
            if (respone.isSuccessful){
                exerciseCategoryLiveData.postValue(respone.body())
            }
            else{
                val errorMsg = respone.errorBody()?.string()
                errorLiveData.postValue(errorMsg)
            }
        }
    }


}
