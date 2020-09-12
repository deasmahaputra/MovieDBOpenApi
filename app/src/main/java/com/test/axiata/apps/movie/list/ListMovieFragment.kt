package com.test.axiata.apps.movie.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.axiata.apps.BR
import com.test.axiata.apps.R
import com.test.axiata.apps.base.BaseFragment
import com.test.axiata.apps.databinding.FragmentListMovieBinding
import com.test.axiata.apps.movie.MovieContract
import com.test.axiata.apps.movie.MovieViewModel
import com.test.axiata.apps.movie.list.adapter.MovieListAdapter
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class ListMovieFragment : BaseFragment<FragmentListMovieBinding, MovieViewModel>(), MovieContract{

    @Inject
    internal lateinit var factoryModel: ViewModelProvider.Factory

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<DialogFragment>

    private lateinit var viewModel: MovieViewModel
    private lateinit var binding : FragmentListMovieBinding
    private lateinit var layoutManager  : GridLayoutManager
    private lateinit var adapter : MovieListAdapter

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.fragment_list_movie

    override fun getViewModel(): MovieViewModel {
        viewModel = ViewModelProviders.of(this, factoryModel).get(MovieViewModel::class.java)
        return viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
        viewModel.setNavigator(this)

        initViews()
    }

    private fun initViews() {
        viewModel.fetchMovieList(1)

        layoutManager = GridLayoutManager(requireContext(), 2)
        binding.movieRv.layoutManager = layoutManager
        adapter = MovieListAdapter(requireContext())
        binding.movieRv.adapter = adapter

        viewModel.movieListResponse.observe(
            viewLifecycleOwner,
            Observer { response ->
                adapter.setDataList(response)
            })

    }

}