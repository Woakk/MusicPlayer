package com.example.lecteurmusique.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.lecteurmusique.R
import com.example.lecteurmusique.model.MusicPlayerModel

class MusicPlayerAdapter(var context: Context, var musics: ArrayList<MusicPlayerModel>): BaseAdapter() {
    override fun getCount(): Int {
        return musics.size
    }

    override fun getItem(position: Int): MusicPlayerModel {
        return musics[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder", "MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.list_item, parent, false)

        val imageView = rowView.findViewById<ImageView>(R.id.imageView)
        val textView1 = rowView.findViewById<TextView>(R.id.tvItem1)
        val textView2 = rowView.findViewById<TextView>(R.id.tvItem2)
        val textView3 = rowView.findViewById<TextView>(R.id.tvItem3)

        val country = getItem(position)

        imageView.setImageResource(country.image)
        textView1.text = musics[position].title
        textView2.text = musics[position].length
        textView3.text = musics[position].artist

        return rowView

    }
}