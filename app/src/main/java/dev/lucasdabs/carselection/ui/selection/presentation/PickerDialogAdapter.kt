package dev.lucasdabs.carselection.ui.selection.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.lucasdabs.carselection.R
import dev.lucasdabs.carselection.api.data.BaseData
import dev.lucasdabs.carselection.extension.colorRes
import kotlinx.android.synthetic.main.item_adapter.view.*

class PickerDialogAdapter(val clickItem: (item: Any) -> Unit = {}
) : PagedListAdapter<BaseData, PickerDialogAdapter.ViewHolder>(BaseDataDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_adapter, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let{
            holder.render(it, position)
        }
    }

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        fun render(item: Any, position: Int) {
            view.setBackgroundColor(view.context.colorRes(
                if (position % 2 == 0)
                    R.color.gray
                else
                    android.R.color.white)
            )

            view.textView.text = item.toString()
            view.setOnClickListener { clickItem.invoke(item) }
        }
    }

    companion object {
        val BaseDataDiffCallback = object : DiffUtil.ItemCallback<BaseData>() {
            override fun areItemsTheSame(oldItem: BaseData, newItem: BaseData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: BaseData, newItem: BaseData): Boolean =
                oldItem == newItem

        }
    }
}