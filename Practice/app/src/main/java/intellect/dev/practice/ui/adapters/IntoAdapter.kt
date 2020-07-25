package intellect.dev.practice.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import intellect.dev.practice.R
import intellect.dev.practice.data.model.IntoData
import intellect.dev.practice.utils.extentions.bindItem
import intellect.dev.practice.utils.extentions.inflate
import kotlinx.android.synthetic.main.into_pager.view.*

class IntoAdapter(private val data: List<IntoData>) :
    RecyclerView.Adapter<IntoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            =ViewHolder(parent.inflate(R.layout.into_pager))

    override fun getItemCount()=data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)=holder.bind()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() = bindItem {
            val d = data[adapterPosition]
            circleImageView.setImageResource(d.icon)
            textTitlePager.text=d.title
            description.text=d.description
        }
    }
}