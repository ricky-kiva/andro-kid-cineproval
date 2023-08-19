package com.rickyslash.kidcineproval.core.ui

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.rickyslash.kidcineproval.R
import com.rickyslash.kidcineproval.databinding.ItemListMovieBinding
import com.rickyslash.kidcineproval.core.domain.model.Movie
import com.rickyslash.kidcineproval.core.utils.dateKebabToSentence

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMovieBinding.bind(itemView)
        fun bind(data: Movie) {
            with(binding) {
                tvListItemTitle.text = data.title
                tvListItemDate.text = dateKebabToSentence(data.date)

                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${data.backdrop}")
                    .into(object : CustomTarget<Drawable>() {
                        override fun onResourceReady(
                            resource: Drawable, transition:
                            Transition<in Drawable>?
                        ) {
                            rlContainer.background = resource
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {}
                    })
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false))
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

}