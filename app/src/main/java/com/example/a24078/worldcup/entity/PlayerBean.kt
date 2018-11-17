package com.example.a24078.worldcup.entity


/**
 * Created by haoran on 2018/9/27.
 */
data class PlayerBean(
        val objectId: String,
        val playerName: String,
        val shirtNumber: Int,
        var teamObjectId: String?,
        var goal:Int=0
)