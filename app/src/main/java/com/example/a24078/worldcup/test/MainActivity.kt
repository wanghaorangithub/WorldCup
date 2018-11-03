package com.example.a24078.worldcup.test

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.a24078.worldcup.R
import com.example.a24078.worldcup.entity.MatchBean
import com.example.a24078.worldcup.entity.PlayerBean
import com.example.a24078.worldcup.support.dataStructure.linkedlist.LinkedList
import com.example.a24078.worldcup.ui.matchlist.MatchListModel
import com.example.a24078.worldcup.ui.shooterList.ShooterListModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    internal var a = LinkedList<Int>()
    private var b = LinkedList<Int>()
    var shooterListModel: ShooterListModel = ShooterListModel(this)
    var matchListModel: MatchListModel = MatchListModel(this)
    var time: Int = 1531751672

    @SuppressLint("LogNotTimber")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textview.text = time.toString()
        button1.setOnClickListener {
            shooterListModel.setUpList()
            matchListModel.setUpList()
            val matchList: LinkedList<MatchBean> = matchListModel.getMatchListModel(time)
            val shootList: LinkedList<PlayerBean> = shooterListModel.getShooterListByEvent(time)

//            for (i in 0..(shootList.size() - 1)) {
//                Log.e("序号",i.toString())
//                Log.e("姓名", shootList[i]?.playerName)
//                Log.e("球队", shootList[i]?.teamObjectId)
//                Log.e("进球数", shootList[i]?.goal.toString())
//            }
            for (i in 0..(matchList.size() - 1)) {
                Log.e("场次", matchList[i]?.matchNumber.toString())
                Log.e("时间", matchList[i]?.Time)
                Log.e("时间戳", matchList[i]?.startingTime.toString())
                Log.e("主队", matchList[i]?.homeTeamName)
                Log.e("客队", matchList[i]?.awayTeamName)
                Log.e("比分", matchList[i]?.homeTeamGoals.toString() + ":" + matchList[i]?.awayTeamGoals.toString())

            }
            shooterListModel.clearList()
            matchListModel.clearList()
//            test()
//            Log.e("size", a.size().toString())
//            Log.e("a.1", a[0].toString())
        }
        button2.setOnClickListener {
            time = Integer.parseInt(edittext.text.toString())
            textview.text = time.toString()
        }

    }


}
