package com.example.a24078.worldcup.ui.shooterList

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.a24078.worldcup.R
import com.example.a24078.worldcup.entity.PlayerBean
import com.example.a24078.worldcup.support.dataStructure.linkedlist.LinkedList
import com.example.a24078.worldcup.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_shooterlist.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by haoran on 2018/9/28.
 */

class ShooterListActivity : BaseActivity() {
    private var time: Long = 0
    override fun layoutId(): Int {
        return R.layout.activity_shooterlist
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun created(bundle: Bundle?) {
        time = this.intent.getLongExtra("time", 1530455809)
        shooterlist_act_recyclerview.adapter = ShooterListAdapter(setUpPlayerList())
        shooterlist_act_recyclerview.layoutManager = LinearLayoutManager(this)
        val aDate = SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ")
        aDate.timeZone = TimeZone.getTimeZone("GMT+8")
        shooterlist_act_textview1.text="射手榜"+ aDate.format(time * 1000)
        shooterlist_act_button.setOnClickListener { finish()}

    }

    override fun resume() {
    }

    @SuppressLint("LogNotTimber")
    private fun setUpPlayerList(): LinkedList<PlayerBean> {
        val shooterListModel = ShooterListModel(this)
        shooterListModel.setUpList()
        val time = time
        val shootList: LinkedList<PlayerBean> = shooterListModel.getShooterListByEvent(time)
        for (i in 0..(shootList.size() - 1)) {
            Log.e("序号", i.toString())
            Log.e("姓名", shootList[i]?.playerName)
            Log.e("球队", shootList[i]?.teamObjectId)
            Log.e("进球数", shootList[i]?.goal.toString())
        }
        return shootList
    }

    companion object {
        fun newIntent(packageContext: Context, time: Long): Intent {
            val intent = Intent(packageContext, ShooterListActivity::class.java)
            intent.putExtra("time", time)
            return intent
        }
    }

}
