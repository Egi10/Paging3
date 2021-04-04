package id.buaja.paging3.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import id.buaja.paging3.databinding.ItemLoadingBinding

/**
 * Created by Julsapargi Nursam on 4/4/21.
 */

class LoaderStateAdapter(private val retry: () -> Unit): LoadStateAdapter<LoadingViewHolder>() {
    override fun onBindViewHolder(holder: LoadingViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        LoadingViewHolder(ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false), retry)
}

class LoadingViewHolder(private val binding: ItemLoadingBinding, retry: () -> Unit): RecyclerView.ViewHolder(binding.root) {
    private val retry: MaterialButton = binding.btnRetry
        .also {
            it.setOnClickListener { retry() }
        }

    fun bind(loadState: LoadState){
        /**
         * Untuk menampilkan Error dan melakukan pengelolaan Loading, lebih jelas bisa di
         * https://developer.android.com/topic/libraries/architecture/paging/v3-paged-data#display-loading-state
         */
        with(binding) {
            if (loadState is LoadState.Error) {
                tvErrorMessage.text = loadState.error.localizedMessage
            }

            progressBar.isVisible = loadState is LoadState.Loading
            retry.isVisible = loadState is LoadState.Error
            tvErrorMessage.isVisible = loadState is LoadState.Error
        }
    }
}
