package com.example.a24078.worldcup.ui.leaguetable

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.a24078.worldcup.R
import com.example.a24078.worldcup.entity.TeamBean
import com.example.a24078.worldcup.support.dataStructure.linkedlist.LinkedList
import kotlinx.android.synthetic.main.item_leaguetable.view.*

/**
 * Created by haoran on 2018/11/9.
 */
class LeagueTableAdapter(private val leagueTableList: ArrayList<LinkedList<TeamBean>>) : RecyclerView.Adapter<LeagueTableAdapter.ViewHodler>() {
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHodler, position: Int) {
        holder.bind(leagueTableList[position], position)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHodler {
        return ViewHodler(View.inflate(parent?.context, R.layout.item_leaguetable, null))
    }

    override fun getItemCount(): Int {
        return leagueTableList.size
    }

    inner class ViewHodler(private val item: View) : RecyclerView.ViewHolder(item) {


        @SuppressLint("SetTextI18n")
        fun bind(teamList: LinkedList<TeamBean>, position: Int) {
            val sbu = StringBuffer()
            sbu.append(Integer.parseInt((position + 65).toString()).toChar())
            item.leaguetable_item_textview1.text = sbu.toString()+"        胜 平 负 净胜球 积分"
            for (i in 0..3)
                when (i) {
                    0 -> {
                        item.leaguetable_item_textview2.text = (i + 1).toString() + " " +
                                teamList[i]?.name + " " +
                                teamList[i]?.win.toString() + "    " +
                                teamList[i]?.draw.toString() + "    " +
                                teamList[i]?.loss.toString() + "    " +
                                teamList[i]?.goalDifferent.toString() + "    " +
                                teamList[i]?.integral.toString()
                    }
                    1 -> {
                        item.leaguetable_item_textview3.text = (i + 1).toString() + " " +
                                teamList[i]?.name + " " +
                                teamList[i]?.win.toString() + "    " +
                                teamList[i]?.draw.toString() + "    " +
                                teamList[i]?.loss.toString() + "    " +
                                teamList[i]?.goalDifferent.toString() + "    " +
                                teamList[i]?.integral.toString()

                    }
                    2 -> {
                        item.leaguetable_item_textview4.text = (i + 1).toString() + " " +
                                teamList[i]?.name + "    " +
                                teamList[i]?.win.toString() + "    " +
                                teamList[i]?.draw.toString() + "    " +
                                teamList[i]?.loss.toString() + "    " +
                                teamList[i]?.goalDifferent.toString() + "    " +
                                teamList[i]?.integral.toString()

                    }
                    3 -> {
                        item.leaguetable_item_textview5.text = (i + 1).toString() + " " +
                                teamList[i]?.name + "    " +
                                teamList[i]?.win.toString() + "    " +
                                teamList[i]?.draw.toString() + "    " +
                                teamList[i]?.loss.toString() + "    " +
                                teamList[i]?.goalDifferent.toString() + "    " +
                                teamList[i]?.integral.toString()

                    }
                }

        }
    }
}
