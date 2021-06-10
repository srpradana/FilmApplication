package com.example.filmapplication.ui.list

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmapplication.R
import com.example.filmapplication.databinding.ActivityHomeBinding
import com.example.filmapplication.core.ui.MoviesListAdapter
import com.example.filmapplication.core.data.source.Resource
import com.example.filmapplication.ui.detail.DetailFilmActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var moviesAdapter : MoviesListAdapter
    private val moviesListViewModel: MoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moviesAdapter = MoviesListAdapter()
        moviesAdapter.onItemClick = {
            val intent = Intent(this, DetailFilmActivity::class.java)
            intent.putExtra(DetailFilmActivity.EXTRA_DATA, it)
            startActivity(intent)
        }

        moviesListViewModel.movies.observe(this, {
            if(it != null){
                when(it){
                    is Resource.Loading -> binding.pbMoviesList.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.pbMoviesList.visibility = View.GONE
                        moviesAdapter.setFilm(it.data)
                        moviesAdapter.notifyDataSetChanged()
                    }
                    is Resource.Error -> {
                        binding.pbMoviesList.visibility = View.GONE
                        Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        with(binding.rvMovies) {
            layoutManager = LinearLayoutManager(context)
            adapter = moviesAdapter
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_favorite_list){
            val uri = Uri.parse("filmapplication://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
        return super.onOptionsItemSelected(item)
    }
}