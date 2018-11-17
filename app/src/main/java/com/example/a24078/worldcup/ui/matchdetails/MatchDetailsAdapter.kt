package com.example.a24078.worldcup.ui.matchdetails

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.a24078.worldcup.R
import com.example.a24078.worldcup.entity.EventBean
import com.example.a24078.worldcup.support.dataStructure.linkedlist.LinkedList
import kotlinx.android.synthetic.main.item_matchdetails.view.*

/**
 * Created by haoran on 2018/11/9.
 */
class MatchDetailsAdapter(private val eventList: LinkedList<EventBean>) : RecyclerView.Adapter<MatchDetailsAdapter.ViewHodler>() {
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHodler, position: Int) {
        holder.bind(eventList[position]!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHodler {
        return ViewHodler(View.inflate(parent?.context, R.layout.item_matchdetails, null))
    }

    override fun getItemCount(): Int {
        return eventList.size()
    }

    class ViewHodler(private val item: View) : RecyclerView.ViewHolder(item) {


        @SuppressLint("SetTextI18n")
        fun bind(event: EventBean) {
            if (event.eventType == "g")
                item.item_matchdetials_textview.text = event.time + "分钟 " + event.teamId + event.playerId + "打进一球"
            if (event.eventType == "o")
                item.item_matchdetials_textview.text = event.time + "分钟 " + event.teamId + event.playerId + "不慎乌龙"
        }
    }
}