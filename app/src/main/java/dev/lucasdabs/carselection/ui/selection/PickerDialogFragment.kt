package dev.lucasdabs.carselection.ui.selection

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import dev.lucasdabs.carselection.R
import dev.lucasdabs.carselection.api.data.Manufacturer
import dev.lucasdabs.carselection.ui.selection.presentation.PickerDialogAdapter
import kotlinx.android.synthetic.main.dialog_fragment.view.*

class PickerDialogFragment: DialogFragment(), PickerDialogContract.View {

    override val viewContext: Context by lazy { context!! }
    override val presenter by lazy { PickerDialogPresenter(this) }

    private val listManufacturer = mutableListOf<Manufacturer>()
    private val adapter = PickerDialogAdapter(listManufacturer)

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


    override fun updateAdapter(list: List<Manufacturer>) {
//        listManufacturer.clear()
//        listManufacturer.addAll(list)
//        adapter.notifyDataSetChanged()
    }

    private fun bindView() {
        initProgress()

        dialogView.recyclerView.setHasFixedSize(true)
//        recyclerView.addItemDecoration()

        val layoutManager = LinearLayoutManager(activity)
        dialogView.recyclerView.layoutManager = layoutManager

        dialogView.recyclerView.adapter = adapter
        presenter.list.observe(this, Observer<PagedList<Manufacturer>> { adapter.list = it })

//        presenter.loadData()
    }

    private fun onScrollListener(manager: LinearLayoutManager, currentPage: Int) {

    }

    override fun initProgress() {
        dialogView.progressBar.visibility = View.VISIBLE
    }

    override fun stopProgress() {
        dialogView.progressBar.visibility = View.GONE
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(viewContext, errorMessage, Toast.LENGTH_LONG).show()
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