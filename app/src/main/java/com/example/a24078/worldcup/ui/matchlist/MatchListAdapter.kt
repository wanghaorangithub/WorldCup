package com.example.a24078.worldcup.ui.matchlist

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.a24078.worldcup.R
import com.example.a24078.worldcup.entity.MatchBean
import com.example.a24078.worldcup.support.dataStructure.linkedlist.LinkedList
import com.example.a24078.worldcup.ui.matchdetails.MatchDetailsActivity
import kotlinx.android.synthetic.main.item_matchlist.view.*

/**
 * Created by haoran on 2018/11/7.
 */
class MatchListAdapter(private val matchList: LinkedList<MatchBean>, private val activity: MatchListActivity, val time: Long) : RecyclerView.Adapter<MatchListAdapter.ViewHodler>() {
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHodler, position: Int) {
        holder.bind(matchList[position]!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHodler {
        return ViewHodler(View.inflate(parent?.context, R.layout.item_matchlist, null))
    }

    override fun getItemCount(): Int {
        return matchList.size()
    }

    inner class ViewHodler(private val item: View) : RecyclerView.ViewHolder(item) {
        private val view: View = item.findViewById(R.id.item_matchlist_relativelayout)
        private var match: MatchBean? = null
        private var team: String? = null
        private var goal: String? = null

        init {
            view.setOnClickListener {
                activity.startActivity(MatchDetailsActivity.newIntent(
                        packageContext = activity,
                        time = time,
                        matchId = match?.objectId!!,
                        startingTime = match?.startingTime!!,
                        team = team!!,
                        goal = goal!!))
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(match: MatchBean) {
            if (match.awayTeamName != null && match.homeTeamName != null) {
                item.matchlist_item_textview1.text = "第" + match.matchNumber.toString() + "场 " + match.awayTeamName + ":" + match.homeTeamName
            }
            if (match.awayTeamName != null && match.homeTeamName == null) {
                item.matchlist_item_textview1.text = match.awayTeamName + ":" + "待定"
            }
            if (match.awayTeamName == null && match.homeTeamName != null) {
                item.matchlist_item_textview1.text = "待定" + ":" + match.homeTeamName
            }
            if (match.awayTeamName == null && match.homeTeamName == null) {
                item.matchlist_item_textview1.text = "待定" + ":" + "待定"
            }
            if (match.homeTeamGoals >= 0 && match.isOver == null) {
                item.matchlist_item_textview2.text = match.awayTeamGoals.toString() + ":" + match.homeTeamGoals.toString() + "        已结束"
            }
            if (match.homeTeamGoals >= 0 && match.isOver == 0) {
                item.matchlist_item_textview2.text = match.awayTeamGoals.toString() + ":" + match.homeTeamGoals.toString() + "        进行中"
            }
            if (match.homeTeamGoals < 0) {
                item.matchlist_item_textview2.text = "未开始"
            }

            item.matchlist_item_textview3.text = match.Time

            if (match.isGroupMatch) {
                item.matchlist_item_textview4.text = "小组赛：" + match.groupId + "组"
            }
            if (!match.isGroupMatch) {
                item.matchlist_item_textview4.text = "淘汰赛"
            }
            this.match = match
            this.team = item.matchlist_item_textview1.text.toString()
            this.goal = item.matchlist_item_textview2.text.toString()

        }


    }
}





