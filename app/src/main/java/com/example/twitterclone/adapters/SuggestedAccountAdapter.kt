package com.example.twitterclone.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.twitterclone.R
import com.example.twitterclone.data.SuggestedAccount
import com.google.firebase.database.core.Context
import de.hdodenhof.circleimageview.CircleImageView

class SuggestedAccountAdapter(
    private val listOfAccounts: List<SuggestedAccount>,
    private val context: android.content.Context,
    private val clickListener: ClickListener
) : RecyclerView.Adapter<SuggestedAccountAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: CircleImageView = itemView.findViewById(R.id.suggested_account_profile)
        val email: TextView = itemView.findViewById(R.id.text_suggested_account_email)
        val btnFollow: Button = itemView.findViewById(R.id.btn_follow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_suggested_account,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfAccounts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentAccount = listOfAccounts[position]
        holder.email.text = currentAccount.userEmail
        Glide.with(context)
            .load(currentAccount.profileImage)
            .into(holder.image)
        holder.btnFollow.setOnClickListener{
            clickListener.onFollowClicked(currentAccount.uid)
        }

    }

    interface ClickListener {
        fun onFollowClicked(uid: String)
    }
}