package com.example.sunil.assignmentkb

import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


class CustomAdapter(private var context: Context, internal var data: ArrayList<AlbumPhoto>) : BaseAdapter() {

    private var inflter: LayoutInflater = LayoutInflater.from(context)
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(i: Int): Any? {
        return null
    }

    override fun getItemId(i: Int): Long {
        return 0
    }
    fun setData( list: ArrayList<AlbumPhoto>){
        data.clear()
        data = list
        notifyDataSetChanged()
    }

    override fun getView(i: Int, view: View, viewGroup: ViewGroup): View {
        var view = view
        view = inflter.inflate(R.layout.item_layout, null) // inflate the layout
        val icon = view.findViewById(R.id.icon) as ImageView
        val title = view.findViewById(R.id.title) as TextView
        Glide.with(context)
                .load(data.get(i).thumbnailUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(icon);
        title.text =  data.get(i).title
        return view
    }
}