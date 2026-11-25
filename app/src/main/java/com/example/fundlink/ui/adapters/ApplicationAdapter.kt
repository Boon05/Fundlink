package com.example.fundlink.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fundlink.data.model.StudentApplication
import com.example.fundlink.databinding.ItemApplicationBinding

class ApplicationAdapter : RecyclerView.Adapter<ApplicationAdapter.Holder>() {
    private val items = mutableListOf<StudentApplication>()

    fun submit(newItems: List<StudentApplication>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): Holder =
        Holder(ItemApplicationBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: Holder, position: Int) =
        holder.bind(items[position])

    inner class Holder(private val b: ItemApplicationBinding) :
        RecyclerView.ViewHolder(b.root) {

        fun bind(a: StudentApplication) {
            b.tvAppTitle.text = a.opportunityTitle
            b.tvAppAmount.text = "Requested: KES %,.2f".format(a.requestedAmount)
            b.tvAppStatus.text = "Status: ${a.status}"
            b.tvAppComment.text = "Officer comment: ${a.reviewComment}"
            b.tvAppComment.visibility = if (a.reviewComment.isBlank()) View.GONE else View.VISIBLE
        }
    }
}
