package com.wallsc.sixth.scape.sixthwallpaper.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wallsc.sixth.scape.sixthwallpaper.R
import com.wallsc.sixth.scape.sixthwallpaper.base.App

class WSSlPgssaperAdapter(private val type: Int, private var dataList: List<String>) :
    RecyclerView.Adapter<WSSlPgssaperAdapter.ViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgItem: AppCompatImageView = itemView.findViewById(R.id.aiv_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = if (type == 1) {
            inflater.inflate(R.layout.item_top, parent, false)
        } else {
            inflater.inflate(R.layout.item_bottom, parent, false)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        Glide.with(App.appContext)
            .load(SixthUtils.getImgByName(data))
            .thumbnail(0.12f)
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)
            .into(holder.imgItem)
        holder.itemView.setOnClickListener {
            listener?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    // Method to update the dataset
    fun updateData(newData: List<String>) {
        dataList = newData
        notifyDataSetChanged()
    }
}