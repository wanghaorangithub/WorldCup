package com.example.a24078.worldcup.ui.matchdetails

import android.content.Context
import com.example.a24078.worldcup.entity.EventBean
import com.example.a24078.worldcup.entity.MatchBean
import com.example.a24078.worldcup.entity.PlayerBean
import com.example.a24078.worldcup.entity.TeamBean
import com.example.a24078.worldcup.support.dataStructure.linkedlist.LinkedList
import com.example.a24078.worldcup.support.utils.ReadTxtToJson

/**
 * Created by haoran on 2018/11/8.
 */
class MatchDetailsModel(private val context: Context) {
    private var matchList: LinkedList<MatchBean> = LinkedList()
    private var teamList: LinkedList<TeamBean> = LinkedList()
    private var eventList: LinkedList<EventBean> = LinkedList()
    private var playerList: LinkedList<PlayerBean> = LinkedList()

    fun setUpList() {
        val matchList1: List<MatchBean> = ReadTxtToJson<MatchBean>().getDataFromFileInAssets(context, "match_2", MatchBean::class.java)
        val teamList1: List<TeamBean> = ReadTxtToJson<TeamBean>().getDataFromFileInAssets(context, "team_1", TeamBean::class.java)
        val playerList1: List<PlayerBean> = ReadTxtToJson<PlayerBean>().getDataFromFileInAssets(context, "player_1", PlayerBean::class.java)
        val eventList1: List<EventBean> = ReadTxtToJson<EventBean>().getDataFromFileInAssets(context, "event_1", EventBean::class.java)
        val matchList2 = matchList1.sorted()
        for (i in matchList2) {
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

    fun getEventList(matchId: String, time: Long, StartingTime: Long): LinkedList<EventBean> {
        val eventListResult = LinkedList<EventBean>()
        for (i in 0 until (eventList.size() - 1)) {
            if (eventList[i]?.matchId == matchId && eventList[i]?.time?.toLong()!! <= time) {
                //处理时间
                eventList[i]?.time = (((eventList[i]?.time?.toLong()!! - StartingTime) / 60).toInt()).toString()

                //处理球员姓名
                for (j in 0..(playerList.size() - 1)) {
                    if (eventList[i]?.playerId == playerList[j]?.objectId) {
                        eventList[i]?.playerId = playerList[i]?.shirtNumber.toString()+"号"+playerList[j]?.playerName!!
                        eventList[i]?.teamId = playerList[j]?.teamObjectId
                    }
                }
                //处理队名
                for (j in 0..(teamList.size() - 1)) {
                    if (eventList[i]?.teamId == teamList[j]?.objectId) {
                        eventList[i]?.teamId = teamList[j]?.name
                    }
                }
                eventListResult.add(eventList[i]!!)
            }
        }
        return eventListResult
    }
}

