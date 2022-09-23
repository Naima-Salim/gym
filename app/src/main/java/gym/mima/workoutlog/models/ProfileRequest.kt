package gym.mima.workoutlog.models

data class ProfileRequest(
    val user_id: String,
    val sex: String,
    val date_of_birth: String,
    val weight: Double
)
