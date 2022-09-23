package gym.mima.workoutlog.models

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("user-id") var user_id :String,
    @SerializedName("date-of-birth") var date_of_birth:String,
    @SerializedName("sex") var sex:String,
    @SerializedName("weight") var weight:Double,

)
