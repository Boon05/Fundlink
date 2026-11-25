package com.example.fundlink.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fundlink.R
import com.example.fundlink.data.model.StudentApplication

class ReviewAdapter(
    private val applications: List<StudentApplication>
) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val student: TextView =
            view.findViewById(R.id.txtStudent)

        val opportunity: TextView =
            view.findViewById(R.id.txtOpportunity)

        val status: TextView =
            view.findViewById(R.id.txtStatus)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_review_application,
                parent,
                false
            )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val item = applications[position]

        holder.student.text = item.studentName
        holder.opportunity.text = item.opportunityTitle
        holder.status.text = item.status
    }

    override fun getItemCount(): Int {
        return applications.size
    }
}