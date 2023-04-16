package alex.example.movies.ui.screens.onboarding

import alex.example.movies.R
import alex.example.movies.databinding.FragmentNewOnboardingBinding
import alex.example.movies.databinding.OnboardingViewBinding
import alex.example.movies.domain.PagerItem
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide

class NewOnboardingFragment : Fragment() {

    private lateinit var binding: FragmentNewOnboardingBinding
    private lateinit var onPageChangeCallback: ViewPager2.OnPageChangeCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerItems = listOf(
            PagerItem(
                getString(R.string.pager_title_1),
                getString(R.string.pager_desc_1),
                R.drawable.sample_placeholder
            ),
            PagerItem(
                getString(R.string.pager_title_1),
                getString(R.string.pager_desc_1),
                R.drawable.sample_placeholder
            ),
            PagerItem(
                getString(R.string.pager_title_1),
                getString(R.string.pager_desc_1),
                R.drawable.sample_placeholder
            )
        )

        val pagerAdapter = NewOnboardingPagerAdapter(pagerItems)
        binding.viewPager.adapter = pagerAdapter

        binding.nextBtn.setOnClickListener {
            if (binding.viewPager.currentItem < pagerItems.size - 1) binding.viewPager.currentItem += 1 else Log.i(
                "onboarding current item:",
                binding.viewPager.currentItem.toString()
            )
        }

        onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == pagerItems.size - 1) binding.nextBtn.text =
                    getString(R.string.finish) else binding.nextBtn.text =
                    getString(R.string.next)
            }
        }
        binding.viewPager.registerOnPageChangeCallback(onPageChangeCallback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.viewPager.unregisterOnPageChangeCallback(onPageChangeCallback)
    }

    private inner class NewOnboardingPagerAdapter(val pagerItems: List<PagerItem>) :
        RecyclerView.Adapter<NewOnboardingPagerAdapter.PagerViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
            val binding =
                OnboardingViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PagerViewHolder(binding)
        }

        override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
            val item = pagerItems[position]
            with(holder) {
                binding.titleTv.text = item.title
                binding.descriptionTv.text = item.description

                Glide.with(binding.root).load(item.imgResource).into(binding.img)
            }
        }

        override fun getItemCount(): Int {
            return pagerItems.size
        }

        private inner class PagerViewHolder(val binding: OnboardingViewBinding) :
            RecyclerView.ViewHolder(binding.root)
    }

}