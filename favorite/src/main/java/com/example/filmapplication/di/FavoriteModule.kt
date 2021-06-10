package com.example.filmapplication.di

import com.example.filmapplication.favorite.MoviesFavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favModule = module {
    viewModel { MoviesFavoriteViewModel(get()) }
}