package dev.lucasdabs.carselection.ui.selection

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import dev.lucasdabs.carselection.R
import dev.lucasdabs.carselection.api.data.BaseData
import dev.lucasdabs.carselection.api.data.RequestParameter
import dev.lucasdabs.carselection.api.repository.BaseRepository
import dev.lucasdabs.carselection.api.repository.BuiltDateRepository
import dev.lucasdabs.carselection.api.repository.ManufacturerRepository
import dev.lucasdabs.carselection.api.repository.ModelRepository
import dev.lucasdabs.carselection.ui.selection.paging.PickerDialogDataSource
import dev.lucasdabs.carselection.ui.selection.presentation.PickerDialogAdapter
import dev.lucasdabs.carselection.util.RequestType
import kotlinx.android.synthetic.main.dialog_fragment.*
import kotlinx.android.synthetic.main.dialog_fragment.view.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.support.kodein
import org.kodein.di.generic.instance

class PickerDialogFragment: DialogFragment(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val manufacturerRepository by instance<ManufacturerRepository>()
    private val modelRepository by instance<ModelRepository>()
    private val builtDatesRepository by instance<BuiltDateRepository>()
    private lateinit var currentRepository: BaseRepository

    private lateinit var adapter: PickerDialogAdapter
    private lateinit var dialogView: View

    var manufacturerLiveData = MutableLiveData<BaseData>()
    lateinit var viewModel: PickerDialogViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val parameters = arguments?.get(PARAMETER) as? RequestParameter
        currentRepository =
            when (arguments?.get(SERVICE)) {
                RequestType.MANUFACTURER -> manufacturerRepository
                RequestType.MODEL -> modelRepository
                else -> builtDatesRepository
            }

        viewModel = ViewModelProviders
            .of(this, MyFactory(currentRepository, parameters?: RequestParameter()))
            .get(PickerDialogViewModel::class.java)

        dialogView = LayoutInflater.from(context)
            .inflate(R.layout.dialog_fragment, null)

        val builder = AlertDialog.Builder(context)
            .setNegativeButton(R.string.cancel_button, null)

        bindView()
        builder.setView(dialogView)
        return builder.create()
    }



    private fun onDialogClickItem(item: Any) {
        manufacturerLiveData.postValue(item as BaseData)
        dismiss()
    }

    private fun bindView() {

        adapter = PickerDialogAdapter(::onDialogClickItem)

        dialogView.recyclerView.layoutManager = LinearLayoutManager(activity)
        dialogView.recyclerView.setHasFixedSize(true)

        dialogView.recyclerView.adapter = adapter
        viewModel.list.observe(this, Observer<PagedList<BaseData>> {
            adapter.submitList(it)
        })

        viewModel.state.observe(this, Observer<PickerDialogDataSource.State> {
            when (it) {
                PickerDialogDataSource.State.LOADING -> loading()
                PickerDialogDataSource.State.DONE -> done()
                PickerDialogDataSource.State.ERROR -> error()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.list.removeObservers(this)
        viewModel.state.removeObservers(this)
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
        const val SERVICE = "SERVICE"

        fun newInstance(title: String): PickerDialogFragment {
            val dialog = PickerDialogFragment()
            val bundle = Bundle()
            bundle.putString("title", title)
            dialog.arguments = bundle
            return dialog
        }
    }

    class MyFactory(private val repository: BaseRepository,
                    private val parameter: RequestParameter): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PickerDialogViewModel(repository, parameter) as T
        }
    }

}