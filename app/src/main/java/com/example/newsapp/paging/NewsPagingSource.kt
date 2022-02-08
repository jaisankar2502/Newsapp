package com.example.newsapp.paging

import androidx.paging.PagingSource
import com.example.newsapp.Contants
import com.example.newsapp.retrofit.NewsInterface
import com.example.newsapp.retrofit.responce.Article
import retrofit2.HttpException
import java.io.IOException

const val STARTING_INDEX=1
class NewsPagingSource (val newsInterface: NewsInterface):PagingSource<Int, Article>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val positions=params.key?: STARTING_INDEX
        return try {
            val data= newsInterface.getAllnews("in","business", Contants.API_KEY,positions,params.loadSize)
            LoadResult.Page(
                data = data.articles,
                prevKey = if(params.key== STARTING_INDEX) null else positions-1,
                nextKey = if(data.articles.isEmpty()) null else positions+1
            )
        }catch (e: IOException){
            LoadResult.Error(e)
        }
        catch (e: HttpException){
            LoadResult.Error(e)
        }

    }

}