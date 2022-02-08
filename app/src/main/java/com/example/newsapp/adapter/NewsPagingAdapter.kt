package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.BR
import com.example.newsapp.databinding.ListItemBinding
import com.example.newsapp.retrofit.responce.Article
import kotlinx.android.synthetic.main.list_item.view.*

class NewsPagingAdapter:PagingDataAdapter<Article,NewsPagingAdapter.Myviewholder>(DIFF_UTIL) {

    companion object{
        val DIFF_UTIL=object :DiffUtil.ItemCallback<Article>(){
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem

            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title ==newItem.title

            }

        }
    }
    inner class Myviewholder(val viewDataBinding: ViewDataBinding):RecyclerView.ViewHolder(viewDataBinding.root)



    override fun onBindViewHolder(holder: NewsPagingAdapter.Myviewholder, position: Int) {
        val item=getItem(position)
        holder.viewDataBinding.setVariable(BR.article,item)
        Glide.with(holder.viewDataBinding.root).load(item!!.urlToImage).
        into(holder.viewDataBinding.root.image_list_item)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsPagingAdapter.Myviewholder {
       val binding= ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Myviewholder(binding)
    }
}