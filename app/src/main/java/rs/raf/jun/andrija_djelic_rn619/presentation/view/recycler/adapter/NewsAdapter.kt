package rs.raf.jun.andrija_djelic_rn619.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.jun.andrija_djelic_rn619.data.models.news.News
import rs.raf.jun.andrija_djelic_rn619.databinding.LayoutItemNewsBinding
import rs.raf.jun.andrija_djelic_rn619.presentation.view.recycler.diff.NewsDiffCallback
import rs.raf.jun.andrija_djelic_rn619.presentation.view.recycler.viewholder.NewsViewHolder

class NewsAdapter(
    val goToWebPage: (news: News) -> Unit
): ListAdapter<News, NewsViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemBinding = LayoutItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(itemBinding,parent.context, { goToWebPage(getItem(it))})
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}