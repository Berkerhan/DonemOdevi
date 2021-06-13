package com.berkerhan.donemodevi.adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkerhan.donemodevi.R
import com.berkerhan.donemodevi.data.model.User
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.item_layout.view.*

class UserAdapter:RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    private val differCallback=object :DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem==newItem
        }
    }

    val differ=AsyncListDiffer(this, differCallback)

    override fun getItemCount():Int {
        Log.i("USER_SIZE", differ.currentList.size.toString())
        return differ.currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val users=differ.currentList[position]
        holder.itemView.apply {
            val image=users.avatar

            Glide.with(context).load(image).apply {
                RequestOptions().error(R.drawable.ic_warning).centerCrop()
            }.listener(object :RequestListener<Drawable?> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable?>?, isFirstResource: Boolean): Boolean {
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable?>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    return false
                }
            }).into(user_image)
            val email=users.email
            user_email.text=email
            val name=users.name
            user_name.text=name
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(users)
        }
    }

    private var onItemClickListener:((User)->(Unit))?=null

    fun setUserItemClickListener(itemClickListener:((User)->Unit)) {
        onItemClickListener=itemClickListener
    }

}