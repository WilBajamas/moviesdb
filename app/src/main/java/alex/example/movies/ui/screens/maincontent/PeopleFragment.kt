package alex.example.movies.ui.screens.maincontent

import alex.example.movies.ui.viewmodels.maincontent.PeopleFragmentViewModel
import alex.example.movies.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class PeopleFragment : Fragment() {

    companion object {
        fun newInstance() = PeopleFragment()
    }

    private lateinit var viewModel: PeopleFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_people, container, false)
    }

}