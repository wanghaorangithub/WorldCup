package com.example.a24078.worldcup.test

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.a24078.worldcup.R
import com.example.a24078.worldcup.entity.MatchBean
import com.example.a24078.worldcup.entity.PlayerBean
import com.example.a24078.worldcup.entity.TeamBean
import com.example.a24078.worldcup.support.dataStructure.linkedlist.LinkedList
import com.example.a24078.worldcup.support.utils.parseTime
import com.example.a24078.worldcup.ui.leaguetable.LeagueTableModel
import com.example.a24078.worldcup.ui.matchlist.MatchListModel
import com.example.a24078.worldcup.ui.shooterList.ShooterListModel
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    internal var a = LinkedList<Int>()
    private var b = LinkedList<Int>()
    var shooterListModel: ShooterListModel = ShooterListModel(this)
    var matchListModel: MatchListModel = MatchListModel(this)
    var leagueTableModel: LeagueTableModel = LeagueTableModel(this)
    var time: Long = 1530455809000

    @SuppressLint("LogNotTimber", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val aDate = SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ")
        aDate.timeZone = TimeZone.getTimeZone("GMT+8")
        textview.text = aDate.format(time)

        button1.setOnClickListener {
            shooterListModel.setUpList()
            matchListModel.setUpList()
            leagueTableModel.setUpList()
            time /= 1000
            Log.e("当前时间戳", time.toString())
            val matchList: LinkedList<MatchBean> = matchListModel.getMatchListModel(time)
            val shootList: LinkedList<PlayerBean> = shooterListModel.getShooterListByEvent(time)
            val teamList: ArrayList<LinkedList<TeamBean>> = leagueTableModel.getLeagueTableByIntegral(time)
//            for (i in 0..(shootList.size() - 1)) {
//                Log.e("序号",i.toString())
//                Log.e("姓名", shootList[i]?.playerName)
//                Log.e("球队", shootList[i]?.teamObjectId)
//                Log.e("进球数", shootList[i]?.goal.toString())
//            }
            for (i in 0..(matchList.size() - 1)) {
                Log.e("场次", matchList[i]?.matchNumber.toString())
                if (matchList[i]?.groupId!=null) {
                    Log.e("小组", matchList[i]?.groupId)
                }
                Log.e("状态", matchList[i]?.isOver.toString())
                Log.e("时间", matchList[i]?.Time)
                Log.e("时间戳", matchList[i]?.startingTime.toString())
                if (matchList[i]?.homeTeamName != null) {
                    Log.e("主队", matchList[i]?.homeTeamName)
                } else {
                    Log.e("主队", "--")
                }
                if (matchList[i]?.awayTeamName != null) {
                    Log.e("客队", matchList[i]?.awayTeamName)
                } else {
                    Log.e("客队", "--")
                }
                Log.e("比分", matchList[i]?.homeTeamGoals.toString() + ":" + matchList[i]?.awayTeamGoals.toString())

            }

            for (i in 0..7){
                val sbu = StringBuffer()
                sbu.append(Integer.parseInt((i+65).toString()).toChar())
                Log.e("小组:",sbu.toString())
                for (j in 0..(teamList[i].size() - 1)){
                    Log.e("球队",teamList[i][j]?.name)
                    Log.e("胜场",teamList[i][j]?.win.toString())
                    Log.e("平场",teamList[i][j]?.draw.toString())
                    Log.e("负场",teamList[i][j]?.loss.toString())
                    Log.e("净胜球",teamList[i][j]?.goalDifferent.toString())
                    Log.e("积分",teamList[i][j]?.integral.toString())

                }
            }
            val a:Int=3/2
            Log.e("计算测试:",a.toString())

            shooterListModel.clearList()
            matchListModel.clearList()
            leagueTableModel.clearList()
            time *= 1000L
//            test()
//            Log.e("size", a.size().toString())
//            Log.e("a.1", a[0].toString())
        }
        button2.setOnClickListener {
            time = parseTime(edittext.text.toString())
            textview.text = aDate.format(time)

        }

    }


}
