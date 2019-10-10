package com.nickagas.formvalidationlivedata

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nickagas.formvalidationlivedata.databinding.ItemBinding



data class Item(var id : String, var firstName : String, val lastName : String, val position : Int, val teamName : String)

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemBinding.inflate(LayoutInflater.from(parent?.context), parent, false))
    }

    var items = listOf<Item>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var viewModel: FormValidationViewModel? = null

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.bind(items[position])


    override fun getItemCount(): Int = items.size

    inner class ItemViewHolder(private val itemBinding: ItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Item) {
            itemBinding.item = item
            itemBinding.handler = Handler()
            itemBinding.viewModel = viewModel
            itemBinding.executePendingBindings()
        }
    }
}