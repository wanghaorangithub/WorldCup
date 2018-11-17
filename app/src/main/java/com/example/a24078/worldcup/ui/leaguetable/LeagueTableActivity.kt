package com.example.a24078.worldcup.ui.leaguetable

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.a24078.worldcup.R
import com.example.a24078.worldcup.entity.TeamBean
import com.example.a24078.worldcup.support.dataStructure.linkedlist.LinkedList
import com.example.a24078.worldcup.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_leaguetable.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by haoran on 2018/9/28.
 */
class LeagueTableActivity : BaseActivity() {
    private var time: Long = 0
    override fun layoutId(): Int {
        return R.layout.activity_leaguetable
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun created(bundle: Bundle?) {
        time = this.intent.getLongExtra("time", 1530455809)
        leaguetable_act_recyclerview.adapter = LeagueTableAdapter(setUpTeamList())
        leaguetable_act_recyclerview.layoutManager = LinearLayoutManager(this)
        val aDate = SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ")
        aDate.timeZone = TimeZone.getTimeZone("GMT+8")
        leaguetable_act_textview1.text = "积分榜" + aDate.format(time * 1000)
        leaguetable_act_button.setOnClickListener { finish() }

    }

    override fun resume() {
    }

    private fun setUpTeamList(): ArrayList<LinkedList<TeamBean>> {
        val leagueTableModel = LeagueTableModel(this)
        leagueTableModel.setUpList()
        val time = time
        val teamList = leagueTableModel.getLeagueTableByIntegral(time)

        return teamList
    }

    companion object {
        fun newIntent(packageContext: Context, time: Long): Intent {
            val intent = Intent(packageContext, LeagueTableActivity::class.java)
            intent.putExtra("time", time)
            return intent
        }
    }

}
