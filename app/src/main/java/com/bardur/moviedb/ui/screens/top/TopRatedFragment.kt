package com.bardur.moviedb.ui.screens.top

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bardur.moviedb.R
import com.bardur.moviedb.data.Movie
import com.bardur.moviedb.databinding.FragmentPopularBinding
import com.bardur.moviedb.databinding.FragmentTopRatedBinding
import com.bardur.moviedb.ui.adapters.MovieAdapter
import com.bardur.moviedb.ui.screens.popular.PopularViewModel
import com.bardur.moviedb.ui.screens.utills.showMovieDetails


class TopRatedFragment : Fragment(), MovieAdapter.MovieClickListener {

    private lateinit var topRatedViewModel: TopRatedViewModel
    private lateinit var binding: FragmentTopRatedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_rated, container, false)

        topRatedViewModel = ViewModelProvider(this)[TopRatedViewModel::class.java]

        binding.viewModel = topRatedViewModel

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieAdapter = MovieAdapter(this)
        binding.topRatedRecyclerView.adapter = movieAdapter

        topRatedViewModel.movies.observe(viewLifecycleOwner) {
            movieAdapter.updateMovies(it)
        }

        topRatedViewModel.error.observe(viewLifecycleOwner) { error ->
            run {
                if (error == 1) {
                    Toast.makeText(context, "Unable to load popular movies!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun handleClick(view: View, movie: Movie) {
        showMovieDetails(view, movie)
    }
}