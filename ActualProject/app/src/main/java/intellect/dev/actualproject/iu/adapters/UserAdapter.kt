package intellect.dev.actualproject.iu.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import intellect.dev.actualproject.R
import intellect.dev.actualproject.room.entities.UserData
import intellect.dev.actualproject.utils.SingleBlock
import intellect.dev.actualproject.utils.extentions.bindItem
import intellect.dev.actualproject.utils.extentions.inflate
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private val ls = ArrayList<UserData>()
    private var listenerItem: SingleBlock<UserData>? = null
    private var listenerEdit: SingleBlock<UserData>? = null
    private var listenerDelete: SingleBlock<UserData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_user))

    override fun getItemCount() = ls.size

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) = holder.bind()

    fun submitList(data: List<UserData>) {
        ls.clear()
        ls.addAll(data)
        notifyItemRangeRemoved(0, data.size)
    }

    fun removeItem(data: UserData) {
        val index = ls.indexOfFirst { it.id == data.id }
        ls.removeAt(index)
        notifyItemRemoved(index)
    }

    fun updateItem(data: UserData) {
        val index = ls.indexOfFirst { it.id == data.id }
        ls[index] = data
        notifyItemChanged(index)
    }

    fun insertItem(data: UserData) {
        ls.add(data)
        notifyItemInserted(ls.size - 1)
    }

    fun setOnItemClickListener(block: SingleBlock<UserData>) {
        listenerItem = block
    }

    fun setOnItemEditListener(block: SingleBlock<UserData>) {
        listenerEdit = block
    }

    fun setOnItemDeleteListener(block: SingleBlock<UserData>) {
        listenerDelete = block
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.apply {
                setOnClickListener { listenerItem?.invoke(ls[adapterPosition]) }
                buttonMore.setOnClickListener {
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
            textTitleUser.text = d.login
        }
    }
}