package alex.example.movies.ui.screens.maincontent

import alex.example.movies.R
import alex.example.movies.ui.viewmodels.maincontent.HomeFragmentViewModel
import alex.example.movies.databinding.FragmentHomeBinding
import alex.example.movies.ui.adapters.HomeAdapter
import alex.example.movies.utils.BaseFragment
import alex.example.movies.utils.Const
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Random


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>(
    FragmentHomeBinding::inflate, HomeFragmentViewModel::class.java
) {

    private lateinit var homeAdapter: HomeAdapter
    private lateinit var offsetChangedListener: OnOffsetChangedListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.init()
        val random = Random()

        with(binding) {

            homeAdapter = HomeAdapter(viewModel.sectionItemsData.value!!)
            homeRv.adapter = homeAdapter
            homeRv.layoutManager = LinearLayoutManager(requireContext())

            // Fixed bug on SwipeRefreshLayout overriding recyclerview's scroll
            offsetChangedListener = OnOffsetChangedListener { _, verticalOffset ->
                swipeRefreshLayout.isEnabled = verticalOffset == 0
            }.apply {
                appbar.addOnOffsetChangedListener(this)
            }

            swipeRefreshLayout.setOnRefreshListener {
                with(viewModel.backdropData.value) {
                    this?.let {
                        Glide.with(this@HomeFragment)
                            .load("${Const.POSTER_PATH_BASE_URL}${it[random.nextInt(it.size)].file_path}")
                            .placeholder(R.drawable.list_placeholder_img).into(collapsingIv)

                        swipeRefreshLayout.isRefreshing = false
                    }

                }
            }
            viewLifecycleOwner.lifecycleScope.launch {

                viewModel.backdropData.observe(viewLifecycleOwner) {
                    if (!it.isNullOrEmpty()) {
                        // TODO: Create glide module for reusing
                        Glide.with(this@HomeFragment)
                            .load("${Const.POSTER_PATH_BASE_URL}${it[random.nextInt(it.size)].file_path}")
                            .placeholder(R.drawable.list_placeholder_img).into(collapsingIv)
                    } else {
                        Glide.with(this@HomeFragment).load(R.drawable.sample_placeholder)
                            .into(collapsingIv)
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Log.i("HomeFragment", "onPause")
        binding.appbar.removeOnOffsetChangedListener(offsetChangedListener)
    }

}