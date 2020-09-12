package com.test.axiata.apps.movie.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.test.axiata.apps.BR
import com.test.axiata.apps.R
import com.test.axiata.apps.base.BaseFragment
import com.test.axiata.apps.databinding.FragmentListMovieBinding
import com.test.axiata.apps.movie.MovieContract
import com.test.axiata.apps.movie.MovieViewModel
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class ListMovieFragment : BaseFragment<FragmentListMovieBinding, MovieViewModel>(), MovieContract{

    @Inject
    internal lateinit var factoryModel: ViewModelProvider.Factory

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<DialogFragment>

    private lateinit var viewModel: MovieViewModel

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.fragment_list_movie

    override fun getViewModel(): MovieViewModel {
        viewModel = ViewModelProviders.of(this, factoryModel).get(MovieViewModel::class.java)
        return viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setNavigator(this)

        initViews()
    }

    private fun initViews() {

    }

}