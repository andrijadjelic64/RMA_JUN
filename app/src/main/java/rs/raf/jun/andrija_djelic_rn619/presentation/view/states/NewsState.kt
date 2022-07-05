package rs.raf.jun.andrija_djelic_rn619.presentation.view.states

import rs.raf.jun.andrija_djelic_rn619.data.models.news.News

sealed class NewsState {
    object Loading : NewsState()
    object DataFetched : NewsState()
    data class Success(val news: List<News>) : NewsState()
    data class Error(val message: String) : NewsState()
}
