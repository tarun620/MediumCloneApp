package com.example.conduit_2.ui.feed

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api.models.entities.Article
import com.example.conduit_2.R
import com.example.conduit_2.databinding.ListItemArticleBinding
import com.example.conduit_2.extensions.loadImage
import com.example.conduit_2.extensions.timeStamp

class ArticleFeedAdapter(val onArticleClicked:(slug:String)->Unit) : ListAdapter<Article,ArticleFeedAdapter.ArticleViewHolder>(
        object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.toString() == newItem.toString()
            }
        }
) {

//    interface OnArticleClickedListner{
//        fun onArticleClicked()
//    }

    inner class ArticleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
                parent.context.getSystemService(LayoutInflater::class.java).inflate(
                        R.layout.list_item_article,
                        parent,
                        false
                )
        )
    }


    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        var bind=ListItemArticleBinding.bind(holder.itemView).apply {
            val article = getItem(position)

            authorTextView.text = article.author.username
            titleTextView.text = article.title
            bodySnippetTextView.text = article.body
            dateTextView.timeStamp=article.createdAt
//            dateTextView.text="Deecember 15 2020" //
//            Glide.with(this)
//                    .load
//            avatarImageView.background=ColorDrawable(Color.GRAY) //
            avatarImageView.loadImage(article.author.image)

            root.setOnClickListener {onArticleClicked(article.slug)}
        }
    }
}
