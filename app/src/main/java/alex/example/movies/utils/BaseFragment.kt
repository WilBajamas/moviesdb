package alex.example.movies.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding, VM: ViewModel>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T,
    private val viewModelClass: Class<out VM>
) : Fragment() {

    private var _binding: T? = null
    protected val binding: T
        get() = _binding!!

    protected lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater(inflater, container, false)
        viewModel = ViewModelProvider(this)[viewModelClass]
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}