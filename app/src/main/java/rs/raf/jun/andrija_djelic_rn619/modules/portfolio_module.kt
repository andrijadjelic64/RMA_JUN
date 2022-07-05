package rs.raf.jun.andrija_djelic_rn619.modules

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.jun.andrija_djelic_rn619.data.datasources.local.LocalDataBase
import rs.raf.jun.andrija_djelic_rn619.data.datasources.remote.popularstocks.PopularStocksServiceImpl
import rs.raf.jun.andrija_djelic_rn619.data.datasources.remote.quote.QuoteService
import rs.raf.jun.andrija_djelic_rn619.data.datasources.remote.quote.QuoteServiceImpl
import rs.raf.jun.andrija_djelic_rn619.data.repositories.PortfolioRepository
import rs.raf.jun.andrija_djelic_rn619.data.repositories.PortfolioRepositoryImpl
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.PortfolioViewModel

val portfolioModule = module {

    viewModel { PortfolioViewModel(portfolioRepository = get()) }

    single<PortfolioRepository> {
        PortfolioRepositoryImpl(
            localDataSource = get()
        )
    }

    single { get<LocalDataBase>().getPortfolioDao() }
//    single<QuoteService> { QuoteServiceImpl(context = androidApplication(), moshi = get()) }
}