package rs.raf.jun.andrija_djelic_rn619.modules

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.jun.andrija_djelic_rn619.data.datasources.remote.popularstocks.PopularStocksServiceImpl
import rs.raf.jun.andrija_djelic_rn619.data.datasources.remote.quote.QuoteService
import rs.raf.jun.andrija_djelic_rn619.data.datasources.remote.quote.QuoteServiceImpl
import rs.raf.jun.andrija_djelic_rn619.data.repositories.PopularStocksRepositoryImpl
import rs.raf.jun.andrija_djelic_rn619.data.repositories.QuoteRepository
import rs.raf.jun.andrija_djelic_rn619.data.repositories.QuoteRepositoryImpl
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.PopularStocksViewModel
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.QuoteViewModel

val quoteModule = module {

    viewModel { QuoteViewModel(quoteRepository = get()) }
    single<QuoteRepository> { QuoteRepositoryImpl(remoteDataSource = get()) }

    single<QuoteService> { QuoteServiceImpl(context = androidApplication(), moshi = get()) }
}

