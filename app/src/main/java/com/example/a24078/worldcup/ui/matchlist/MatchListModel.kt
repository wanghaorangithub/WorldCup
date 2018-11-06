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
import com.example.a24078.worldcup.ui.leaguetable.LeagueTableModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by haoran on 2018/10/8.
 */
class MatchListModel(private val context: Context) {
    private var matchList: LinkedList<MatchBean> = LinkedList()
    private var teamList: LinkedList<TeamBean> = LinkedList()
    private var eventList: LinkedList<EventBean> = LinkedList()
    private var playerList: LinkedList<PlayerBean> = LinkedList()
    private var matchListForTree: LinkedList<MatchBean> = LinkedList()
    private var leagueTableModel: LeagueTableModel = LeagueTableModel(context)

    @SuppressLint("LogNotTimber")
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

    private fun refreshTeamName(time: Long): LinkedList<MatchBean> {
        leagueTableModel.setUpList()
        val teamList: ArrayList<LinkedList<TeamBean>> = leagueTableModel.getLeagueTableByIntegral(time)
        matchListForTree.add(matchList[63]!!)
        matchListForTree.add(matchList[60]!!)
        matchListForTree.add(matchList[61]!!)
        matchListForTree.add(matchList[56]!!)
        matchListForTree.add(matchList[57]!!)
        matchListForTree.add(matchList[59]!!)
        matchListForTree.add(matchList[58]!!)
        if (time >= 1530219600) {//2018-06-29 05:00:00
            matchList[49]?.awayTeamId = teamList[0][0]?.objectId!!
            matchList[49]?.homeTeamId = teamList[1][1]?.objectId!!
            matchListForTree.add(matchList[49]!!)

            matchList[48]?.awayTeamId = teamList[2][0]?.objectId!!
            matchList[48]?.homeTeamId = teamList[3][1]?.objectId!!
            matchListForTree.add(matchList[48]!!)

            matchList[52]?.awayTeamId = teamList[4][0]?.objectId!!
            matchList[52]?.homeTeamId = teamList[5][1]?.objectId!!
            matchListForTree.add(matchList[52]!!)

            matchList[53]?.awayTeamId = teamList[6][0]?.objectId!!
            matchList[53]?.homeTeamId = teamList[7][1]?.objectId!!
            matchListForTree.add(matchList[53]!!)

            matchList[50]?.awayTeamId = teamList[1][0]?.objectId!!
            matchList[50]?.homeTeamId = teamList[0][1]?.objectId!!
            matchListForTree.add(matchList[50]!!)

            matchList[51]?.awayTeamId = teamList[3][0]?.objectId!!
            matchList[51]?.homeTeamId = teamList[2][1]?.objectId!!
            matchListForTree.add(matchList[51]!!)

            matchList[54]?.awayTeamId = teamList[5][0]?.objectId!!
            matchList[54]?.homeTeamId = teamList[4][1]?.objectId!!
            matchListForTree.add(matchList[54]!!)

            matchList[55]?.awayTeamId = teamList[7][0]?.objectId!!
            matchList[55]?.homeTeamId = teamList[6][1]?.objectId!!
            matchListForTree.add(matchList[55]!!)


        } else {
            matchList[49]?.awayTeamId = "1A"
            matchList[49]?.homeTeamId = "2B"
            matchListForTree.add(matchList[49]!!)

            matchList[48]?.awayTeamId = "1C"
            matchList[48]?.homeTeamId = "2D"
            matchListForTree.add(matchList[48]!!)

            matchList[52]?.awayTeamId = "1E"
            matchList[52]?.homeTeamId = "2F"
            matchListForTree.add(matchList[52]!!)

            matchList[53]?.awayTeamId = "1G"
            matchList[53]?.homeTeamId = "2H"
            matchListForTree.add(matchList[53]!!)

            matchList[50]?.awayTeamId = "1B"
            matchList[50]?.homeTeamId = "2A"
            matchListForTree.add(matchList[50]!!)

            matchList[51]?.awayTeamId = "1D"
            matchList[51]?.homeTeamId = "2C"
            matchListForTree.add(matchList[51]!!)

            matchList[54]?.awayTeamId = "1F"
            matchList[54]?.homeTeamId = "2E"
            matchListForTree.add(matchList[54]!!)

            matchList[55]?.awayTeamId = "1H"
            matchList[55]?.homeTeamId = "2G"
            matchListForTree.add(matchList[55]!!)
        }

        getTeamName(time)
        matchListForTree.add(matchList[62]!!)
        val matchListForTree1 = sortMatchLinkedList(matchListForTree)
        val matchListResult: LinkedList<MatchBean> = LinkedList()
        for (i in 0..47) {
            matchListResult.add(matchList[i]!!)
        }
        for (i in 0..15)
            matchListResult.add(matchListForTree1[i]!!)
        leagueTableModel.clearList()
        return matchListResult
    }

    fun clearList() {
        matchList.clear()
        teamList.clear()
        eventList.clear()
        playerList.clear()
        matchListForTree.clear()
    }

