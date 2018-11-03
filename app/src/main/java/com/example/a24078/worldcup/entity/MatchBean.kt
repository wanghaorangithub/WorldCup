package com.example.a24078.worldcup.entity

import com.google.gson.annotations.SerializedName


/**
 * Created by haoran on 2018/9/27.
 */
data class MatchBean(
        val objectId: String,
        val awayTeamId: String,
        var awayTeamGoals: Int,
        val homeTeamId: String,
        var homeTeamGoals: Int,
        val isGroupMatch: Boolean,
        val startingTime: Long,
        @SerializedName("match_number")
        val matchNumber: Int,
        var groupId: String? = null,
        var awayTeamName: String? = null,
        var homeTeamName: String? = null,
        var Time: String? = null

) : Comparable<MatchBean> {
    override fun compareTo(other: MatchBean): Int {
        var result: Int = 0
        if (this.startingTime > other.startingTime) {
            result = 1
        }
        if (this.startingTime == other.startingTime) {
            result = 0
        }
        if (this.startingTime < other.startingTime) {
            result = -1
        }
        return result
    }
}

