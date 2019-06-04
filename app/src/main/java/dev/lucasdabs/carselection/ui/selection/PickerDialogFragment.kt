package dev.lucasdabs.carselection.ui.selection

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import dev.lucasdabs.carselection.R
import dev.lucasdabs.carselection.api.data.Manufacturer
import dev.lucasdabs.carselection.ui.selection.paging.PickerDialogDataSource
import dev.lucasdabs.carselection.ui.selection.presentation.PickerDialogAdapter
import kotlinx.android.synthetic.main.dialog_fragment.view.*

class PickerDialogFragment: DialogFragment(), PickerDialogContract.View {

    override val viewContext: Context by lazy { requireContext() }
    override val presenter by lazy { PickerDialogPresenter(this) }

    private lateinit var adapter: PickerDialogAdapter
    private lateinit var dialogView: View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialogView = LayoutInflater.from(context)
            .inflate(R.layout.dialog_fragment, null)

        val builder = AlertDialog.Builder(context)
            .setPositiveButton(R.string.ok_button, {_, _ -> })
            .setNegativeButton(R.string.cancel_button, null)

        bindView()
        builder.setView(dialogView)
        return builder.create()
    }

    private fun bindView() {
        adapter = PickerDialogAdapter()

        dialogView.recyclerView.layoutManager = LinearLayoutManager(activity)
        dialogView.recyclerView.setHasFixedSize(true)

        dialogView.recyclerView.adapter = adapter
        presenter.list.observe(this, Observer<PagedList<Manufacturer>> {
            adapter.submitList(it)
        })

        presenter.state.observe(this, Observer<PickerDialogDataSource.State> {
            when (it) {
                PickerDialogDataSource.State.LOADING -> loading()
                PickerDialogDataSource.State.DONE -> done()
                PickerDialogDataSource.State.ERROR -> error()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onCleared()
    }

    private fun loading() {
        dialogView.progressBar.visibility = View.VISIBLE
    }

    private fun done() {
        dialogView.progressBar.visibility = View.GONE
    }

    private fun error() {
        Toast.makeText(activity, R.string.error_fetch_data, Toast.LENGTH_SHORT).show()
    }

    companion object {

        const val PARAMETER = "PARAMETER"

        fun newInstance(title: String): PickerDialogFragment {
            val dialog = PickerDialogFragment()
            val bundle = Bundle()
            bundle.putString("title", title)
            dialog.arguments = bundle
            return dialog
        }
    }

}