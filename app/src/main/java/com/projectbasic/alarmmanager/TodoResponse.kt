package com.projectbasic.alarmmanager

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class TodoResponse(
    @SerializedName("completed")
    val completed: Boolean?, // false
    @SerializedName("id")
    val id: Int?, // 1
    @SerializedName("title")
    val title: String?, // delectus aut autem
    @SerializedName("userId")
    val userId: Int?, // 1
)
