package dev.lucasdabs.carselection.ui.selection.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.lucasdabs.carselection.R
import dev.lucasdabs.carselection.api.data.Manufacturer
import kotlinx.android.synthetic.main.item_adapter.view.*

class PickerDialogAdapter(val clickItem: (item: Any) -> Unit = {}
) : PagedListAdapter<Manufacturer, PickerDialogAdapter.ViewHolder>(ManufacturerDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_adapter, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let{
            holder.render(it)
        }
    }

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        fun render(item: Any) {
            view.textView.text = item.toString()
            view.setOnClickListener { clickItem.invoke(item) }
        }
    }

    companion object {
        val ManufacturerDiffCallback = object : DiffUtil.ItemCallback<Manufacturer>() {
            override fun areItemsTheSame(oldItem: Manufacturer, newItem: Manufacturer): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Manufacturer, newItem: Manufacturer): Boolean =
                oldItem == newItem

        }
    }

}