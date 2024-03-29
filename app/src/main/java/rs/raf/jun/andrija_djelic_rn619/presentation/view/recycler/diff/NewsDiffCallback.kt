package rs.raf.jun.andrija_djelic_rn619.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.jun.andrija_djelic_rn619.data.models.news.News

class NewsDiffCallback: DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.content == newItem.content &&
                oldItem.link == newItem.link &&
                oldItem.date == newItem.date &&
                oldItem.image == newItem.image
    }
    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.content == newItem.content &&
                oldItem.link == newItem.link &&
                oldItem.date == newItem.date &&
                oldItem.image == newItem.image
    }
}