    @SuppressLint("SimpleDateFormat")
    fun getMatchListModel(time: Long): LinkedList<MatchBean> {
        var m = -1
        var n = -1

        //更新淘汰赛赛程
        matchList = refreshTeamName(time)

        //更新小组信息
        for (i in 0..(matchList.size() - 1)) {
            val homeTeamId = matchList[i]?.homeTeamId
            val awayTeamId = matchList[i]?.awayTeamId
            //找到小组名和具体队名
            //主队
            if (matchList[i]?.homeTeamId != null) {
                if (matchList[i]?.homeTeamId?.length!! > 2) {//如果球队名字已经确定
                    do {
                        m++
                        if (teamList[m]?.objectId == homeTeamId) {
                            if (matchList[i]?.isGroupMatch!!) {
                                matchList[i]?.groupId = teamList[m]?.group
                            }
                            matchList[i]?.homeTeamName = teamList[m]?.name
                        }
                    } while (teamList[m]?.objectId != homeTeamId)
                }
            }
            //客队
            if (matchList[i]?.awayTeamId != null) {
                if (matchList[i]?.awayTeamId?.length!! > 2) {//如果球队名字已经确定
                    do {
                        n++
                        if (teamList[n]?.objectId == awayTeamId) {
                            matchList[i]?.awayTeamName = teamList[n]?.name
                        }
                    } while (teamList[n]?.objectId != awayTeamId)
                }
            }


            //更新正在进行的比赛的比分
            if (matchList[i]?.startingTime!! <= time && (matchList[i]?.startingTime!! + 10800L) > time) {
                //需要更新，先清零
                matchList[i]?.homeTeamGoals = 0
                matchList[i]?.awayTeamGoals = 0
                matchList[i]?.isOver = 0
                //根据事件修改比分
                for (j in 0..(eventList.size() - 1)) {
                    if (eventList[j]?.eventType == "g" && eventList[j]!!.time.toInt() <= time && eventList[j]!!.matchId == matchList[i]?.objectId) {

                        //如果是进球，找到球员
                        for (x in 0..(playerList.size() - 1)) {
                            if (playerList[x]?.objectId == eventList[j]?.playerId) {
                                //找到球员后根据球员所属球队更新比分
                                if (playerList[x]?.teamObjectId == matchList[i]?.homeTeamId) {
                                    matchList[i]!!.homeTeamGoals++
                                } else {
                                    matchList[i]!!.awayTeamGoals++
                                }
                            }
                        }
                    }
                    if (eventList[j]?.eventType == "o" && eventList[j]!!.time <= time.toString() && eventList[j]!!.matchId == matchList[i]?.objectId) {
                        //如果是乌龙球，找到球员，反向加分
                        for (x in 0..(playerList.size() - 1)) {
                            if (playerList[x]?.objectId == eventList[j]?.playerId) {
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
                matchList[i]?.isOver = -1
                matchList[i]?.homeTeamGoals = -1
                matchList[i]?.awayTeamGoals = -1
            }

            m = -1
            n = -1
        }
        //格式化比赛时间
        for (i in 0..(matchList.size() - 1)) {
            val aDate = SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ")
            aDate.timeZone = TimeZone.getTimeZone("GMT+8")
            var time1: Long = matchList[i]?.startingTime!!
            time1 *= 1000L
            matchList[i]?.Time = aDate.format(time1)
        }


        return matchList
    }


    @SuppressLint("LogNotTimber")
    private fun getTeamName(time: Long) {
        for (i in 14 downTo 1) {
            if ((matchListForTree[i]?.startingTime!! + 10800L) < time && i % 2 == 1) {
                Log.e("child",i.toString())
                Log.e("parent",(i/2).toString())
                if (matchListForTree[i]?.homeTeamGoals!! > matchListForTree[i]?.awayTeamGoals!!)
                    matchListForTree[i / 2]?.awayTeamId = matchListForTree[i]?.homeTeamId!!
                if (matchListForTree[i]?.homeTeamGoals!! < matchListForTree[i]?.awayTeamGoals!!)

                    matchListForTree[i / 2]?.awayTeamId = matchListForTree[i]?.awayTeamId!!
            }
            if ((matchListForTree[i]?.startingTime!! + 10800L) < time && i % 2 == 0) {
                Log.e("child",i.toString())
                Log.e("parent",((i-1)/2).toString())
                if (matchListForTree[i]?.homeTeamGoals!! > matchListForTree[i]?.awayTeamGoals!!)
                    matchListForTree[(i - 1) / 2]?.homeTeamId = matchListForTree[i]?.homeTeamId!!
                if (matchListForTree[i]?.homeTeamGoals!! < matchListForTree[i]?.awayTeamGoals!!)
                    matchListForTree[(i - 1) / 2]?.homeTeamId = matchListForTree[i]?.awayTeamId!!
            }
        }
//        处理季军赛
        if ((matchList[60]?.startingTime!! + 10800L) < time) {
            if (matchList[60]?.homeTeamGoals!! < matchList[60]?.awayTeamGoals!!)
                matchList[62]?.awayTeamId = matchList[60]?.homeTeamId!!
            if (matchList[60]?.homeTeamGoals!! > matchList[60]?.awayTeamGoals!!)
                matchList[62]?.awayTeamId = matchList[60]?.awayTeamId!!
        }
        if ((matchList[61]?.startingTime!! + 10800L) < time) {
            if (matchList[61]?.homeTeamGoals!! < matchList[61]?.awayTeamGoals!!)
                matchList[62]?.homeTeamId = matchList[61]?.homeTeamId!!
            if (matchList[61]?.homeTeamGoals!! > matchList[61]?.awayTeamGoals!!)
                matchList[62]?.homeTeamId = matchList[61]?.awayTeamId!!
        }
    }


    private fun sortMatchLinkedList(matchLinkedList: LinkedList<MatchBean>): LinkedList<MatchBean> {
        var temp: MatchBean
        //选择排序
        for (i in 0..(matchLinkedList.size() - 2)) {
            var min = i
            for (j in (min + 1)..(matchLinkedList.size() - 1))
                if (matchLinkedList[j]?.startingTime!! < matchLinkedList[min]?.startingTime!!)
                    min = j
            if (i != min) {
                temp = matchLinkedList[i]!!
                matchLinkedList[i] = matchLinkedList[min]
                matchLinkedList[min] = temp
            }
        }
        return matchLinkedList
    }
}
