package rs.raf.jun.andrija_djelic_rn619.modules

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.jun.andrija_djelic_rn619.data.datasources.remote.news.NewsService
import rs.raf.jun.andrija_djelic_rn619.data.datasources.remote.news.NewsServiceImpl
import rs.raf.jun.andrija_djelic_rn619.data.repositories.NewsRepository
import rs.raf.jun.andrija_djelic_rn619.data.repositories.NewsRepositoryImpl
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.NewsViewModel

val newsModule = module {

    viewModel { NewsViewModel(newsRepository = get()) }

    single<NewsRepository> { NewsRepositoryImpl(remoteDataSource = get()) }

    single<NewsService> { NewsServiceImpl(context = androidApplication(), moshi = get()) }
}