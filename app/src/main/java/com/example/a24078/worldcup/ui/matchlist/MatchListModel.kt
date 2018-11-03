package com.example.a24078.worldcup.ui.matchlist

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.a24078.worldcup.entity.EventBean
import com.example.a24078.worldcup.entity.MatchBean
import com.example.a24078.worldcup.entity.PlayerBean
import com.example.a24078.worldcup.entity.TeamBean
import com.example.a24078.worldcup.support.dataStructure.linkedlist.LinkedList
import com.example.a24078.worldcup.support.utils.ReadTxtToJson
import java.text.SimpleDateFormat

/**
 * Created by haoran on 2018/10/8.
 */
class MatchListModel(val context: Context) {
    private var matchList: LinkedList<MatchBean> = LinkedList()
    private var teamList: LinkedList<TeamBean> = LinkedList()
    private var eventList: LinkedList<EventBean> = LinkedList()
    private var playerList: LinkedList<PlayerBean> = LinkedList()

    @SuppressLint("LogNotTimber")
    fun setUpList() {
        val matchList1: List<MatchBean> = ReadTxtToJson<MatchBean>().getDataFromFileInAssets(context, "match_1", MatchBean::class.java)
        val teamList1: List<TeamBean> = ReadTxtToJson<TeamBean>().getDataFromFileInAssets(context, "team_1", TeamBean::class.java)
        val playerList1: List<PlayerBean> = ReadTxtToJson<PlayerBean>().getDataFromFileInAssets(context, "player_1", PlayerBean::class.java)
        val eventList1: List<EventBean> = ReadTxtToJson<EventBean>().getDataFromFileInAssets(context, "event_1", EventBean::class.java)
        val matchList2 = matchList1.sorted()
        for (i in matchList2) {
            Log.e("matchList_1", i.matchNumber.toString())
            matchList.add(i)
        }
        for (i in teamList1)
            teamList.add(i)
        for (i in playerList1)
            playerList.add(i)
        for (i in eventList1)
            eventList.add(i)

    }

    fun clearList() {
        matchList.clear()
        teamList.clear()
        eventList.clear()
        playerList.clear()
    }

    @SuppressLint("SimpleDateFormat")
    fun getMatchListModel(time: Int): LinkedList<MatchBean> {
        var m = -1
        var n = -1
        //更新小组信息
        for (i in 0..(matchList.size() - 1)) {
            val homeTeamId = matchList[i]?.homeTeamId
            val awayTeamId = matchList[i]?.awayTeamId
            //找到小组名和具体队名
            //主队
            do {
                m++
                if (teamList[m]?.objectId == homeTeamId) {
                    matchList[i]?.groupId = teamList[m]?.group
                    matchList[i]?.homeTeamName = teamList[m]?.name
                }
            } while (teamList[m]?.objectId != homeTeamId)
            //客队
            do {
                n++
                if (teamList[n]?.objectId == awayTeamId) {
                    matchList[i]?.awayTeamName = teamList[n]?.name
                }
            } while (teamList[n]?.objectId != awayTeamId)

            //更新正在进行的比赛的比分
            if (matchList[i]?.startingTime!! <= time) {
                //需要更新，先清零
                matchList[i]?.homeTeamGoals = 0
                matchList[i]?.awayTeamGoals = 0
                //根据事件修改比分
                for (n in 0..(eventList.size() - 1)) {
                    if (eventList[n]?.eventType == "g" && eventList[n]!!.time.toInt() <= time && eventList[n]!!.matchId == matchList[i]?.objectId) {

                        //如果是进球，找到球员
                        for (x in 0..(playerList.size() - 1)) {
                            if (playerList[x]?.objectId == eventList[n]?.playerId) {
                                //找到球员后根据球员所属球队更新比分
                                if (playerList[x]?.teamObjectId == matchList[i]?.homeTeamId) {
                                    matchList[i]!!.homeTeamGoals++
                                } else {
                                    matchList[i]!!.awayTeamGoals++
                                }
                            }
                        }
                    }
                    if (eventList[n]?.eventType == "o" && eventList[n]!!.time.toInt() <= time && eventList[n]!!.matchId == matchList[i]?.objectId) {
                        //如果是乌龙球，找到球员，反向加分
                        for (x in 0..(playerList.size() - 1)) {
                            if (playerList[x]?.objectId == eventList[n]?.playerId) {
                                //找到球员后根据球员所属球队更新比分
                                if (playerList[x]?.teamObjectId == matchList[i]?.homeTeamId) {
                                    matchList[i]!!.awayTeamGoals++
                                } else {
                                    matchList[i]!!.homeTeamGoals++
                                }
                            }
                        }
                    }
                }
            }
            //比赛没有开始，不显示数据
            if (matchList[i]?.startingTime!! > time) {
                matchList[i]?.homeTeamGoals = -1
                matchList[i]?.awayTeamGoals = -1
            }

            m = -1
            n = -1
        }
        //格式化比赛时间
        for (i in 0..(matchList.size() - 1)) {
            val aDate = SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ")
            var time:Long= matchList[i]?.startingTime!!
            time *= 1000L
            matchList[i]?.Time = aDate.format(time)
        }
        return matchList
    }
}
