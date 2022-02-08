package com.example.newsapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.NewsViewmodel
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news.view.*


//bf7cef9c0fb34cb193f2abc57202d237 Api key
@AndroidEntryPoint
class NewsFragment : Fragment() {
    //    lateinit var news_progress:ProgressBar
//    lateinit var news_recycler:RecyclerView
    private val viewmodel by viewModels<NewsViewmodel>()

    private val newsPagingAdapter = NewsPagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewmodel.list.observe(viewLifecycleOwner) {
            newsPagingAdapter.submitData(lifecycle, it)
        }

        newsPagingAdapter.addLoadStateListener { state ->

            when (state.refresh) {
                is LoadState.Loading -> {
                    view.news_progress.visibility = View.VISIBLE
                }
                is LoadState.NotLoading -> {
                    view.news_progress.visibility = View.GONE
                }
                is LoadState.Error -> {
                    view.news_progress.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error Occured", Toast.LENGTH_SHORT).show()
                }

            }

        }


        view.news_recycler.adapter = newsPagingAdapter

    }
}
