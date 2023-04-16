package alex.example.movies

import alex.example.movies.databinding.FragmentMainContentBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController

class MainContentFragment : Fragment() {

    private lateinit var binding: FragmentMainContentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment = binding.mainContentNavHostFragment.getFragment<NavHostFragment>()
        val navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController)
    }

}