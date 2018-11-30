package com.example.a24078.worldcup.ui.leaguetable

import android.content.Context
import com.example.a24078.worldcup.entity.EventBean
import com.example.a24078.worldcup.entity.MatchBean
import com.example.a24078.worldcup.entity.PlayerBean
import com.example.a24078.worldcup.entity.TeamBean
import com.example.a24078.worldcup.support.dataStructure.linkedlist.LinkedList
import com.example.a24078.worldcup.support.utils.ReadTxtToJson
import com.example.a24078.worldcup.support.utils.stringToInt

/**
 * Created by haoran on 2018/10/21.
 */
class LeagueTableModel(private val context: Context) {
    private var matchList: LinkedList<MatchBean> = LinkedList()
    private var teamList: LinkedList<TeamBean> = LinkedList()
    private var eventList: LinkedList<EventBean> = LinkedList()
    private var playerList: LinkedList<PlayerBean> = LinkedList()
    fun setUpList() {
        val matchList1: List<MatchBean> = ReadTxtToJson<MatchBean>().getDataFromFileInAssets(context, "match_2", MatchBean::class.java)
        val teamList1: List<TeamBean> = ReadTxtToJson<TeamBean>().getDataFromFileInAssets(context, "team_1", TeamBean::class.java)
        val playerList1: List<PlayerBean> = ReadTxtToJson<PlayerBean>().getDataFromFileInAssets(context, "player_1", PlayerBean::class.java)
        val eventList1: List<EventBean> = ReadTxtToJson<EventBean>().getDataFromFileInAssets(context, "event_1", EventBean::class.java)
        for (i in matchList1) {
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

    private fun refreshIntegral(time: Long) {
        for (i in 0..(matchList.size() - 1)) {
            //由于没有endtime所以这个地方只能先这样，其实是不准的
            if ((matchList[i]?.startingTime!! + 10800L) <= time && matchList[i]?.isGroupMatch == true) {

                //主胜
                if (matchList[i]?.homeTeamGoals!! > matchList[i]?.awayTeamGoals!!) {
                    val homeTeamId = matchList[i]?.homeTeamId
                    val awayTeamId = matchList[i]?.awayTeamId
                    for (m in 0..(teamList.size() - 1)) {
                        if (teamList[m]?.objectId == homeTeamId) {
                            teamList[m]!!.win++
                            teamList[m]?.integral = teamList[m]?.integral!! + 3
                            teamList[m]!!.goalDifferent += matchList[i]?.homeTeamGoals!! - matchList[i]?.awayTeamGoals!!

                        }
                    }
                    for (m in 0..(teamList.size() - 1)) {
                        if (teamList[m]?.objectId == awayTeamId) {
                            teamList[m]!!.loss++
                            teamList[m]!!.goalDifferent += matchList[i]?.awayTeamGoals!! - matchList[i]?.homeTeamGoals!!
                        }
                    }
                }
                //平
                if (matchList[i]?.homeTeamGoals!! == matchList[i]?.awayTeamGoals!!) {
                    val homeTeamId = matchList[i]?.homeTeamId
                    val awayTeamId = matchList[i]?.awayTeamId
                    for (m in 0..(teamList.size() - 1)) {
                        if (teamList[m]?.objectId == homeTeamId || teamList[m]?.objectId == awayTeamId) {
                            teamList[m]!!.integral++
                            teamList[m]!!.draw++
                        }
                    }
                }
                //客胜
                if (matchList[i]?.homeTeamGoals!! < matchList[i]?.awayTeamGoals!!) {
                    val homeTeamId = matchList[i]?.homeTeamId
                    val awayTeamId = matchList[i]?.awayTeamId
                    for (m in 0..(teamList.size() - 1)) {
                        if (teamList[m]?.objectId == awayTeamId) {
                            teamList[m]!!.win++
                            teamList[m]?.integral = teamList[m]?.integral!! + 3
                            teamList[m]!!.goalDifferent += matchList[i]?.awayTeamGoals!! - matchList[i]?.homeTeamGoals!!
                        }
                    }
                    for (m in 0..(teamList.size() - 1)) {
                        if (teamList[m]?.objectId == homeTeamId) {
                            teamList[m]!!.loss++
                            teamList[m]!!.goalDifferent += matchList[i]?.homeTeamGoals!! - matchList[i]?.awayTeamGoals!!
                        }
                    }
                }
            }
        }
    }

    private fun sortTeamLinkedList(teamLinkedList: LinkedList<TeamBean>): LinkedList<TeamBean> {
        var temp: TeamBean
        //选择排序
        for (i in 0..(teamLinkedList.size() - 2)) {
            var max = i
            for (j in (max + 1)..(teamLinkedList.size() - 1))
                if (teamLinkedList[j]?.integral!! > teamLinkedList[max]?.integral!!)
                    max = j
            if (i != max) {
                temp = teamLinkedList[i]!!
                teamLinkedList[i] = teamLinkedList[max]
                teamLinkedList[max] = temp
            }
        }
        return teamLinkedList
    }

    fun getLeagueTableByIntegral(time: Long): ArrayList<LinkedList<TeamBean>> {
        //更新每队积分
        refreshIntegral(time)
        //根据积分排序
        val teamListToSort: ArrayList<LinkedList<TeamBean>> = ArrayList()
        val teamListResult: ArrayList<LinkedList<TeamBean>> = ArrayList()
        //为8个小组创建LinkedList放入ArrayList
        for (i in 0..7) {
            val teamLinkListToSort: LinkedList<TeamBean> = LinkedList()
            teamListToSort.add(teamLinkListToSort)
        }
        //根据组别填入待排链表
        for (i in 0..(teamList.size() - 1)) {
            teamListToSort[stringToInt(teamList[i]?.group!!)].add(teamList[i]!!)
        }

        for (i in 0..7) {
            teamListResult.add(sortTeamLinkedList(teamListToSort[i]))
        }

        return teamListResult
    }

}


