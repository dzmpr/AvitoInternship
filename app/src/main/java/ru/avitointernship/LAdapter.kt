package ru.avitointernship

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class LAdapter(private val itemRemovedCallback: MainActivity.ItemRemovedCallback) :
    ListAdapter<ListItem, LViewHolder>(ListItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LViewHolder {
        return LViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_plate, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.bDelete.isEnabled = true
        holder.bDelete.setOnClickListener {
            it.isEnabled = false // Preventing double-click on button
            itemRemovedCallback.removeItem(holder.adapterPosition)
        }
    }
}

class ListItemDiffCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem == newItem
}

class LViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val tvId: TextView = v.findViewById(R.id.tvItemId)
    val bDelete: Button = v.findViewById(R.id.bDelete)

    fun bind(item: ListItem) {
        tvId.text = item.id.toString()
    }
}
