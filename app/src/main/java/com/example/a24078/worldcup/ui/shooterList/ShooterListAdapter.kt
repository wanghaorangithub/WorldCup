package com.example.a24078.worldcup.ui.shooterList

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.a24078.worldcup.R
import com.example.a24078.worldcup.entity.PlayerBean
import com.example.a24078.worldcup.support.dataStructure.linkedlist.LinkedList
import kotlinx.android.synthetic.main.item_shooterlist.view.*

/**
 * Created by haoran on 2018/11/9.
 */
class ShooterListAdapter(private val playerList: LinkedList<PlayerBean>) : RecyclerView.Adapter<ShooterListAdapter.ViewHodler>() {
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHodler, position: Int) {
        holder.bind(playerList[position]!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHodler {
        return ViewHodler(View.inflate(parent?.context, R.layout.item_shooterlist, null))
    }

    override fun getItemCount(): Int {
        return playerList.size()
    }

    inner class ViewHodler(private val item: View) : RecyclerView.ViewHolder(item) {


        @SuppressLint("SetTextI18n")
        fun bind(player: PlayerBean) {
            item.shooterlist_item_textview1.text = player.teamObjectId
            item.shooterlist_item_textview2.text = player.shirtNumber.toString() + "号" + player.playerName
            item.shooterlist_item_textview3.text = player.goal.toString() + "球"
        }

    }
}