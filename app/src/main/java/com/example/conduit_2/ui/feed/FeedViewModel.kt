package com.example.conduit_2.ui.feed

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.models.entities.Article
import com.example.conduit_2.data.ArticlesRepo
import kotlinx.coroutines.launch

class FeedViewModel: ViewModel() {
    private val _feed= MutableLiveData<List<Article>>()
    val feed:LiveData<List<Article>> = _feed

    fun fetchGlobalFeed()=viewModelScope.launch {
        ArticlesRepo.getGlobalFeed().let {
            _feed.postValue(it)
//            Log.d("FEED","feed fetched${it.articlesCount}")
        }

    }

    fun fetchMyFeed()=viewModelScope.launch {
        ArticlesRepo.getMyfeed().let {
            _feed.postValue(it)
//            Log.d("My_FEED","my geed fetched${it.articlesCount}")
        }

    }
}