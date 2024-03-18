package com.ifs21038.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21038.dinopedia.databinding.ItemRowFamiliaBinding
class ListFamiliaAdapter(private val listFamilia: ArrayList<Familia>) :
    RecyclerView.Adapter<ListFamiliaAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback:
                               OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType:
    Int): ListViewHolder {
        val binding =
            ItemRowFamiliaBinding.inflate(LayoutInflater.from(viewGroup.context),
                viewGroup, false)
        return ListViewHolder(binding)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position:
    Int) {
        val familia = listFamilia[position]
        holder.binding.ivItemFamilia.setImageResource(familia.icon)
        holder.binding.tvItemFamilia.text = familia.name
        holder.itemView.setOnClickListener {
            onItemClickCallback
                .onItemClicked(listFamilia[holder.adapterPosition])
        }
    }
    override fun getItemCount(): Int = listFamilia.size
    class ListViewHolder(var binding: ItemRowFamiliaBinding) :
        RecyclerView.ViewHolder(binding.root)
    interface OnItemClickCallback {
        fun onItemClicked(data: Familia)
    }
}
