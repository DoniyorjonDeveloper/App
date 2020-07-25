package intellect.dev.actualproject.iu.adapters

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import intellect.dev.actualproject.R
import intellect.dev.actualproject.room.entities.ContactData
import intellect.dev.actualproject.utils.SingleBlock
import intellect.dev.actualproject.utils.extentions.bindItem
import intellect.dev.actualproject.utils.extentions.inflate
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    private val ls = ArrayList<ContactData>()
    private var listenerItem: SingleBlock<ContactData>? = null
    private var listenerEdit: SingleBlock<ContactData>? = null
    private var listenerDelete: SingleBlock<ContactData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_contact))

    override fun getItemCount() = ls.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    fun submitList(data: List<ContactData>) {
        ls.clear()
        ls.addAll(data)
        notifyItemRangeRemoved(0, data.size)
    }

    fun removeItem(data: ContactData) {
        val index = ls.indexOfFirst { it.id == data.id }
        ls.removeAt(index)
        notifyItemRemoved(index)
    }

    fun updateItem(data: ContactData) {
        val index = ls.indexOfFirst { it.id == data.id }
        ls[index] = data
        notifyItemChanged(index)
    }

    fun insertItem(data: ContactData) {
        ls.add(data)
        notifyItemInserted(ls.size - 1)
    }

    fun setOnItemClickListener(block: SingleBlock<ContactData>) {
        listenerItem = block
    }

    fun setOnEditClickListener(block: SingleBlock<ContactData>) {
        listenerEdit = block
    }

    fun setOnDeleteClickListener(block: SingleBlock<ContactData>) {
        listenerDelete = block
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.apply {
                setOnClickListener { listenerItem?.invoke(ls[adapterPosition]) }
                buttonAdMore.setOnClickListener {
                    val menu = PopupMenu(context, it)
                    menu.inflate(R.menu.crud_menu)
                    menu.setOnMenuItemClickListener {
                        when (it.itemId) {
                            R.id.delete -> listenerDelete?.invoke(ls[adapterPosition])
                            R.id.edit -> listenerEdit?.invoke(ls[adapterPosition])
                        }
                        true
                    }
                    menu.show()
                }
            }
        }

        fun bind() = bindItem {
            val d = ls[adapterPosition]
            textTitle.text = d.name
            textNumber.text = d.number
        }
    }
}