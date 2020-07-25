package intellect.dev.practice.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import intellect.dev.practice.R
import intellect.dev.practice.data.model.ContactData
import intellect.dev.practice.utils.SingleBlock
import intellect.dev.practice.utils.extentions.bindItem
import intellect.dev.practice.utils.extentions.inflate
import kotlinx.android.synthetic.main.item_data.view.*

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    private var differ = AsyncListDiffer(this, ITEM_DIFF)
    private var listenerEdit: SingleBlock<Int>? = null
    private var listenerDelete: SingleBlock<Int>? = null

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<ContactData>() {
            override fun areItemsTheSame(oldItem: ContactData, newItem: ContactData) =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: ContactData, newItem: ContactData) =
                oldItem.title == newItem.title && oldItem.description == newItem.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_data))

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()
    fun submitList(ls: List<ContactData>) {
        differ.submitList(ls.toMutableList())
    }

    fun setOnEditClickListener(block: SingleBlock<Int>) {
        listenerEdit = block
    }

    fun setOnDeleteClickListener(block: SingleBlock<Int>) {
        listenerDelete = block
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.apply {
                edit.setOnClickListener { listenerEdit?.invoke(adapterPosition) }
                delete.setOnClickListener { listenerDelete?.invoke(adapterPosition) }
            }
        }

        fun bind() = bindItem {
            val d = differ.currentList[adapterPosition]
            textTitle.text = d.title
            textNumber.text = d.description
        }
    }
}