package com.example.filmapplication.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.filmapplication.R
import com.example.filmapplication.databinding.ActivityDetailFilmBinding
import com.example.filmapplication.core.domain.model.Movies
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFilmActivity : AppCompatActivity() {
     private lateinit var activityBinding: ActivityDetailFilmBinding
     private val detailViewModel: DetailFilmViewModel by viewModel()
     private var menu: Menu? = null
     companion object{
         const val EXTRA_DATA = "extra_data"
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityDetailFilmBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        setSupportActionBar(activityBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadingData(true)

        val extras = intent.getParcelableExtra<Movies>(EXTRA_DATA)
        if (extras != null) {
            detailViewModel.getDetailMovie(extras.id.toString()).observe(this, {
                    loadingData(false)
                    populateMovies(it)
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        val extras = intent.getParcelableExtra<Movies>(EXTRA_DATA)
        detailViewModel.getDetailMovie(extras?.id.toString()).observe(this, {
            loadingData(false)
            val state = it.favCheck
            setFavoriteState(state)
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            detailViewModel.setFavoriteMovie()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadingData(set: Boolean){
        activityBinding.pbDetail.isVisible = set
        activityBinding.toolbar.isInvisible = set
        activityBinding.layoutDetail.isInvisible = set
    }


    private fun populateMovies(movie: Movies){
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500"+movie.posterPath)
            .into(activityBinding.imgDetail)
        activityBinding.tvTitle.text = movie.title
        activityBinding.tvDate.text = movie.date
        activityBinding.tvRate.text = movie.vote.toString()
        activityBinding.tvOverview.text = movie.overview

        loadingData(false)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_yes)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_no)
        }
    }
}
