package com.example.sunil.assignmentkb

import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


class CustomAdapter(private var context: Context, private var data: ArrayList<AlbumPhoto>) : BaseAdapter() {

    private val mInflator: LayoutInflater = LayoutInflater.from(context)

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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val vh: ListRowHolder
        if (convertView == null) {
            view = this.mInflator.inflate(R.layout.item_layout, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }
        Glide.with(context)
                .load(data[position].thumbnailUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(vh.icon)
        vh.title.text = data[position].title
        return view!!
    }

    inner class ListRowHolder(row: View?) {
         val icon: ImageView = row?.findViewById(R.id.icon) as ImageView
         val title: TextView = row?.findViewById(R.id.title) as TextView
    }
}