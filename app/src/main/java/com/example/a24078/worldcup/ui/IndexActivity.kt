package com.example.a24078.worldcup.ui

import android.annotation.SuppressLint
import android.os.Bundle
import com.example.a24078.worldcup.R
import com.example.a24078.worldcup.support.dataStructure.linkedlist.LinkedList
import com.example.a24078.worldcup.support.utils.parseTime
import com.example.a24078.worldcup.ui.base.BaseActivity
import com.example.a24078.worldcup.ui.leaguetable.LeagueTableActivity
import com.example.a24078.worldcup.ui.matchlist.MatchListActivity
import com.example.a24078.worldcup.ui.shooterList.ShooterListActivity
import kotlinx.android.synthetic.main.activity_index.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by haoran on 2018/9/28.
 */
class IndexActivity : BaseActivity() {
    internal var a = LinkedList<Int>()
    var time: Long = 1528992000000
    override fun layoutId(): Int {
        return R.layout.activity_index
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun created(bundle: Bundle?) {
        val aDate = SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ")
        aDate.timeZone = TimeZone.getTimeZone("GMT+8")
        index_act_textview.text = "当前选择时间段：" + aDate.format(time)
        index_act_button1.setOnClickListener {

        }
        index_act_button2.setOnClickListener {
            this.startActivity(ShooterListActivity.newIntent(this, time / 1000))
        }
        index_act_button3.setOnClickListener {
            this.startActivity(LeagueTableActivity.newIntent(this, time / 1000))
        }
        index_act_button4.setOnClickListener {
            this.startActivity(MatchListActivity.newIntent(this, time / 1000))
        }
        index_act_button5.setOnClickListener {
            val string = "2018" + index_act_edittext1.text.toString() + index_act_edittext2.text.toString() + index_act_edittext3.text.toString() + "0000"
            time = parseTime(string)
            index_act_textview.text = "当前选择时间段：" + aDate.format(time)
        }
    }


    override fun resume() {
    }

}
