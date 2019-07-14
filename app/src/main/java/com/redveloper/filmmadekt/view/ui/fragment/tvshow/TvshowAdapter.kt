package com.redveloper.filmmadekt.view.ui.fragment.tvshow

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.redveloper.filmmadekt.R
import com.redveloper.filmmadekt.model.tvshow.ResponTvshow
import kotlinx.android.synthetic.main.list_item.view.*

class TvshowAdapter(val items : ArrayList<ResponTvshow.ResultTvShow>) : RecyclerView.Adapter<TvshowAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.list_item, p0, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.binding(items.get(p1))
    }

    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun binding(data: ResponTvshow.ResultTvShow) {
            itemView.textview_title_list.setText(data.name)
            itemView.textview_description_list.setText(data.overview)
            Glide.with(itemView.context)
                .load(itemView.context.resources.getString(R.string.BASE_IMAGE) + data.poster_path)
                .into(itemView.imageview_list)
        }
    }

}