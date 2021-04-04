package id.buaja.paging3.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.buaja.paging3.databinding.ItemListHomeBinding
import id.buaja.paging3.domain.model.ResultsItem

/**
 * Created by Julsapargi Nursam on 4/4/21.
 */

class HomeAdapter : PagingDataAdapter<ResultsItem, ViewHolder>(Comparator) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemListHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    object Comparator : DiffUtil.ItemCallback<ResultsItem>() {
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem == newItem
        }
    }

}

class ViewHolder(private val binding: ItemListHomeBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ResultsItem) {
        with(binding) {
            ivImage.load(item.image)
            tvName.text = item.name
            tvStatus.text = item.status
        }
    }
}
