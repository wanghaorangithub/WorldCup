package com.example.a24078.worldcup.ui.matchlist

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.a24078.worldcup.R
import com.example.a24078.worldcup.entity.MatchBean
import com.example.a24078.worldcup.support.dataStructure.linkedlist.LinkedList
import com.example.a24078.worldcup.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_matchlist.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by haoran on 2018/9/28.
 */
@SuppressLint("Registered")
class MatchListActivity : BaseActivity() {
    private var time: Long = 0

    override fun layoutId(): Int {
        return R.layout.activity_matchlist
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n", "LogNotTimber")
    override fun created(bundle: Bundle?) {
        time = this.intent.getLongExtra("time", 1530455809)
        Log.e("time", time.toString())
        matchlist_act_recyclerview.adapter = MatchListAdapter(setUpMatchList(), this, time)
        matchlist_act_recyclerview.layoutManager = LinearLayoutManager(this)
        val aDate = SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ")
        aDate.timeZone = TimeZone.getTimeZone("GMT+8")
        matchlist_act_textview1.text = "赛程表：" + aDate.format(time * 1000)
        matchlist_act_button.setOnClickListener { finish() }
    }

    @SuppressLint("LogNotTimber")
    override fun resume() {
        Log.e("MatchListActivity","resume")
    }

    private fun setUpMatchList(): LinkedList<MatchBean> {
        val matchListModel = MatchListModel(this)
        matchListModel.setUpList()
        val time = time
        return matchListModel.getMatchListModel(time)
    }

    companion object {
        fun newIntent(packageContext: Context, time: Long): Intent {
            val intent = Intent(packageContext, MatchListActivity::class.java)
            intent.putExtra("time", time)
            return intent
        }
    }
}

