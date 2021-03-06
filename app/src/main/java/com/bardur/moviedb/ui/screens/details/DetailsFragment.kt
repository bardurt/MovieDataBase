package com.bardur.moviedb.ui.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bardur.moviedb.MainActivity
import com.bardur.moviedb.R
import com.bardur.moviedb.databinding.FragmentDetailsBinding
import com.bardur.moviedb.storage.MovieStorageRepo

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var viewModelFactory: DetailsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val repo: MovieStorageRepo = (activity as MainActivity).movieStorageRepo

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        viewModelFactory =
            DetailsViewModelFactory(DetailsFragmentArgs.fromBundle(requireArguments()).movie, repo)

        detailsViewModel =
            ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)

        binding.detailsViewModel = detailsViewModel

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}