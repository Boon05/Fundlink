package com.example.fundlink.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fundlink.data.model.FundingOpportunity
import com.example.fundlink.databinding.ItemOpportunityBinding

class OpportunityAdapter(private val click: (FundingOpportunity) -> Unit) :
    RecyclerView.Adapter<OpportunityAdapter.Holder>() {

    private val items = mutableListOf<FundingOpportunity>()

    fun submit(newItems: List<FundingOpportunity>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): Holder =
        Holder(ItemOpportunityBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: Holder, position: Int) =
        holder.bind(items[position])

    inner class Holder(private val b: ItemOpportunityBinding) :
        RecyclerView.ViewHolder(b.root) {

        fun bind(item: FundingOpportunity) {
            b.tvOpportunityTitle.text = item.title
            b.tvOpportunityAmount.text = "KES %,.2f".format(item.amount)
            b.tvOpportunityDeadline.text = "Deadline: ${item.deadline}"
            b.tvOpportunitySummary.text = item.description
            b.btnViewOpportunity.setOnClickListener { click(item) }
        }
    }
}
