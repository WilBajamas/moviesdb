package alex.example.movies.ui.screens.onboarding

import alex.example.movies.databinding.OnboardingViewBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class OnboardingViewFragment : Fragment() {

    private lateinit var binding: OnboardingViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = OnboardingViewBinding.inflate(inflater, container, false)
        return binding.root
    }
}