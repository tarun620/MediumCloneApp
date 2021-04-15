package com.example.conduit_2.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.conduit_2.R
import com.example.conduit_2.databinding.FragmentFeedBinding

class GloabalFeedFragment : Fragment() {

    lateinit var _binding: FragmentFeedBinding
    private lateinit var viewModel:FeedViewModel
    private lateinit var feedAdapter: ArticleFeedAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel=ViewModelProvider(this).get(FeedViewModel::class.java)
//        feedAdapter= ArticleFeedAdapter(object: ArticleFeedAdapter.OnArticleClickedListner{
//            override fun onArticleClicked()=openArticle()
//        })
        feedAdapter= ArticleFeedAdapter{openArticle(it)}
        _binding= FragmentFeedBinding.inflate(inflater,container,false)
        _binding.feedRecyclerView.layoutManager=LinearLayoutManager(context)
        _binding.feedRecyclerView.adapter=feedAdapter

//        val root=inflater.inflate(R.layout.fragment_feed,container,false)
//        val fetchFeedButton:Button=root.findViewById(R.id.fetchFeedButton)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchGlobalFeed()
        viewModel.feed.observe({ lifecycle }){
            feedAdapter.submitList(it)
        }
    }
    fun openArticle(articleId: String) {
        findNavController().navigate(
                R.id.action_globalFeed_openArticle,
                bundleOf(
                        resources.getString(R.string.arg_article_id) to articleId
                )
        )
    }
}