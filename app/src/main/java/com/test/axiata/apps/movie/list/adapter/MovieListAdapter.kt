package com.test.axiata.apps.movie.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.axiata.apps.R
import com.test.axiata.apps.network.response.MovieListResponse
import kotlinx.android.synthetic.main.list_item_movie.view.*

class MovieListAdapter(val context: Context, val listener : onItemClickListener) : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>(){

    var movieList : MutableList<MovieListResponse.MovieList> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_movie, parent, false), listener)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bindItem(movieList[position])
    }

    fun setDataList(data : List<MovieListResponse.MovieList>){
        var size = data.size
        movieList.addAll(data)
        var newSize = movieList.size
        notifyItemRangeChanged(size, newSize)
    }

    fun clearListMovie(){
        movieList.clear()
        notifyDataSetChanged()
    }

    class MovieListViewHolder(itemView : View, val listener : onItemClickListener) : RecyclerView.ViewHolder(itemView){


        var url = "https://image.tmdb.org/t/p/w500"

        fun bindItem(dataList : MovieListResponse.MovieList){
            Glide.with(itemView.context).load(url + dataList.poster_path).placeholder(R.drawable.ic_empty_image).into(itemView.movies_iv)
            itemView.title_tv.text = if(!dataList.name.isNullOrEmpty()) dataList.name else "Null Title"
            itemView.setOnClickListener {
                listener.itemClicked(dataList.id?:0)
            }
        }
    }

    interface onItemClickListener{
        fun itemClicked(movieId : Int)
    }
}