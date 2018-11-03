package com.example.a24078.worldcup.ui.shooterList

import android.content.Context
import com.example.a24078.worldcup.entity.EventBean
import com.example.a24078.worldcup.entity.PlayerBean
import com.example.a24078.worldcup.support.dataStructure.linkedlist.LinkedList
import com.example.a24078.worldcup.support.utils.ReadTxtToJson

/**
 * Created by haoran on 2018/9/28.
 */
class ShooterListModel(val context: Context) {
    private var playerList: LinkedList<PlayerBean> = LinkedList()
    private var eventList: LinkedList<EventBean> = LinkedList()

    fun setUpList() {
        val playerList1: List<PlayerBean> = ReadTxtToJson<PlayerBean>().getDataFromFileInAssets(context, "player_1", PlayerBean::class.java)
        val eventList1: List<EventBean> = ReadTxtToJson<EventBean>().getDataFromFileInAssets(context, "event_1", EventBean::class.java)
        for (i in playerList1)
            playerList.add(i)
        for (i in eventList1)
            eventList.add(i)
    }

    fun clearList() {
        playerList.clear()
        eventList.clear()
    }

    private fun sortPlayerListByGoals(playerList: LinkedList<PlayerBean>): LinkedList<PlayerBean> {
        val shooterList: LinkedList<PlayerBean> = LinkedList()
        var maxGoals = 1
        for (i in 0..(playerList.size() - 1)) {
            if (playerList[i]?.goal!! > maxGoals) {
                maxGoals = playerList[i]!!.goal
            }
        }
        //桶排序（改）
        val goalList: ArrayList<LinkedList<PlayerBean>> = ArrayList()
        //根据最高进球数动态创建桶
        for (i in 0..(maxGoals - 1)) {
            val goalLinkedList: LinkedList<PlayerBean> = LinkedList()
            goalList.add(goalLinkedList)
        }
        //根据球员进球数，将球员放入相应桶中
        for (i in 0..(playerList.size() - 1)) {
            if (playerList[i]?.goal != 0) {
                val position = maxGoals - playerList[i]!!.goal//倒序
                playerList[i]?.let { goalList[position].add(it) }
            }
        }
        //将所有的数据连在一起
        for (i in 0..(maxGoals - 1)) {
            shooterList.addAll(goalList[i])
        }
        return shooterList
    }

    fun getShooterListByEvent(time: Int): LinkedList<PlayerBean> {
        var m = -1
        //更新球员进球数
        for (i in 0..eventList.size()) {
            if (eventList[i]?.eventType == "g" && eventList[i]!!.time.toInt() <= time) {
                do {
                    m++
                    if (playerList[m]?.objectId == eventList[i]?.playerId) {
                        playerList[m]!!.goal++
                    }
                } while (playerList[m]?.objectId != eventList[i]?.playerId)
            }
            m = -1//重置m
        }
        return sortPlayerListByGoals(playerList)
    }
}


