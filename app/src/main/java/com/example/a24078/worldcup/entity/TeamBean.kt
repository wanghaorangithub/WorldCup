package com.example.a24078.worldcup.entity

import android.database.AbstractWindowedCursor


/**
 * Created by haoran on 2018/9/27.
 */
data class TeamBean(
        val objectId: String,
        val name: String,
        val group: String,
        var integral: Int = 0,
        var rank: Int = 0,
        var win:Int = 0,
        var draw:Int=0,
        var loss:Int=0,
        var goalDifferent:Int=0

)