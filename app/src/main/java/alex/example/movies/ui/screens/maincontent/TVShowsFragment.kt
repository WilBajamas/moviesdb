package alex.example.movies.ui.screens.maincontent

import alex.example.movies.R
import alex.example.movies.ui.viewmodels.maincontent.TVShowsFragmentViewModel
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class TVShowsFragment : Fragment() {

    companion object {
        fun newInstance() = TVShowsFragment()
    }

    private lateinit var viewModel: TVShowsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_t_v_shows, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TVShowsFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}