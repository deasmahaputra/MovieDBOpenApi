package com.test.axiata.apps.movie.details

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.test.axiata.apps.BR
import com.test.axiata.apps.R
import com.test.axiata.apps.base.BaseFragment
import com.test.axiata.apps.databinding.FragmentDetailsMovieBinding
import com.test.axiata.apps.movie.MovieActivity
import com.test.axiata.apps.movie.youtube.YoutubeVideoPlayerActivity
import com.test.axiata.apps.network.response.DetailsMovieResponse
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class DetailsMovieFragment : BaseFragment<FragmentDetailsMovieBinding, DetailsMovieViewModel>(), DetailsMovieContract{

    @Inject
    internal lateinit var factoryModel: ViewModelProvider.Factory

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<DialogFragment>

    private lateinit var viewModel: DetailsMovieViewModel
    private lateinit var binding : FragmentDetailsMovieBinding
    private val url = "https://image.tmdb.org/t/p/w500"
    private var titleMovie = ""

    override fun getBindingVariable(): Int = BR.viewModel


    override fun getLayoutId(): Int = R.layout.fragment_details_movie

    override fun getViewModel(): DetailsMovieViewModel {
        viewModel = ViewModelProviders.of(this, factoryModel).get(DetailsMovieViewModel::class.java)
        return viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
        viewModel.setNavigator(this)

        initViews()

        viewModel.errorMessages.observe(viewLifecycleOwner, Observer {
            onError(it)
        })

        viewModel.detailsMovieData.observe(viewLifecycleOwner, Observer{
            response -> loadDataMovie(response)
        })

        viewModel.trailerMovieData.observe(viewLifecycleOwner, Observer{
            if(!it.results?.get(0)?.key.isNullOrEmpty()) {
                val intent = Intent(requireContext(), YoutubeVideoPlayerActivity::class.java)
                intent.putExtra("url", it?.results?.get(0)?.key)
                startActivity(intent)
            }
        })
    }

    private fun initViews() {
        (activity as MovieActivity).visibilityToolbar(false)
        val movieId = arguments?.getString("movieId")
        movieId?.toInt()?.let { viewModel.fetchMovieDetails(it) }

        binding.trailerBtn.setOnClickListener {
            movieId?.toInt()?.let { it1 -> viewModel.fetchTrailerVideo(it1) }
        }
    }

    override fun onError(message: String) {
        showSimpleAlertDialog("", message)
    }

    private fun loadDataMovie(data : DetailsMovieResponse){
        Glide.with(requireContext()).load(url + data.poster_path).placeholder(R.drawable.ic_empty_image).into(binding.moviesIv)
        binding.titleTv.text = data.title
        binding.overviewTv.text = data.overview
        binding.voteTv.text = data.vote_average.toString()
        titleMovie = data.title.toString()
    }
}