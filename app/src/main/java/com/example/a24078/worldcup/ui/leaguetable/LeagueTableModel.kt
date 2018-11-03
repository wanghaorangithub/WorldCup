package com.example.a24078.worldcup.ui.leaguetable

import com.example.a24078.worldcup.entity.EventBean
import com.example.a24078.worldcup.entity.MatchBean
import com.example.a24078.worldcup.entity.PlayerBean
import com.example.a24078.worldcup.entity.TeamBean
import com.example.a24078.worldcup.support.dataStructure.linkedlist.LinkedList

/**
 * Created by haoran on 2018/10/21.
 */
class LeagueTableModel {
    private var matchList: LinkedList<MatchBean> = LinkedList()
    private var teamList: LinkedList<TeamBean> = LinkedList()
    private var eventList: LinkedList<EventBean> = LinkedList()
    private var playerList: LinkedList<PlayerBean> = LinkedList()
    fun setUpList() {

    }

    fun clearList() {
        matchList.clear()
        teamList.clear()
        eventList.clear()
        playerList.clear()
    }

    fun refreshIntegral(time: Int){
        for (i in 0..(matchList.size() - 1)) {
            //由于没有endtime所以这个地方只能先这样，其实是不准的
            if (matchList[i]?.startingTime!! < time) {

                //主胜
                if (matchList[i]?.homeTeamGoals!! > matchList[i]?.awayTeamGoals!!) {
                    val teamId = matchList[i]?.homeTeamId
                    for (m in 0..(teamList.size() - 1)) {
                        if (teamList[m]?.objectId == teamId)
                            teamList[m]?.integral = teamList[m]?.integral!! + 3
                    }
                }
                //平
                if (matchList[i]?.homeTeamGoals!! == matchList[i]?.awayTeamGoals!!) {
                    val teamId1 = matchList[i]?.homeTeamId
                    val teamId2 = matchList[i]?.awayTeamId
                    for (m in 0..(teamList.size() - 1)) {
                        if (teamList[m]?.objectId == teamId1 || teamList[m]?.objectId == teamId2) {
                            teamList[m]!!.integral++
                        }
                    }
                }
                //客胜
                if (matchList[i]?.homeTeamGoals!! < matchList[i]?.awayTeamGoals!!) {
                    val teamId = matchList[i]?.awayTeamId
                    for (m in 0..(teamList.size() - 1)) {
                        if (teamList[m]?.objectId == teamId)
                            teamList[m]?.integral = teamList[m]?.integral!! + 3
                    }
                }
            }
        }
    }
    fun sortTeamLinkedList(teamLinkedList:LinkedList<TeamBean>){
        for (i in 0..(teamLinkedList.size()-1)){
            teamLinkedList[i]?.integral
        }
    }
    fun getLeagueTableByIntegral(time: Int): LinkedList<TeamBean> {
        //更新每队积分
        refreshIntegral(time)
        //根据积分排序
        val teamListToSort:ArrayList<LinkedList<TeamBean>> = ArrayList()
        //为8个小组创建LinkedList放入ArrayList
        for(i in 0..7){
            val teamLinkListToSort:LinkedList<TeamBean> = LinkedList()
            teamListToSort.add(teamLinkListToSort)
        }
        for (i in 0..(teamList.size() - 1)){
//            teamListToSort[teamList[i]]
        }




        return teamList
    }

}

