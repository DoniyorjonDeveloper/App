package intellect.dev.actualproject.iu.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import intellect.dev.actualproject.R
import intellect.dev.actualproject.model.IntroData
import intellect.dev.actualproject.utils.extentions.bindItem
import intellect.dev.actualproject.utils.extentions.inflate
import kotlinx.android.synthetic.main.pager_component.view.*

class IntroAdapter(private val data: List<IntroData>) : RecyclerView.Adapter<IntroAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.pager_component))

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() = bindItem {
            val d = data[adapterPosition]
            circle_image.setImageResource(d.icon)
            titleText.text = d.title
            descriptionText.text = d.description
        }
    }
}