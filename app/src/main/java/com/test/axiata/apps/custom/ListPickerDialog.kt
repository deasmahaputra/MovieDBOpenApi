package com.test.axiata.apps.custom

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.test.axiata.apps.R
import com.test.axiata.apps.network.response.GenreResponse
import kotlinx.android.synthetic.main.dialog_list_picker.*
import kotlinx.android.synthetic.main.item_sample_text.view.*


class ListPickerDialog : BottomSheetDialogFragment() {

    companion object {
        private const val TAG = "ListPickerDialog"
        private const val KEY_TITLE = "title"
        private const val KEY_ITEMS = "items"
        private const val KEY_CODE = "reqCode"

        fun newInstance(title: String, items: GenreResponse?, reqCode: Int = 0): ListPickerDialog {
            val dialog = ListPickerDialog()
            val bundle = Bundle()
            bundle.putString(KEY_TITLE, title)
            bundle.putString(KEY_ITEMS, Gson().toJson(items))
            bundle.putInt(KEY_CODE, reqCode)
            dialog.arguments = bundle
            return dialog
        }
    }

    private lateinit var selection: (dialog: ListPickerDialog, item: GenreResponse.GenresItem, reqCode: Int, position: Int) -> Unit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.dialog_list_picker, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = arguments?.getString(KEY_TITLE)
        val items : GenreResponse = Gson().fromJson(arguments?.getString(KEY_ITEMS), GenreResponse::class.java)
        val reqCode = arguments?.getInt(KEY_CODE)
        textDialogTitle.text = title
        listDialogListPicker.adapter = context?.let {
            items.let {
                it1 -> reqCode?.let {
                it2 -> ListPickerDialogAdapter(it, items, it2, this, selection)
            } }
        }
    }

    fun show(manager: FragmentManager, selection: (dialog: ListPickerDialog, itemText: GenreResponse.GenresItem, reqCode: Int, position: Int) -> Unit) {
        this.selection = selection
        super.show(manager, TAG)
    }

    class ListPickerDialogAdapter(private val context: Context,
                                  private val items: GenreResponse,
                                  private val reqCode: Int,
                                  private val dialog: ListPickerDialog,
                                  private val selection: (dialog: ListPickerDialog, item: GenreResponse.GenresItem, reqCode: Int, position: Int) -> Unit)
        : RecyclerView.Adapter<ListPickerDialogAdapter.ListPickerDialogHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPickerDialogHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.item_sample_text, parent, false)
            return ListPickerDialogHolder(view, selection)
        }

        override fun onBindViewHolder(holder: ListPickerDialogHolder, position: Int) {
            items.genres?.get(position)?.let { holder.bindData(dialog, it, reqCode, position) }
        }

        override fun getItemCount(): Int = items.genres.size

        class ListPickerDialogHolder(view: View, private val selection: ((dialog: ListPickerDialog, item: GenreResponse.GenresItem, reqCode: Int, position: Int) -> Unit)?)
            : RecyclerView.ViewHolder(view) {

            fun bindData(dialog: ListPickerDialog, itemText: GenreResponse.GenresItem, reqCode: Int, position: Int) {
                itemView.textItem.text = itemText.name
                itemView.setOnClickListener { selection?.invoke(dialog, itemText, reqCode, position) }
            }
        }

    }

}