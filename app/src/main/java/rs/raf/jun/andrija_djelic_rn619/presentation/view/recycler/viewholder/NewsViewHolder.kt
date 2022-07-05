package rs.raf.jun.andrija_djelic_rn619.presentation.view.recycler.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import rs.raf.jun.andrija_djelic_rn619.data.models.news.News
import rs.raf.jun.andrija_djelic_rn619.databinding.LayoutItemNewsBinding


class NewsViewHolder (
    private val itembinding: LayoutItemNewsBinding,
    private val context: Context,
    val goToWebPage: (position: Int)-> Unit
) : RecyclerView.ViewHolder(itembinding.root){

    init {
        itembinding.newsTicket.setOnClickListener {
            goToWebPage(layoutPosition)
        }
    }

    fun bind(news: News){

        val imageUri = news.image
        Picasso.with(context).load(imageUri).into(itembinding.picassoImage)

        itembinding.titleLabel.text = news.title
        itembinding.dateLabel.text = news.date
    }
}