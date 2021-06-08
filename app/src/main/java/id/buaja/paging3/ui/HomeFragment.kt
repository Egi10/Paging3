package id.buaja.paging3.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.buaja.paging3.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    private var homeAdapter: HomeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeAdapter = HomeAdapter()
        /**
         * Kalau menginginkan saat error terjadi menampilkan sesuatu di luar list
         * Anda bisa menggunakan ini untuk melakukannya
         * https://developer.android.com/topic/libraries/architecture/paging/v3-paged-data#load-state-listener
         */
        homeAdapter?.addLoadStateListener {
            binding.progressBar.isVisible = it.refresh is LoadState.Loading
        }

        initBinding()

        /**
         * Untuk Referensi tentang lifecycleScope
         * https://developer.android.com/topic/libraries/architecture/coroutines?hl=id
         */
        lifecycleScope.launch {
            viewModel.getCharacter().collectLatest {
                homeAdapter?.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        homeAdapter = null
    }

    private fun initBinding() = with(binding) {
        /**
         * :: creates a member reference or a class reference
         * https://kotlinlang.org/docs/reflection.html#function-references
         * https://kotlinlang.org/docs/reflection.html#class-references
         */
        recyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeAdapter?.withLoadStateFooter(
                footer = LoaderStateAdapter(homeAdapter!!::retry)
            )
        }
    }
}