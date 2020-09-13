package com.test.axiata.apps.movie.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.axiata.apps.BR
import com.test.axiata.apps.R
import com.test.axiata.apps.base.BaseFragment
import com.test.axiata.apps.common.PaginationScrollListener
import com.test.axiata.apps.custom.ListPickerDialog
import com.test.axiata.apps.databinding.FragmentListMovieBinding
import com.test.axiata.apps.extension.navigateSafe
import com.test.axiata.apps.movie.MovieActivity
import com.test.axiata.apps.movie.MovieContract
import com.test.axiata.apps.movie.MovieViewModel
import com.test.axiata.apps.movie.list.adapter.MovieListAdapter
import com.test.axiata.apps.network.response.GenreResponse
import dagger.android.DispatchingAndroidInjector
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ListMovieFragment : BaseFragment<FragmentListMovieBinding, MovieViewModel>(), MovieContract, MovieListAdapter.onItemClickListener{

    @Inject
    internal lateinit var factoryModel: ViewModelProvider.Factory

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<DialogFragment>

    private lateinit var viewModel: MovieViewModel
    private lateinit var binding : FragmentListMovieBinding
    private lateinit var layoutManager  : GridLayoutManager
    private lateinit var adapter : MovieListAdapter
    private var genreList : GenreResponse?= null
    private var defPage = 1
    private var genreId = 28
    private lateinit var navController: NavController

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

        (activity as MovieActivity).visibilityToolbar(true)

        initViews()
    }

    private fun initViews() {
        viewModel.fetchMovieList(defPage, 28)
        viewModel.fetchMovieGenre()

        layoutManager = GridLayoutManager(requireContext(), 2)
        binding.movieRv.layoutManager = layoutManager
        adapter = MovieListAdapter(requireContext(), this)
        binding.movieRv.adapter = adapter
        binding.movieRv.addOnScrollListener(scrollData())

        viewModel.movieListResponse.observe(
            viewLifecycleOwner,
            Observer { response ->
                adapter.setDataList(response)
                binding.progressBar.visibility = View.GONE
                binding.buttonFilter.visibility = View.VISIBLE
            })

        viewModel.movieGenreResponse.observe(
            viewLifecycleOwner,
            Observer { response ->
                genreList = response
            })


        binding.buttonFilter.setOnClickListener {
            val pickerDialog = ListPickerDialog.
            newInstance("Select Genre", genreList, 0)
                pickerDialog.show(this.childFragmentManager) { dialog, itemText, _, position ->
                    dialog.dismiss()
                    genreId = itemText.id?:0
                    defPage = 1
                    adapter.clearListMovie()
                    viewModel.fetchMovieList(defPage, genreId)
                }
        }

        viewModel.shimmerVisibility.observe(viewLifecycleOwner, Observer {
            if(it){
                startLoading()
            }else{
                stopLoading()
            }
        })

        viewModel.errorMessages.observe(viewLifecycleOwner, Observer {
            onError(it)
        })
    }

    private fun scrollData(): PaginationScrollListener {
        return object : PaginationScrollListener() {
            @SuppressLint("CheckResult")
            override fun onLoadMore() {
                binding.progressBar.visibility = View.VISIBLE
                binding.buttonFilter.visibility = View.GONE
                Completable
                    .timer(3, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        defPage += 1
                        viewModel.fetchMovieList(defPage, genreId)
                    }, {

                    })
            }
        }
    }

    override fun startLoading() {
        binding.shimmerViewContainer.visibility = View.VISIBLE
        binding.shimmerViewContainer.startShimmerAnimation()
    }

    override fun stopLoading() {
        binding.shimmerViewContainer.stopShimmerAnimation()
        binding.shimmerViewContainer.visibility = View.GONE
    }

    override fun onError(message: String) {
        showSimpleAlertDialog("", message)
    }

    override fun itemClicked(movieId: Int) {
        val bundle = bundleOf("movieId" to movieId.toString())
        view?.findNavController()?.navigate(R.id.view_movie_details, bundle)
    }

}