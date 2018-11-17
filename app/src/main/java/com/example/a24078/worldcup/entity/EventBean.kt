package com.example.a24078.worldcup.entity

/**
 * Created by haoran on 2018/9/27.
 */
data class EventBean(
        val objectId: String,
        var time: String,
        var playerId: String,
        val matchId: String,
        val eventType: String,
        var teamId:String?
)

