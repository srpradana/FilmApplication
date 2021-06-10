package com.example.filmapplication.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmapplication.core.ui.MoviesFavoriteAdapter
import com.example.filmapplication.di.favModule
import com.example.filmapplication.favorite.databinding.ActivityFavoriteBinding
import com.example.filmapplication.ui.detail.DetailFilmActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favMoviesAdapter: MoviesFavoriteAdapter
    private val favMoviesListViewModel: MoviesFavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favMoviesAdapter = MoviesFavoriteAdapter()
        favMoviesAdapter.onItemClick = {
            val intent = Intent(this, DetailFilmActivity::class.java)
            intent.putExtra(DetailFilmActivity.EXTRA_DATA, it)
            startActivity(intent)
        }

        loadKoinModules(favModule)

        favMoviesListViewModel.movies.observe(this, {
            if(it != null){
                binding.pbFavoriteMovieList.visibility = View.GONE
                favMoviesAdapter.setFilm(it)
                favMoviesAdapter.notifyDataSetChanged()
            }
        })
        binding.rvFavoritList.layoutManager = LinearLayoutManager(this)
        binding.rvFavoritList.adapter = favMoviesAdapter
    }
}