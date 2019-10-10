package com.nickagas.formvalidationlivedata

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nickagas.formvalidationlivedata.databinding.ItemBinding



data class Item(val id : String, val firstName : String, val lastName : String, val position : Int, val teamName : String)

class ItemViewModel(val position : String, val name : String, val team : String, val isMe : Boolean)

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemBinding.inflate(LayoutInflater.from(parent?.context), parent, false))
    }

    var items = listOf<ItemViewModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.bind(items[position])


    override fun getItemCount(): Int = items.size

    inner class ItemViewHolder(private val itemBinding: ItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(itemViewModel: ItemViewModel) {
            itemBinding.item = itemViewModel
            itemBinding.executePendingBindings()
        }
    }
}