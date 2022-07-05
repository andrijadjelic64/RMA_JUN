package rs.raf.jun.andrija_djelic_rn619.modules

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.jun.andrija_djelic_rn619.data.datasources.remote.popularstocks.PopularStocksService
import rs.raf.jun.andrija_djelic_rn619.data.datasources.remote.popularstocks.PopularStocksServiceImpl
import rs.raf.jun.andrija_djelic_rn619.data.repositories.PopularStocksRepository
import rs.raf.jun.andrija_djelic_rn619.data.repositories.PopularStocksRepositoryImpl
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.PopularStocksViewModel

val popularStocksModule = module {

    viewModel { PopularStocksViewModel(popularStocksRepository = get()) }

    single<PopularStocksRepository> { PopularStocksRepositoryImpl(remoteDataSource = get()) }

    single<PopularStocksService> { PopularStocksServiceImpl(context = androidApplication(), moshi = get()) }
}