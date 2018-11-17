package com.example.a24078.worldcup.ui.matchdetails

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.a24078.worldcup.R
import com.example.a24078.worldcup.ui.base.BaseActivity
import com.example.a24078.worldcup.ui.matchlist.MatchListActivity
import kotlinx.android.synthetic.main.activity_matchdetails.*

/**
 * Created by haoran on 2018/11/8.
 */
@SuppressLint("Registered")
class MatchDetailsActivity : BaseActivity() {

    override fun layoutId(): Int = R.layout.activity_matchdetails


    @SuppressLint("LogNotTimber")
    override fun created(bundle: Bundle?) {
        Log.e("MatchDetailsActivity","created")
        val matchId: String = this.intent.getStringExtra("matchId")
        val time: Long = this.intent.getLongExtra("time", 1530455809)
        val startingTime: Long = this.intent.getLongExtra("startingTime", 1530455809)
        val matchDetailsModel = MatchDetailsModel(this)
        matchDetailsModel.setUpList()
        val eventList = matchDetailsModel.getEventList(matchId, time, startingTime)
        for (i in 0..(eventList.size() - 1)) {
            Log.e("时间", eventList[i]?.time)
            Log.e("球队", eventList[i]?.teamId)
            Log.e("球员", eventList[i]?.playerId)
            Log.e("类型", eventList[i]?.eventType)
        }
        matchdetials_act_textview1.text = this.intent.getStringExtra("team")
        matchdetails_act_textview2.text = this.intent.getStringExtra("goal")

        matchdetails_act_recyclerview.adapter = MatchDetailsAdapter(eventList)
        matchdetails_act_recyclerview.layoutManager = LinearLayoutManager(this)
    }

    @SuppressLint("LogNotTimber")
    override fun resume() {
        Log.e("MatchDetailsActivity","resume")
    }

    companion object {
        fun newIntent(packageContext: Context, time: Long, matchId: String, startingTime: Long, team: String, goal: String): Intent {
            val intent = Intent(packageContext, MatchDetailsActivity::class.java)
            intent.putExtra("time", time)
            intent.putExtra("matchId", matchId)
            intent.putExtra("startingTime", startingTime)
            intent.putExtra("team", team)
            intent.putExtra("goal", goal)
            return intent
        }

    }
}

