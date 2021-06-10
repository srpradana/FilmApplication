package com.example.filmapplication.di

import com.example.filmapplication.core.domain.usecase.MovieInteractor
import com.example.filmapplication.core.domain.usecase.MoviesUseCase
import com.example.filmapplication.ui.detail.DetailFilmViewModel
import com.example.filmapplication.ui.list.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MoviesUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { DetailFilmViewModel(get()) }
}