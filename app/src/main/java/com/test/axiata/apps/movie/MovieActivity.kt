package com.test.axiata.apps.movie

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.test.axiata.apps.R
import com.test.axiata.apps.base.BaseActivity
import com.test.axiata.apps.databinding.ActivityMovieBinding
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_movie.*
import javax.inject.Inject

class MovieActivity : BaseActivity<ActivityMovieBinding, MovieViewModel>(), MovieContract, HasSupportFragmentInjector{

    @Inject
    internal lateinit var factoryModel: ViewModelProvider.Factory

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var viewModel: MovieViewModel

    private lateinit var navController: NavController

    private lateinit var binding: ActivityMovieBinding

    private lateinit var appBarConfig: AppBarConfiguration

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_movie

    override fun getViewModel(): MovieViewModel {
        viewModel = ViewModelProviders.of(this, factoryModel).get(MovieViewModel::class.java)
        return viewModel
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        initViews()
    }

    private fun initViews() {
        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun startLoading() {}

    override fun stopLoading() {}
    override fun onError(message: String) {}
    override fun showServiceUnavailablePage() {}

    fun visibilityToolbar(visible : Boolean){
        binding.toolbar.isVisible = visible
        if (visible) binding.toolbar.invalidate()
    }
}