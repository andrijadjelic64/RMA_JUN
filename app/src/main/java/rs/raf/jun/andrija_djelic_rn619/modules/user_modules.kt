package rs.raf.jun.andrija_djelic_rn619.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.jun.andrija_djelic_rn619.data.datasources.local.LocalDataBase
import rs.raf.jun.andrija_djelic_rn619.data.repositories.UserRepository
import rs.raf.jun.andrija_djelic_rn619.data.repositories.UserRepositoryImpl
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.UserViewModel

val userModule = module {

    viewModel { UserViewModel(userRepository = get()) }

    single<UserRepository> { UserRepositoryImpl(localDataSource = get()) }

    single { get<LocalDataBase>().getUserDao() }

}